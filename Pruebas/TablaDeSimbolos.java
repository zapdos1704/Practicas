package Practicas.Pruebas;

import java.util.HashMap;

public class TablaDeSimbolos {
	private HashMap<String, String[]> simbolos;

    public TablaDeSimbolos() {
        this.simbolos = new HashMap<>();
    }
    
    public HashMap<String, String[]> agregarSimbolo(String nombre, String componente,
            String tipo, String valor, String link, String noma) {
        
        HashMap<String, String[]> simboloIndividual = new HashMap<>();
        
        // Crear clave única para evitar duplicados
        String clave;
        if(!noma.isEmpty()) {
            clave = noma;
        } else {
            clave = nombre;
        }
        
        String val[] = {nombre, componente, tipo, valor, link, noma.isEmpty() ? "GLOBAL" : noma};
        simboloIndividual.put(clave, val);
        simbolos.put(clave, val); 
        
        return simboloIndividual; 
    }
     
    public void limpiar() {
        simbolos.clear();
    }
    
   
    
}
