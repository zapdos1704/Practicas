package trabajos;

import ed.Datos;
import ed.Formato;

public class Articulo
{
	private int clv, exi;
	private String nom;
	private double precio;
	private Datos obd = new Datos();

	public Articulo(int clv)
	{
		System.out.println("Datos del articulo...");
		this.clv = clv;
		System.out.println("Clave: " + this.clv);
		nom = obd.Cadena("Nombre").toUpperCase();
		do
			exi = obd.Entero("Existencia");
		while (exi < 1);
		do
			precio = obd.Entero("Precio");
		while (precio < 1);
	}

	public Articulo()
	{
		do
			clv = obd.Entero("Clave a buscar");
		while (clv < 1);
	}

	public void Mostrar()
	{
		Formato obf = new Formato();
		System.out.println(
				obf.Izq(clv + "", 5) + obf.Izq(nom, 25) + obf.Der(exi + "", 7) + obf.Der(obf.Pesos(precio), 12));
	}

	public int Clave()
	{
		return clv;
	}
}
