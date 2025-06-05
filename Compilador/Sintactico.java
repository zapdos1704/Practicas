package Practicas.Compilador;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.HashMap;
import java.util.Map;

/**
 * Analizador sintáctico LL(1) para el lenguaje híbrido usando tabla sintáctica.
 */
public class Sintactico {
    
    private List<Lexico.Token> tokens;
    private int posicion;
    private List<String> errores;
    private Stack<String> pila;
    private Map<String, Integer> mapaTokens;
    private Map<String, Integer> mapaNoTerminales;
    
    // Tokens terminales
    private static final String[] TERMINALES = {
        "ID", "NUM", "STRING", "CHAR", "BOOLEAN", "+", "-", "*", "/", "<", ">", "<=", ">=", 
        "==", "!=", "&&", "||", "!", "=", "(", ")", "{", "}", ";", ",", ":", 
        "beginProgram", "endProgram", "for", "endFor", "while", "endWhile", "if", "endif", 
        "else", "method", "endMethod", "return", "def", "int", "float", "double", 
        "string", "boolean", "char", "$"
};
    
    // Variables no terminales
    private static final String[] NO_TERMINALES = {
        "P", "D", "D'", "S", "S'", "I", "L", "L'", "E", "EL", "EL'", "ER", "ER'", "T", "T'", "F", "TIPO", "A","EXPR"
    };
    
