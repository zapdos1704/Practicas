package Practicas.Pruebas;

import java.util.ArrayList;
import java.util.Arrays;
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
    private String pilaV="";
    
    // Tokens terminales
    private static final String[] TERMINALES = {
        "ID", "NUM", "STRING", "CHAR", "BOOLEAN", "+", "-", "*", "/", "<", ">", "<=", ">=", 
        "==", "!=", "&&", "||", "!", "=", "(", ")", "{", "}", ";", ",", ":", 
        "Program", "for", "endFor", "while", "endWhile", "if", "endif", 
        "else", "method", "endMethod", "return", "int", "float", "double", 
        "string", "boolean", "char","void", "$"
};
    
    // Variables no terminales
    private static final String[] NO_TERMINALES = {
        "P", "D", "D'", "S", "S'", "I", "L", "L'", "E", "EL", "EL'", "ER", "ER'", "T", "T'", "F", "TIPO", "A","EXPR","MULT","MET"
    };
    
    // Tabla sintáctica con acciones implícitas
    private static final String[][] TABLA_SINTACTICA = {
    	    //                ID                    NUM             STRING          CHAR            BOOLEAN         +               -               *               /               <               >               <=              >=              ==              !=              &&              ||              !               =               (               )               {               }               ;               ,               :               Program                 		         for                       		endFor          while           			endWhile        if              	endif           else            method          					endMethod       return             int             		float           	double          		string          		boolean         		char            void         	   $
    		/*P    */{	"SALTAR", 				"SALTAR", 		   "SALTAR", 	   "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR",       "SALTAR",       "SALTAR",        "SALTAR",       "SALTAR",       "SALTAR",       "SALTAR",       "SALTAR",        "SALTAR",     "SALTAR",        "SALTAR",       "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR",       "SALTAR",        "SALTAR",       "SALTAR",   "Program ID : MET { D S } ","SALTAR",        "SALTAR",          			  "SALTAR",       "SALTAR",    				"SALTAR",    "SALTAR",            "SALTAR",       "SALTAR",        "SALTAR",      						"SALTAR",      "SALTAR",          "SALTAR",        	    "SALTAR",      	      "SALTAR",        	       "SALTAR",       		    "SALTAR",           "SALTAR",		        "SALTAR",       "SACAR"},
    	    
    	    /*D    */{  "SALTAR",               "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		"SACAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  		  "SALTAR", 				  	  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SACAR", 		      "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "TIPO ID MULT ; D'",  "TIPO ID = E ; D'",   "TIPO ID = E ; D'", 	   "TIPO ID = E ; D'", 		"TIPO ID = E ; D'", "TIPO ID = E ; D'", 	"SALTAR",		"SACAR"},
    	       		 
    	    /*D'   */{  "SALTAR",               "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		"SACAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  		  "SACAR", 				  	  	  "SALTAR", 	  "SACAR", 					"SALTAR", 	 "SACAR",             "SALTAR", 	  "SALTAR", 	   "SALTAR", 	    					"SALTAR", 	   "SALTAR", 		  "TIPO ID = E ; D'", 	 "TIPO ID = E ; D'",   "TIPO ID = E ; D'", 	   "TIPO ID = E ; D'", 		"TIPO ID = E ; D'", "TIPO ID = E ; D'", 	"SALTAR",		"SALTAR"},
    	    
    	    /*S    */{ "ID A ; S'",             "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "{ S } S'", 	 "SALTAR", 		"SACAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  		  "for ID : ( EXPR ) I endFor S'",   "SALTAR", 	  "while EXPR I endWhile S'", "SALTAR",  "if EXPR I endif S'",   "SALTAR", 	  "SALTAR", 	   "SALTAR", 	    					"e", 	   	   "return E ; S'",   "TIPO ID MULT ; S'",  "TIPO ID MULT ; S'",   "TIPO ID MULT ; S'",   "TIPO ID MULT ; S'", 	    "TIPO ID MULT ; S'", "TIPO ID MULT ; S'", 	"SALTAR",		"SACAR"},

    	    /*S'   */{"ID A ; S'",              "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "{ S } S'", 	 "SACAR", 		"e", 	 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  		  "for ID : ( EXPR ) I endFor S'",   "e", 	  	  "while EXPR I endWhile S'",  "e",	     "if EXPR I endif S'",   "e", 	  	  "SALTAR", 	   "SALTAR", 	    					"e", 	       "return E ; S'",   "TIPO ID MULT ; S'",  "TIPO ID MULT ; S'",   "TIPO ID MULT ; S'",   "TIPO ID MULT ; S'", 	    "TIPO ID MULT ; S'", "TIPO ID MULT ; S'", 	"SALTAR",		"SALTAR"},
    	       
    	    /*I    */{"ID A ;",                 "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "{ S }", 		 "SACAR", 		"SALTAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  		  "for ID : ( EXPR ) I endFor",      "SALTAR", 	  "while EXPR I endWhile", 	"SALTAR", 	 "if EXPR I endif",      "e", 	  	  "SALTAR", 	   "SALTAR", 		    				"SALTAR", 	   "return E ;", 	  "TIPO ID MULT ; ", 	 "TIPO ID MULT ; ",   	"TIPO ID MULT ; ", 	   "TIPO ID MULT ; ", 		"TIPO ID MULT ; ", 	"TIPO ID MULT ; ", 	    "SALTAR",		"SALTAR"},
    	    
    	    /*L    */{"SALTAR",                 "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "SALTAR", 		 "( L )", 		 "SACAR", 		 "SALTAR", 		"SALTAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  		   "SALTAR", 				  	  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SALTAR",   		  "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "TIPO ID L'", 	     "TIPO ID L'",   	   "TIPO ID L'", 		   "TIPO ID L'", 			"TIPO ID L'", 		"TIPO ID L'", 			"SALTAR",		"SALTAR"},
    	       		 
    	    /*L'   */{"SALTAR",                 "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		"SALTAR", 		"SALTAR", 		", TIPO ID L",  "SALTAR", 			"SALTAR", 					  		   "SALTAR", 				  	  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SALTAR",   		  "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  ", TIPO ID L", 		", TIPO ID L",   	   ", TIPO ID L", 		   ", TIPO ID L", 			", TIPO ID L", 	    ", TIPO ID L", 			"SALTAR",		"SALTAR"},
    	    		 
    	    /*E    */{"T EL",                   "T EL",             "T EL",        "T EL",          "T EL",      "+ E",          "- E",       	 "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"! EL", 		 "SALTAR", 		 "( E ) T'", 	 "SALTAR", 		 "SALTAR", 		"SALTAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  		   "SALTAR", 				  	  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SALTAR",   		  "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR",		"SALTAR"},
    	      		
    	    /*EL   */{"ER EL'",                 "ER EL'",           "ER EL'",      "ER EL'",        "ER EL'",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"ER EL'", 		 "SALTAR", 		 "ER EL'", 		 "e", 		 	 "SALTAR", 		"SALTAR", 		"SACAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  		   "SALTAR", 				  	  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SALTAR",   		  "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "SALTAR", 			 "SALTAR",  		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR",		"SALTAR"},
    	                                    
    	    /*EL'  */{"SALTAR",                 "SALTAR",           "SALTAR",      "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "&& ER EL'",     "|| ER EL'",  "SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "e", 		 	 "SALTAR", 		"SALTAR", 		"SALTAR", 		"e", 		 	 "SALTAR", 			"SALTAR", 					  		   "SALTAR", 				  	  "e", 	  		  "SALTAR", 				"e", 	 	 "SALTAR",   		  "e", 	  		  "SALTAR", 	   "SALTAR", 							"SALTAR",	    "e",     		  "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR",		"SALTAR"},
		
    	    /*ER   */{"ER'",                  	"ER'",            	"ER'",       	"ER'",          "ER'",       "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"T ER'",	     "SALTAR", 		 "T ER'", 		 "SALTAR", 		 "SALTAR", 		"SALTAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  		   "SALTAR", 				  	  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SALTAR",   		  "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR",		"SALTAR"},
    	    
    	    /*ER'  */{"e",                 		"SALTAR",           "SALTAR",      "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"< T ER'",      "> T ER'",       "<= T ER'",     ">= T ER'",     "== T ER'",     "!= T ER'",     "e",      		  "e", 			"SALTAR", 		 "e", 		     "SALTAR", 		 "e", 		 	 "SALTAR", 		"SALTAR", 		"SALTAR", 		"e", 		 	 "SALTAR", 			"SALTAR", 					  		   "SALTAR", 				  	  "e", 	  		  "SALTAR", 				"e", 	 	 "SALTAR",   		  "e", 	  		  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "e", 	 		  "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR",		"SALTAR"},
    	    	   
    	    /*T    */{"F T'",                   "F T'",              "F T'",       "F T'",          "F T'",      "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"F T'", 		 "SALTAR", 		 "F T'", 		 "SALTAR", 		 "SALTAR", 		"SALTAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  		  "SALTAR", 				  	  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SALTAR",   		  "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR",		"SALTAR"},
    	              	 
    	    /*T'   */{"SALTAR",                 "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "+ F T'",       "- F T'",       "* F T'",      "/ F T'", 		"e",               "e",          "e",      		 "e",        	 "e",      		 "e",      		 "e",             "e", 			"SALTAR", 		 "SALTAR", 		 "( E )",		 "e", 		 	 "SALTAR", 		"SALTAR", 		"SACAR", 		"e", 		 	 "SALTAR",			"SALTAR", 					  		   "SALTAR", 				  	  "e", 	  		  "SALTAR",				    "e", 	     "SALTAR",   		  "e", 	  		  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "e", 	 		  "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR",		"SALTAR"},
    	    		 
    	    /*F    */{"ID",                	    "NUM",             "STRING",       "CHAR",          "BOOLEAN",   "+ F",       	 "- F",       	 "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "SALTAR", 		 "( E )", 		 "SALTAR", 		 "SALTAR", 		"SALTAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  		   "SALTAR", 				  	  "SALTAR", 	  "SALTAR", 				"SALTAR",    "SALTAR",   		  "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR",		"SALTAR"},
    	           		 	  
    	    /*TIPO */{"SALTAR",                 "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		"SALTAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  		   "SALTAR", 				  	  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SALTAR",   		  "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "int", 			 	 "float",    		   "double", 			   "string", 				"boolean", 			"char", 				"void",			"SALTAR"},
    	    		 
    	    /*A    */{"SALTAR",                 "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "= E", 		 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		"SALTAR", 		"e", 			"e", 		 	 "SALTAR", 			"SALTAR", 					  		   "SALTAR", 				  	  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SALTAR",   		  "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR",		"SALTAR"},
    	    
    	    /*EXPR*/ {"T ER'",                  "T ER'",            "T ER'",       "T ER'",         "T ER'",     "+ T ER'",      "- T ER'",      "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"T ER'", 		 "SALTAR", 		 "( EXPR )",     "SALTAR", 		 "SALTAR", 		"SALTAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  		   "SALTAR", 				  	  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SALTAR",   		  "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "SALTAR", 			 "SALTAR",   		   "SALTAR", 			   "SALTAR", 				"SALTAR", 			"SALTAR", 				"SALTAR",		"SALTAR"},
    	    
    	    /*MULT*/ {"SALTAR",                 "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "= E", 		 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		"SACAR", 		"e", 			"ID MULT", 		 "SALTAR", 			"SALTAR", 					  		   "SALTAR", 				  	  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SACAR", 		      "SALTAR", 	  "SALTAR", 	   "SALTAR", 							"SALTAR", 	   "SALTAR", 		  "SALTAR", 	 		 "SALTAR",   		   "SALTAR", 	   		   "SALTAR", 		        "SALTAR", 			"SALTAR", 				"SALTAR",		"SACAR"},
    	    
    	    /*MET */ {"SALTAR",                 "SALTAR",          "SALTAR",       "SALTAR",        "SALTAR",    "SALTAR",       "SALTAR",       "SALTAR",      "SALTAR", 		"SALTAR", 		"SALTAR",        "SALTAR", 		 "SALTAR",   	 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		  "SALTAR", 	"SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "SALTAR", 		 "SACAR", 		"SALTAR", 		"SALTAR", 		"SALTAR", 		 "SALTAR", 			"SALTAR", 					  		   "SALTAR", 				  	  "SALTAR", 	  "SALTAR", 				"SALTAR", 	 "SALTAR", 		      "SALTAR", 	  "SALTAR", 	   "method TIPO ID L S endMethod MET", 	"e", 	       "SALTAR", 		  "SALTAR", 	 		 "SALTAR",   		   "SALTAR", 	   		   "SALTAR", 		        "SALTAR", 			"SALTAR", 				"SALTAR",		"SACAR"}

    	};
    
    public Sintactico() {
        this.errores = new ArrayList<>();
        this.pila = new Stack<>();
        inicializarMapas();
    }
    
    public String getPila() {
    	return pilaV;
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
    
    private boolean esOperador(String lexema) {
        return lexema.equals("+") || lexema.equals("-") || lexema.equals("*") || lexema.equals("/");
    }
    
    /**
     * Analiza sintácticamente una lista de tokens usando la tabla LL(1).
     */
    public List<String> analizar(List<Lexico.Token> tokens) {
        this.tokens = tokens;
        this.posicion = 0;
        this.errores.clear();
        this.pila.clear();
        int lineaAnt=0;
        // Agregar símbolo de fin y símbolo inicial
        pila.push("$");
        pila.push("P");
        pilaV="";
        
        // Agregar token de fin si no existe
        if (tokens.isEmpty() || !tokens.get(tokens.size() - 1).getLexema().equals("$")) {
            tokens.add(new Lexico.Token(Lexico.TipoToken.DELIMITADOR, "$", 
                       tokens.isEmpty() ? 1 : tokens.get(tokens.size() - 1).getLinea()));
        }
        
        while (!pila.isEmpty() && posicion < tokens.size()) {
            Lexico.Token tokenActual = tokens.get(posicion);
            String lexemaToken = convertirToken(tokenActual);
            pilaV=pilaV+"\n"+pila+" | "+lexemaToken;
            String tope = pila.pop();
            
            
            if (posicion + 1 < tokens.size() && esOperador(lexemaToken) && esOperador(convertirToken(tokens.get(posicion + 1)))) {
            	if(!convertirToken(tokens.get(posicion + 2)).equals(";")) {
            		if (tokenActual.getLinea() != lineaAnt) {
                        lineaAnt = tokenActual.getLinea();
                        errores.add("Error en línea " + tokenActual.getLinea() + 
                                    ": Secuencia inválida de operadores '" + lexemaToken + " " + convertirToken(tokens.get(posicion + 1)) + "'");
                    }
                    posicion++;
            	}else
            		posicion=posicion+2;
                continue;
            }
            
            // Si el tope es un terminal
            if (esTerminal(tope)) {
                if (tope.equals(lexemaToken)) {
                    posicion++; // Coincidir y avanzar
                } else {
                	if(tokenActual.getLinea()!=lineaAnt)
                	{
                		lineaAnt=tokenActual.getLinea();
                		errores.add("Error en línea " + tokenActual.getLinea() + 
                                ": Se esperaba '" + tope + "' pero se encontró '" + lexemaToken + "'");
                	}
                    
                    posicion++; // Intentar recuperarse
                }
            } 
            // Si el tope es un no terminal
            else if (esNoTerminal(tope)) {
                String accion = obtenerAccion(tope, lexemaToken);
                
                if (accion.equals("SALTAR")) {
                    // Saltar token actual, mantener no terminal en pila
                	if(tokenActual.getLinea()!=lineaAnt)
                	{
                		lineaAnt=tokenActual.getLinea();
                		errores.add("Error en línea " + tokenActual.getLinea() + 
                                ": Se esperaba '" + tope + "' pero se encontró '" + lexemaToken + "'");
                	}
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
                	if(tokenActual.getLinea()!=lineaAnt)
                	{
                		lineaAnt=tokenActual.getLinea();
                		errores.add("Error en línea " + tokenActual.getLinea() + 
                               ": No se puede procesar '" + lexemaToken + "' con '" + tope + "'");
                	}
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
            case ID:
                return "ID";
            case NUM:
                return "NUM";
            case CADENA:
                return "STRING";
            case CARACTER:
                return "CHAR";
            case PR:
                if (token.getLexema().equals("true") || token.getLexema().equals("false")) {
                    return "BOOLEAN";
                }
                return token.getLexema();
            case OPERADOR:
            case DELIMITADOR:
                return token.getLexema();
            default:
            {
            		errores.add("Error lexico en linea "+token.getLinea() +": El simbolo "+token.getLexema()+" no es valido");
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
    
    
    public String obtenerTablaSimbolosFormateada(List<Lexico.Token> tokens, TablaDeSimbolos tablaSimbolos) {
        this.tokens = tokens;
        List<HashMap<String, String[]>> lista = new ArrayList<>();
        Stack<String> pilan = new Stack<>();
        int s = 0, w = 0, f = 0, iff = 0;
        String lex, nom, noma = "", val = "";

        int x = 0;
        while (x < tokens.size()) {
            lex = tokens.get(x).getLexema();
            
            nom = "";
            if (lex.equals("method")) {
                if (x + 2 < tokens.size() && tokens.get(x + 2).getTipo().toString().equals("ID")) {
                    nom = tokens.get(x + 2).getLexema();
                }
            } else {
                if (x + 1 < tokens.size() && tokens.get(x + 1).getTipo().toString().equals("ID")) {
                    nom = tokens.get(x + 1).getLexema();
                }
            }
            
            switch (lex) {
                case "Program":
                    lista.add(tablaSimbolos.agregarSimbolo(nom, lex, "", "", "", ""));
                    x++;
                    pilan.push(nom);
                    break;
                    
                case "method":
                    String tipoRetorno = "";
                    if (x + 1 < tokens.size()) {
                        tipoRetorno = tokens.get(x + 1).getLexema();
                    }
                    
                    String linkMethod = !pilan.isEmpty() ? pilan.peek() : "";
                    lista.add(tablaSimbolos.agregarSimbolo(nom, lex, tipoRetorno, "", nom, ""));
                    pilan.push(nom);
                    noma = pilan.peek();
                    x = x + 2;
                    break;
                    
                case "for":
                    f++;
                    s = f;
                case "while":
                    w++;
                    s = w;
                case "if":
                	if(lex.equals("if")) {
                		iff++;
                        s = iff;
                	}
                    
                    lista.add(tablaSimbolos.agregarSimbolo(lex + s, lex, "", "", lex + s, noma));
                    pilan.push(lex + s);
                    noma = pilan.peek();
                    x++;
                    break;
                    
                case "int":
                case "float":
                case "double":
                case "char":
                case "string":
                case "void":
                case "boolean":
                    val = "";
                    int y = x + 3;
                    if (x + 2 < tokens.size() && 
                        !(tokens.get(x + 2).getLexema()).equals(",") && 
                        !(tokens.get(x + 2).getLexema()).equals(")")) {
                        while (y < tokens.size() && !tokens.get(y).getLexema().equals(";")) {
                            val = val + tokens.get(y).getLexema();
                            y++;
                        }
                        lista.add(tablaSimbolos.agregarSimbolo(nom, "ID", lex, val, "", noma));
                        x = y;
                    } else {
                        lista.add(tablaSimbolos.agregarSimbolo(nom, "ID", lex, "", "", noma));
                        x++;
                    }
                    break;
                    
                case "{":
                    if (!pilan.isEmpty()) {
                        pilan.pop();
                        noma = "";
                    }
                    x++;
                    break;
                    
                case "endif":
                case "endMethod":
                case "endFor":
                case "endWhile":
                    if (!pilan.isEmpty()) {
                        pilan.pop();
                        noma = !pilan.isEmpty() ? pilan.peek() : "";
                    }
                    x++;
                    break;
                    
                default:
                    x++;
            }
        }
        
        Map<String, List<String[]>> simbolosPorAmbito = new HashMap<>();
        
        for (HashMap<String, String[]> simbolo : lista) {
            for (String key : simbolo.keySet()) {
                String[] valores = simbolo.get(key);
                String ambito = valores[5].isEmpty() ? "GLOBAL" : valores[5];
                
                if (!simbolosPorAmbito.containsKey(ambito)) {
                    simbolosPorAmbito.put(ambito, new ArrayList<>());
                }
                simbolosPorAmbito.get(ambito).add(valores);
            }
        }
        
        StringBuilder resultado = new StringBuilder();
        resultado.append("🗂️ TABLA DE SÍMBOLOS\n");
        resultado.append("═".repeat(100)).append("\n\n");
        
        if (simbolosPorAmbito.isEmpty()) {
            resultado.append("⚠️  No se encontraron símbolos en el código fuente.\n");
            resultado.append("Asegúrate de que el código contenga declaraciones válidas.\n");
        } else {
            
            if (simbolosPorAmbito.containsKey("GLOBAL")) {
                mostrarTablaAmbito("GLOBAL", simbolosPorAmbito.get("GLOBAL"), resultado);
                simbolosPorAmbito.remove("GLOBAL");
            }
            
            for (Map.Entry<String, List<String[]>> entry : simbolosPorAmbito.entrySet()) {
                mostrarTablaAmbito(entry.getKey(), entry.getValue(), resultado);
            }
            
            resultado.append("\n").append("═".repeat(100)).append("\n");
            resultado.append("📊 TABLA GENERAL - RESUMEN DE TODOS LOS SÍMBOLOS\n");
            resultado.append("═".repeat(100)).append("\n\n");
            
            resultado.append(String.format("%-4s | %-20s | %-15s | %-12s | %-20s | %-15s\n", 
                             "#", "Nombre", "Componente", "Tipo", "Valor", "Link"));
            resultado.append("-".repeat(100)).append("\n");
            
            int contador = 1;
            for (HashMap<String, String[]> simbolo : lista) {
                for (String key : simbolo.keySet()) {
                    String[] valores = simbolo.get(key);
                    resultado.append(String.format("%-4d | %-20s | %-15s | %-12s | %-20s | %-15s\n", 
                                     contador++, 
                                     valores[0].isEmpty() ? "-" : valores[0],
                                     valores[1].isEmpty() ? "-" : valores[1],
                                     valores[2].isEmpty() ? "-" : valores[2],
                                     valores[3].isEmpty() ? "-" : valores[3],
                                     valores[4].isEmpty() ? "-" : valores[4]));
                }
            }
            
            resultado.append("\n").append("═".repeat(100)).append("\n");
            resultado.append("✅ Análisis de tabla de símbolos completado exitosamente.");
        }
        return resultado.toString();
    }

    /**
     * Método auxiliar para mostrar una tabla de un ámbito específico
     */
    private void mostrarTablaAmbito(String nombreAmbito, List<String[]> simbolosAmbito, StringBuilder resultado) {
        resultado.append("\n🔍 ").append(nombreAmbito.toUpperCase()).append("\n");
        resultado.append("-".repeat(100)).append("\n");
        
        if (simbolosAmbito.isEmpty()) {
            resultado.append("   (Sin símbolos en este componente)\n\n");
            return;
        }
        
        resultado.append(String.format("%-4s | %-20s | %-15s | %-12s | %-20s | %-15s\n", 
                         "#", "Nombre", "Componente", "Tipo", "Valor", "Link"));
        resultado.append("-".repeat(100)).append("\n");
        
        for (int i = 0; i < simbolosAmbito.size(); i++) {
            String[] valores = simbolosAmbito.get(i);
            resultado.append(String.format("%-4d | %-20s | %-15s | %-12s | %-20s | %-15s\n", 
                             i + 1,
                             valores[0].isEmpty() ? "-" : valores[0], // nombre
                             valores[1].isEmpty() ? "-" : valores[1], // componente
                             valores[2].isEmpty() ? "-" : valores[2], // tipo
                             valores[3].isEmpty() ? "-" : valores[3], // valor
                             valores[4].isEmpty() ? "-" : valores[4]  // link
                            ));
        }
    }
}