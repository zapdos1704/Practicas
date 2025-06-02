package Compilador;

import java.util.ArrayList;
import java.util.List;

/**
 * Analizador léxico para el lenguaje híbrido (Java + Pascal + Python)
 */
public class Lexico {
    
    // Tipo de token
    public enum TipoToken {
        PALABRA_RESERVADA,
        IDENTIFICADOR,
        NUMERO,
        OPERADOR,
        DELIMITADOR,
        CADENA,
        CARACTER,
        COMENTARIO,
        DESCONOCIDO
    }
    
    // Clase para representar un token
    public static class Token {
        private TipoToken tipo;
        private String lexema;
        private int linea;
        
        public Token(TipoToken tipo, String lexema, int linea) {
            this.tipo = tipo;
            this.lexema = lexema;
            this.linea = linea;
        }
        
        public TipoToken getTipo() {
            return tipo;
        }
        
        public String getLexema() {
            return lexema;
        }
        
        public int getLinea() {
            return linea;
        }
        
        @Override
        public String toString() {
            return "[" + tipo + ": '" + lexema + "', línea: " + linea + "]";
        }
    }
    
    // Palabras reservadas del lenguaje híbrido
    private static final String[] PALABRAS_RESERVADAS = {
        // Estructura del programa
        "beginProgram", "endProgram",
        
        // Tipos de datos
        "int", "float", "double", "string", "boolean", "char",
        
        // Estructuras de control
        "for", "endFor", 
        "while", "endWhile", 
        "if", "endif", "else",
        
        // Métodos
        "method", "endMethod",
        "return",
        
        // Valores literales
        "true", "false", "null"
    };
    
    /**
     * Analiza el código fuente y genera una lista de tokens.
     */
    public List<Token> analizar(String codigo) {
        List<Token> tokens = new ArrayList<>();
        String[] lineas = codigo.split("\n");
        
        for (int numLinea = 0; numLinea < lineas.length; numLinea++) {
            String linea = lineas[numLinea];
            analizarLinea(linea, numLinea + 1, tokens);
        }
        
        return tokens;
    }
    
    /**
     * Analiza una línea de código y añade los tokens encontrados a la lista.
     */
    private void analizarLinea(String linea, int numeroLinea, List<Token> tokens) {
        StringBuilder lexema = new StringBuilder();
        char[] caracteres = linea.toCharArray();
        
        // Ignorar líneas de comentario completas
        if (linea.trim().startsWith("//") || linea.trim().startsWith("#")) {
            tokens.add(new Token(TipoToken.COMENTARIO, linea.trim(), numeroLinea));
            return;
        }
        
        for (int i = 0; i < caracteres.length; i++) {
            char c = caracteres[i];
            
            // Comprobar por comentarios de estilo //
            if (c == '/' && i + 1 < caracteres.length && caracteres[i + 1] == '/') {
                if (lexema.length() > 0) {
                    agregarToken(lexema.toString(), numeroLinea, tokens);
                    lexema = new StringBuilder();
                }
                
                StringBuilder comentario = new StringBuilder("//");
                i += 2;
                while (i < caracteres.length) {
                    comentario.append(caracteres[i]);
                    i++;
                }
                
                tokens.add(new Token(TipoToken.COMENTARIO, comentario.toString(), numeroLinea));
                return;
            }
            
            // Saltar espacios en blanco
            if (Character.isWhitespace(c)) {
                if (lexema.length() > 0) {
                    agregarToken(lexema.toString(), numeroLinea, tokens);
                    lexema = new StringBuilder();
                }
                continue;
            }
            
            // Procesar operadores y delimitadores
            if (esOperadorODelimitador(c)) {
                if (lexema.length() > 0) {
                    agregarToken(lexema.toString(), numeroLinea, tokens);
                    lexema = new StringBuilder();
                }
                
                // Verificar operadores de dos caracteres
                if (i + 1 < caracteres.length) {
                    char siguienteChar = caracteres[i + 1];
                    if ((c == '=' && siguienteChar == '=') || 
                        (c == '!' && siguienteChar == '=') ||
                        (c == '<' && siguienteChar == '=') ||
                        (c == '>' && siguienteChar == '=') ||
                        (c == '&' && siguienteChar == '&') ||
                        (c == '|' && siguienteChar == '|')) {
                        tokens.add(new Token(TipoToken.OPERADOR, c + "" + siguienteChar, numeroLinea));
                        i++;
                        continue;
                    }
                }
                
                TipoToken tipo = esDelimitador(c) ? TipoToken.DELIMITADOR : TipoToken.OPERADOR;
                tokens.add(new Token(tipo, Character.toString(c), numeroLinea));
                continue;
            }
            
            // Procesar cadenas con comillas dobles
            if (c == '"') {
                if (lexema.length() > 0) {
                    agregarToken(lexema.toString(), numeroLinea, tokens);
                    lexema = new StringBuilder();
                }
                
                StringBuilder cadena = new StringBuilder();
                cadena.append(c);
                
                i++;
                while (i < caracteres.length && caracteres[i] != '"') {
                    if (caracteres[i] == '\\' && i + 1 < caracteres.length) {
                        cadena.append(caracteres[i]);
                        i++;
                        cadena.append(caracteres[i]);
                    } else {
                        cadena.append(caracteres[i]);
                    }
                    i++;
                }
                
                if (i < caracteres.length) {
                    cadena.append(caracteres[i]);
                }
                
                tokens.add(new Token(TipoToken.CADENA, cadena.toString(), numeroLinea));
                continue;
            }
            
            // Procesar caracteres con comillas simples
            if (c == '\'') {
                if (lexema.length() > 0) {
                    agregarToken(lexema.toString(), numeroLinea, tokens);
                    lexema = new StringBuilder();
                }
                
                StringBuilder caracter = new StringBuilder();
                caracter.append(c);
                
                i++;
                while (i < caracteres.length && caracteres[i] != '\'') {
                    if (caracteres[i] == '\\' && i + 1 < caracteres.length) {
                        caracter.append(caracteres[i]);
                        i++;
                        caracter.append(caracteres[i]);
                    } else {
                        caracter.append(caracteres[i]);
                    }
                    i++;
                }
                
                if (i < caracteres.length) {
                    caracter.append(caracteres[i]);
                }
                
                tokens.add(new Token(TipoToken.CARACTER, caracter.toString(), numeroLinea));
                continue;
            }
            
            // Acumular caracteres para formar lexemas
            lexema.append(c);
        }
        
        // Agregar el último token si existe
        if (lexema.length() > 0) {
            agregarToken(lexema.toString(), numeroLinea, tokens);
        }
    }
    