    // Tabla sintáctica con acciones implícitas
    private static final String[][] TABLA_SINTACTICA = {
    	    //                ID                    NUM             STRING          CHAR            BOOLEAN         +               -               *               /               <               >               <=              >=              ==              !=              &&              ||              !               =               (               )               {               }               ;               ,               :               beginProgram                 endProgram          for                       endFor          while           			endWhile        if              	endif           else            method          					endMethod       return          	def             int             		float           	double          		string          		boolean         		char            	   $
    		/*P    */{	"SALTAR", 				"SALTAR", 		   "SALTAR", 	   "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR",       "SALTAR",       "SALTAR",        "SALTAR",       "SALTAR",       "SALTAR",       "SALTAR",       "SALTAR",        "SALTAR",     "SALTAR",        "SALTAR",       "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR",       "SALTAR",        "SALTAR",       "SALTAR",   "beginProgram ID D S endProgram",    "SALTAR",        "SALTAR",                  "SALTAR",       "SALTAR",       		    "SALTAR",    "SALTAR",        	  "SALTAR",       "SALTAR",        "SALTAR",        					"SALTAR",      "SALTAR",     	  "SALTAR",        "SALTAR",        	 "SALTAR",        	   "SALTAR",      		   "SALTAR",        		"SALTAR",      		"SALTAR",           	"SACAR"},
    	    
    	    /*D    */{  "SALTAR",                "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		"SALTAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  "SACAR", 		   "SALTAR", 				  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SACAR", 		      "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "SALTAR", 	   "TIPO ID = E ; D'", 	 "TIPO ID = E ; D'",   "TIPO ID = E ; D'", 	   "TIPO ID = E ; D'", 		"TIPO ID = E ; D'", "TIPO ID = E ; D'", 	"SACAR"},
    	       		 
    	    /*D'   */{  "SALTAR",               "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		"SALTAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  "SACAR", 		   "SALTAR", 				  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SACAR",             "SALTAR", 	  "SALTAR", 	   "method ID L S endMethod D'", 	    "SALTAR", 	   "SALTAR", 		  "SALTAR", 	   "TIPO ID = E ; D'", 	 "TIPO ID = E ; D'",   "TIPO ID = E ; D'", 	   "TIPO ID = E ; D'", 		"TIPO ID = E ; D'", "TIPO ID = E ; D'", 	"SALTAR"},
    	    
    	    /*S    */{ "ID A ; S'",             "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "{ S } S'", 	 "SALTAR", 		"SACAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  "SACAR", 		   "for ID : ER I endFor S'",  "SALTAR", 	  "while EXPR I endWhile S'", "SALTAR",  "if EXPR I endif S'",   "SALTAR", 	  "SALTAR", 	   "method ID L S endMethod S'", 	    "SALTAR", 	   "return E ; S'",   "SALTAR", 	   "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SACAR"},

    	    /*S'   */{"ID A ; S'",              "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "{ S } S'", 	 "SACAR", 		"e", 	 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  "e", 		   	   "for ID : ER I endFor S'",  "e", 	  	  "while EXPR I endWhile S'",  "e",	     "if EXPR I endif S'",   "e", 	  	  "SALTAR", 	   "method ID L S endMethod S'", 	    "e", 	       "return E ; S'",   "SALTAR", 	   "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR"},
    	       
    	    /*I    */{"ID A ;",                 "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "{ S }", 		 "SACAR", 		"SALTAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  "SALTAR", 	   "for ID : ER I endFor",     "SALTAR", 	  "while EXPR I endWhile", 	"SALTAR", 	 "if EXPR I endif",      "e", 	  	  "SALTAR", 	   "method ID L S endMethod", 		    "SALTAR", 	   "return E ;", 	  "SALTAR", 	   "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR"},
    	    
    	    /*L    */{"SALTAR",                 "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "SALTAR", 		 "( L )", 		 "SACAR", 		 "SALTAR", 		"SALTAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  "SALTAR", 	   "SALTAR", 				  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SALTAR",   		  "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "SALTAR", 	   "TIPO ID L'", 	     "TIPO ID L'",   	   "TIPO ID L'", 		   "TIPO ID L'", 			"TIPO ID L'", 		"TIPO ID L'", 			"SALTAR"},
    	       		 
    	    /*L'   */{"SALTAR",                 "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "SACAR", 		 "SALTAR", 		"SALTAR", 		"SALTAR", 		", TIPO ID L'",  "SALTAR", 			"SALTAR", 					  "SALTAR", 	   "SALTAR", 				  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SALTAR",   		  "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "SALTAR", 	   "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR"},
    	    		 
    	    /*E    */{"T EL",                   "T EL",             "T EL",        "T EL",          "T EL",      "+ F T'",       "- F T'",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"! EL", 		 "SALTAR", 		 "( EL )", 		 "SALTAR", 		 "SALTAR", 		"SALTAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  "SALTAR", 	   "SALTAR", 				  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SALTAR",   		  "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "SALTAR", 	   "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR"},
    	      
    	    /*EL   */{"ER EL'",                 "ER EL'",           "ER EL'",      "ER EL'",        "ER EL'",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"ER EL'", 		 "SALTAR", 		 "ER EL'", 		 "SALTAR", 		 "SALTAR", 		"SALTAR", 		"SACAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  "SALTAR", 	   "SALTAR", 				  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SALTAR",   		  "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "SALTAR", 	   "SALTAR", 			 "SALTAR",  		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR"},
    	                                    
    	    /*EL'  */{"SALTAR",                 "SALTAR",           "SALTAR",      "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "&& ER EL'",     "|| ER EL'",  "SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "e", 		 	 "SALTAR", 		"SALTAR", 		"SALTAR", 		"e", 		 	 "SALTAR", 			"SALTAR", 					  "SALTAR", 	   "SALTAR", 				  "e", 	  		  "SALTAR", 				"e", 	 	 "SALTAR",   		  "e", 	  		  "SALTAR", 	   "SALTAR", 							"SALTAR",	    "e",     		  "SALTAR", 	   "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR"},

    	    /*ER   */{"ER'",                  	"ER'",            	"ER'",       	"ER'",          "ER'",       "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"T ER'",	     "SALTAR", 		 "T ER'", 		 "SALTAR", 		 "SALTAR", 		"SALTAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  "SALTAR", 	   "SALTAR", 				  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SALTAR",   		  "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "SALTAR", 	   "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR"},
    	    
    	    /*ER'  */{"SALTAR",                 "SALTAR",           "SALTAR",      "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"< T ER'",      "> T ER'",       "<= T ER'",     ">= T ER'",     "== T ER'",     "!= T ER'",     "e",      		  "e", 			"SALTAR", 		 "e", 		     "SALTAR", 		 "e", 		 	 "SALTAR", 		"SALTAR", 		"SALTAR", 		"e", 		 	 "SALTAR", 			"SALTAR", 					  "SALTAR", 	   "SALTAR", 				  "e", 	  		  "SALTAR", 				"e", 	 	 "SALTAR",   		  "e", 	  		  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "e", 	 		  "SALTAR", 	   "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR"},
    	    	   
    	    /*T    */{"F T'",                   "F T'",              "F T'",       "F T'",          "F T'",      "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"F T'", 		 "SALTAR", 		 "F T'", 		 "SALTAR", 		 "SALTAR", 		"SALTAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  "SALTAR", 	   "SALTAR", 				  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SALTAR",   		  "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "SALTAR", 	   "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR"},
    	              	 
    	    /*T'   */{"SALTAR",                 "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "+ F T'",       "- F T'",       "* F T'",      "/ F T'", 		"e",               "e",          "e",      		 "e",        	 "e",      		 "e",      		 "e",             "e", 			"SALTAR", 		 "SALTAR", 		 "SALTAR",		 "e", 		 	 "SALTAR", 		"SALTAR", 		"SACAR", 		"e", 		 	 "SALTAR",			"SALTAR", 					  "SALTAR", 	   "SALTAR", 				  "e", 	  		  "SALTAR",				    "e", 	     "SALTAR",   		  "e", 	  		  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "e", 	 		  "SALTAR", 	   "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR"},
    	    		 
    	    /*F    */{"ID",                	    "NUM",             "STRING",       "CHAR",          "BOOLEAN",   "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "SALTAR", 		 "( E )", 		 "SALTAR", 		 "SALTAR", 		"SALTAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  "SALTAR", 	   "SALTAR", 				  "SALTAR", 	  "SALTAR", 				"SALTAR",    "SALTAR",   		  "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "SALTAR", 	   "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR"},
    	           		 	  
    	    /*TIPO */{"SALTAR",                 "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		"SALTAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  "SALTAR", 	   "SALTAR", 				  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SALTAR",   		  "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "SALTAR", 	   "int", 			 	 "float",    		   "double", 			   "string", 				"boolean", 			"char", 				"SALTAR"},
    	    		 
    	    /*A    */{"SALTAR",                 "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "= E", 		 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		"SALTAR", 		"e", 			"e", 		 	 "SALTAR", 			"SALTAR", 					  "SALTAR", 	   "SALTAR", 				  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SALTAR",   		  "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "SALTAR", 	   "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR"},
    	    
    	    /*EXPR*/ {"T ER'",                  "T ER'",            "T ER'",       "T ER'",         "T ER'",     "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"T ER'", 		 "SALTAR", 		 "( EXPR )",     "SALTAR", 		 "SALTAR", 		"SALTAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  "SALTAR", 	   "SALTAR", 				  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SALTAR",   		  "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "SALTAR", 	   "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR"}

    	};
    
    public Sintactico() {
        this.errores = new ArrayList<>();
        this.pila = new Stack<>();
        inicializarMapas();
    }
    
    /**
     * Inicializa los mapas para indexar tokens y no terminales.
     */
    private void inicializarMapas() {
        mapaTokens = new HashMap<>();
        for (int i = 0; i < TERMINALES.length; i++) {
            mapaTokens.put(TERMINALES[i], i);
        }
        
        mapaNoTerminales = new HashMap<>();
        for (int i = 0; i < NO_TERMINALES.length; i++) {
            mapaNoTerminales.put(NO_TERMINALES[i], i);
        }
    }
    
    /**
     * Analiza sintácticamente una lista de tokens usando la tabla LL(1).
     */
    public List<String> analizar(List<Lexico.Token> tokens) {
        this.tokens = tokens;
        this.posicion = 0;
        this.errores.clear();
        this.pila.clear();
        
        // Agregar símbolo de fin y símbolo inicial
        pila.push("$");
        pila.push("P");
        
        // Agregar token de fin si no existe
        if (tokens.isEmpty() || !tokens.get(tokens.size() - 1).getLexema().equals("$")) {
            tokens.add(new Lexico.Token(Lexico.TipoToken.DELIMITADOR, "$", 
                       tokens.isEmpty() ? 1 : tokens.get(tokens.size() - 1).getLinea()));
        }
        
        while (!pila.isEmpty() && posicion < tokens.size()) {
            String tope = pila.pop();
            Lexico.Token tokenActual = tokens.get(posicion);
            String lexemaToken = convertirToken(tokenActual);
            System.out.println(pila+"|"+lexemaToken+"|"+tope);
            // Si el tope es un terminal
            if (esTerminal(tope)) {
                if (tope.equals(lexemaToken)) {
                    posicion++; // Coincidir y avanzar
                } else {
                    errores.add("Error en línea " + tokenActual.getLinea() + 
                               ": Se esperaba '" + tope + "' pero se encontró '" + lexemaToken + "'");
                    posicion++; // Intentar recuperarse
                }
            } 
            // Si el tope es un no terminal
            else if (esNoTerminal(tope)) {
                String accion = obtenerAccion(tope, lexemaToken);
                
                if (accion.equals("SALTAR")) {
                    // Saltar token actual, mantener no terminal en pila
                    pila.push(tope);
                    posicion++;
                } else if (accion.equals("SACAR")) {
                    // Sacar no terminal de la pila (ya se hizo con pop())
                    continue;
                } else if (accion.equals("e")) {
                    // Producción vacía, no hacer nada
                    continue;
                } else if (!accion.equals("SALTAR")) {
                    // Es una producción, expandir
                    expandirProduccion(accion);
                } else {
                    errores.add("Error en línea " + tokenActual.getLinea() + 
                               ": No se puede procesar '" + lexemaToken + "' con '" + tope + "'");
                    posicion++;
                }
            }
        }
        
        // Verificar si quedan elementos en la pila (excepto $)
        while (!pila.isEmpty()) {
            String elemento = pila.pop();
            if (!elemento.equals("$") && !elemento.equals("e")) {
                errores.add("Error: Estructura incompleta - falta procesar: " + elemento);
            }
        }
        
        return errores;
    }
    
    /**
     * Convierte un token léxico a su representación en la tabla sintáctica.
     */
    private String convertirToken(Lexico.Token token) {
        switch (token.getTipo()) {
            case IDENTIFICADOR:
                return "ID";
            case NUMERO:
                return "NUM";
            case CADENA:
                return "STRING";
            case CARACTER:
                return "CHAR";
            case PALABRA_RESERVADA:
                if (token.getLexema().equals("true") || token.getLexema().equals("false")) {
                    return "BOOLEAN";
                }
                return token.getLexema();
            case OPERADOR:
            case DELIMITADOR:
                return token.getLexema();
            default:
            {
            	errores.add("Error de simbolo: El simbolo "+token.getLexema()+" no es valido");
            	return token.getLexema();
            }
                
        }
    }
    
    /**
     * Verifica si un símbolo es terminal.
     */
    private boolean esTerminal(String simbolo) {
    	return mapaTokens.containsKey(simbolo);
    }
    
    /**
     * Verifica si un símbolo es no terminal.
     */
    private boolean esNoTerminal(String simbolo) {
        return mapaNoTerminales.containsKey(simbolo);
    }
    
    /**
     * Obtiene la acción de la tabla sintáctica.
     */
    private String obtenerAccion(String noTerminal, String token) {
        Integer filaIndex = mapaNoTerminales.get(noTerminal);
        Integer columnaIndex = mapaTokens.get(token);
        
        if (filaIndex != null && columnaIndex != null && 
            filaIndex < TABLA_SINTACTICA.length && columnaIndex < TABLA_SINTACTICA[filaIndex].length) {
            return TABLA_SINTACTICA[filaIndex][columnaIndex];
        }
        
        return "SALTAR";
    }
    
    /**
     * Expande una producción en la pila.
     */
    private void expandirProduccion(String produccion) {
        String[] simbolos = produccion.split(" ");
        
        // Insertar símbolos en orden inverso
        for (int i = simbolos.length - 1; i >= 0; i--) {
            if (!simbolos[i].equals("e") && !simbolos[i].trim().isEmpty()) {
                pila.push(simbolos[i]);
            }
        }
    }
    
    /**
     * Formatea los errores para mostrarlos en la interfaz.
     */
    public String formatearErrores() {
        if (errores.isEmpty()) {
            return "Análisis sintáctico completado exitosamente.\nNo se encontraron errores.";
        }
        
        StringBuilder resultado = new StringBuilder();
        resultado.append("ERRORES SINTÁCTICOS ENCONTRADOS:\n");
        resultado.append("=".repeat(40)).append("\n");
        
        for (int i = 0; i < errores.size(); i++) {
            resultado.append((i + 1)).append(". ").append(errores.get(i)).append("\n");
        }
        
        return resultado.toString();
    }
    
    /**
     * Obtiene información de depuración del analizador.
     */
    public String obtenerInformacionDebug() {
        StringBuilder info = new StringBuilder();
        info.append("INFORMACIÓN DEL ANALIZADOR SINTÁCTICO\n");
        info.append("=".repeat(50)).append("\n");
        info.append("Tokens terminales: ").append(TERMINALES.length).append("\n");
        info.append("No terminales: ").append(NO_TERMINALES.length).append("\n");
        info.append("Tamaño de tabla: ").append(TABLA_SINTACTICA.length)
            .append(" x ").append(TABLA_SINTACTICA[0].length).append("\n");
        
        if (!errores.isEmpty()) {
            info.append("Errores encontrados: ").append(errores.size()).append("\n");
        }
        
        return info.toString();
    }
}