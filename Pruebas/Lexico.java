package Practicas.Pruebas;

import java.util.ArrayList;
import java.util.List;

/**
 * Analizador léxico para el lenguaje híbrido (Java + Pascal + Python)
 */
public class Lexico {
	private List<String> erroreslex;
    // Tipo de tokn
    public enum TipoToken {
        PR,
        ID,
        NUM,
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
        "Program", "endProgram",
        
        // Tipos de datos
        "int", "float", "double", "string", "boolean", "char","void",
        
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
        this.erroreslex = new ArrayList<>();
        erroreslex.clear();
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
        int x=0;
        
        // Ignorar líneas de comentario completas
        if (linea.trim().startsWith("//") || linea.trim().startsWith("#")) {
            tokens.add(new Token(TipoToken.COMENTARIO, linea.trim(), numeroLinea));
            return;
        }
        
        for (int i = 0; i < caracteres.length; i++) {
            char c = caracteres[i];
            
            // Procesar números (incluyendo notación científica)
            if (Character.isDigit(c) || (c == '.' && i + 1 < caracteres.length && Character.isDigit(caracteres[i + 1]))) {
                if (lexema.length() > 0) {
                    agregarToken(lexema.toString(), numeroLinea, tokens);
                    lexema = new StringBuilder();
                }
                
                // Procesar número completo (incluyendo notación científica)
                String numero = procesarNumero(caracteres, i);
                char car= numero.charAt(numero.length()-1);
                numero=numero.replace("r", "");
                if(car!='r')
                	tokens.add(new Token(TipoToken.NUM, numero, numeroLinea));
                else {
                	erroreslex.add("Error lexico en linea "+numeroLinea+": numero con formato incorrecto");
                	tokens.add(new Token(TipoToken.DESCONOCIDO, numero, numeroLinea));
                }
                	
                i += numero.length() - 1; // Ajustar índice
                continue;
            }
            
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
                while (i < caracteres.length) {
                	if(caracteres[i] == '"')
                    {
                    	cadena.append(caracteres[i]);
                    	break;
                    }
                    if (caracteres[i] == '\\' && i + 1 < caracteres.length) {
                        cadena.append(caracteres[i]);
                        i++;
                        cadena.append(caracteres[i]);
                    } else {
                        cadena.append(caracteres[i]);
                    }
                    i++;
                }
                if(cadena.length()>1 && (cadena.toString().charAt(cadena.length()-1)=='"' && cadena.toString().charAt(0)=='"')) {
                	if(i < caracteres.length) {
                        cadena.append(caracteres[i]);
                    }
                	tokens.add(new Token(TipoToken.CADENA, cadena.toString(), numeroLinea));
                }
                else {
                	erroreslex.add("Error lexico en linea "+numeroLinea+": falta de comillas de cierre");
                	tokens.add(new Token(TipoToken.DESCONOCIDO, cadena.toString(), numeroLinea));
                }
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
                while (i < caracteres.length) {
                	if(caracteres[i] == '\'')
                    {
                    	caracter.append(caracteres[i]);
                    	break;
                    }
                    if (caracteres[i] == '\\' && i + 1 < caracteres.length) {
                        caracter.append(caracteres[i]);
                        i++;
                        caracter.append(caracteres[i]);
                    } else {
                        caracter.append(caracteres[i]);
                    }
                    i++;
                }
                
                if(caracter.length()==3) {
                	 if((caracter.toString().charAt(2)=='\'' && caracter.toString().charAt(0)=='\'')) {
                     	if (i < caracteres.length) {
                     		caracter.append(caracteres[i]);
                         }
                     	tokens.add(new Token(TipoToken.CARACTER, caracter.toString(), numeroLinea));
                     }
                	 else {
                      	erroreslex.add("Error lexico en linea "+numeroLinea+": falta de comillas simples de cierre");
                      	tokens.add(new Token(TipoToken.DESCONOCIDO, caracter.toString(), numeroLinea));
                      }
                }else {
                 	erroreslex.add("Error lexico en linea "+numeroLinea+": falta de comillas simples de cierre");
                 	tokens.add(new Token(TipoToken.DESCONOCIDO, caracter.toString(), numeroLinea));
                 }
               
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
            tokens.add(new Token(TipoToken.PR, lexema, numeroLinea));
            return;
        }
        
        // Verificar si es un identificador
        if (esIdentificador(lexema)) {
            tokens.add(new Token(TipoToken.ID, lexema, numeroLinea));
            return;
        }
        
        // Si no es ninguno de los anteriores, es un token desconocido
        erroreslex.add("Error lexico en linea "+numeroLinea+": token desconocido verificar estructura");
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
     * Procesa un número completo incluyendo notación científica
     */
    private String procesarNumero(char[] caracteres, int inicio) {
        StringBuilder numero = new StringBuilder();
        int i = inicio;
        
        // Parte entera
        while (i < caracteres.length && Character.isDigit(caracteres[i])) {
            numero.append(caracteres[i]);
            i++;
        }
        
        // Parte decimal
        if (i < caracteres.length && caracteres[i] == '.') {
            numero.append(caracteres[i]);
            i++;
            while (i < caracteres.length && Character.isDigit(caracteres[i])) {
                numero.append(caracteres[i]);
                i++;
            }
        }
        
        // Parte exponencial (notación científica)
        if (i < caracteres.length && (caracteres[i] == 'e' || caracteres[i] == 'E')) {
            numero.append(caracteres[i]);
            i++;
            
            // Signo opcional del exponente
            if (i < caracteres.length && (caracteres[i] == '+' || caracteres[i] == '-')) {
                numero.append(caracteres[i]);
                i++;
            }
            
            // Dígitos del exponente
            while (i < caracteres.length && Character.isDigit(caracteres[i])) {
                numero.append(caracteres[i]);
                i++;
            }
        }
        if(numero.toString().matches("^0|[+-]?(?:(?:[1-9]\\d*)(?:\\.\\d*[1-9])?|0\\.\\d*[1-9])(e[+-]?\\d+)?$"))
        	return numero.toString();
        else
        	return numero.toString()+"r";
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
            } 
            resultado.append(obtenerNombreCorto(token.getTipo()).equals("PR") || obtenerNombreCorto(token.getTipo()).equals("OP")|| obtenerNombreCorto(token.getTipo()).equals("DELIM") 
            		?" "+token.getLexema():" "+obtenerNombreCorto(token.getTipo()));
        }
        
        return resultado.toString();
    }
    
    /**
     * Retorna un nombre corto para el tipo de token.
     */
    private String obtenerNombreCorto(TipoToken tipo) {
        switch (tipo) {
            case PR: return "PR";
            case ID: return "ID";
            case NUM: return "NUM";
            case OPERADOR: return "OP";
            case DELIMITADOR: return "DELIM";
            case CADENA: return "STR";
            case CARACTER: return "CHAR";
            case COMENTARIO: return "COM";
            case DESCONOCIDO: return "DESC";
            default: return tipo.toString();
        }
    }
    
    public String getErrores(int i) {
    	return erroreslex.get(i);
    }
}