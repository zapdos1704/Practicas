package Practicas.Pruebas;

import java.util.ArrayList;
import java.util.List;

public class TablaDeSimbolos {
	private List<String[]> simbolos;
	
	public TablaDeSimbolos() {
		this.simbolos=new ArrayList<>();
	}
	
	 public void agregarSimbolo(String nombre, String componente, 
             String tipo, String valor, String link) {
		 String val[]= {nombre,componente,tipo,valor,link};
		 simbolos.add(val);
	 }
	 
	 public void limpiar() {
		 simbolos.clear();
	 }
}
