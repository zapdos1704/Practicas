package tema3;

import ed.*;

public class Alumno
{
	private String nc, nom;
	private int pro;
	private Datos obd = new Datos();

	public Alumno()
	{
		System.out.println("Escribe los datos del alumno...");
		nc = obd.Cadena("No. Control").toUpperCase();
		nom = obd.Cadena("Nombre").toUpperCase();
		do
			pro = obd.Entero("Promedio");
		while (pro < 0 || pro > 100);
	}

	public void Mostrar()
	{
		Formato obf = new Formato();
		System.out.println(obf.Izq(nc, 10) + " | " + obf.Izq(nom, 25) + " | " + obf.Der(pro < 70 ? "NA" : pro + "", 5));
	}

	public void Modificar()
	{
		int op;
		Menu obm = new Menu("Modificaciones", new String[] { "Nombre", "Promedio" });
		do
			switch (op = obm.Opcion())
			{
				case 1:
					System.out.println("Nombre actual " + nom);
					nom = obd.Cadena("Escribe el nuevo nombre").toUpperCase();
					break;
				case 2:
					System.out.println("Promedio actual " + pro);
					do
						pro = obd.Entero("Promedio");
					while (pro < 0 || pro > 100);
			}
		while (op != obm.Salir());
	}

	public String NoControl()
	{
		return nc;
	}

	public String Nombre()
	{
		return nom;
	}
}
