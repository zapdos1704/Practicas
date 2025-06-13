package tema3;

import ed.Datos;
import ed.Formato;
import ed.Menu;

public class Producto
{
	private int clv;
	private double precio;
	private String nom;

	public Producto(int clv)
	{
		Datos obd = new Datos();
		this.clv = clv;
		System.out.println("Escriba los datos del producto...");
		System.out.println("Clave " + this.clv);
		nom = obd.Cadena("Nombre del producto");
		do
			precio = obd.Doble("Precio del producto");
		while (precio < 1);
	}

	public void Mostrar()
	{
		Formato obf = new Formato();
		System.out.println(obf.Izq(clv + "", 5) + " | " + obf.Izq(nom, 25) + " | " + obf.Der(obf.Pesos(precio), 12));
	}

	public void Modificar()
	{
		int op;
		Menu obm = new Menu("Modificaciones", new String[] { "Nombre", "Precio" });
		do
			switch (op = obm.Opcion())
			{
				case 1:
					System.out.println("Nombre actual " + nom);
					nom = obm.obd.Cadena("Nuevo nombre");
					break;
				case 2:
					System.out.println("Precio actual " + precio);
					do
						precio = obm.obd.Doble("Nuevo precio del producto");
					while (precio < 1);
			}
		while (op != obm.Salir());
	}

	public int Clave()
	{
		return clv;
	}
}