    /**
     * Determina el tipo de token y lo añade a la lista.
     */
    private void agregarToken(String lexema, int numeroLinea, List<Token> tokens) {
        // Verificar si es una palabra reservada
        if (esPalabraReservada(lexema)) {
            tokens.add(new Token(TipoToken.PALABRA_RESERVADA, lexema, numeroLinea));
            return;
        }
        
        // Verificar si es un número
        if (esNumero(lexema)) {
            tokens.add(new Token(TipoToken.NUMERO, lexema, numeroLinea));
            return;
        }
        
        // Verificar si es un identificador
        if (esIdentificador(lexema)) {
            tokens.add(new Token(TipoToken.IDENTIFICADOR, lexema, numeroLinea));
            return;
        }
        
        // Si no es ninguno de los anteriores, es un token desconocido
        tokens.add(new Token(TipoToken.DESCONOCIDO, lexema, numeroLinea));
    }
    
    /**
     * Verifica si el lexema es una palabra reservada.
     */
    private boolean esPalabraReservada(String lexema) {
        for (String palabra : PALABRAS_RESERVADAS) {
            if (palabra.equals(lexema)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Verifica si el lexema es un número.
     */
    private boolean esNumero(String lexema) {
        try {
            Double.parseDouble(lexema);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Verifica si el lexema es un identificador válido.
     */
    private boolean esIdentificador(String lexema) {
        if (lexema.isEmpty()) {
            return false;
        }
        
        char primerChar = lexema.charAt(0);
        if (!Character.isLetter(primerChar) && primerChar != '_') {
            return false;
        }
        
        for (int i = 1; i < lexema.length(); i++) {
            char c = lexema.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Verifica si el carácter es un operador o delimitador.
     */
    private boolean esOperadorODelimitador(char c) {
        return esOperador(c) || esDelimitador(c);
    }
    
    /**
     * Verifica si el carácter es un operador.
     */
    private boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || 
               c == '=' || c == '<' || c == '>' || c == '!' || 
               c == '&' || c == '|' || c == ':';
    }
    
    /**
     * Verifica si el carácter es un delimitador.
     */
    private boolean esDelimitador(char c) {
        return c == ';' || c == ',' || c == '(' || c == ')' || 
               c == '{' || c == '}';
    }
    
    /**
     * Formatea la lista de tokens para mostrarla en la interfaz.
     */
    public String formatearTokens(List<Token> tokens) {
        StringBuilder resultado = new StringBuilder();
        int lineaActual = -1;
        
        for (Token token : tokens) {
            if (token.getLinea() != lineaActual) {
                lineaActual = token.getLinea();
                if (lineaActual != 1) {
                    resultado.append("\n");
                }
                resultado.append("Línea ").append(lineaActual).append(": ");
            } else {
                resultado.append(" | ");
            }
            
            resultado.append(obtenerNombreCorto(token.getTipo()).equals("PR")?token.getLexema():token.getTipo());
        }
        
        return resultado.toString();
    }
    
    /**
     * Retorna un nombre corto para el tipo de token.
     */
    private String obtenerNombreCorto(TipoToken tipo) {
        switch (tipo) {
            case PALABRA_RESERVADA: return "PR";
            case IDENTIFICADOR: return "ID";
            case NUMERO: return "NUM";
            case OPERADOR: return "OP";
            case DELIMITADOR: return "DELIM";
            case CADENA: return "STR";
            case CARACTER: return "CHAR";
            case COMENTARIO: return "COM";
            case DESCONOCIDO: return "DESC";
            default: return tipo.toString();
        }
    }
}