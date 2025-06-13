package tema4;

import ed.*;

public class Trabajador
{
	private int nt;
	private String nom;
	private double sue;
	private Datos obd = new Datos();
	private Formato obf = new Formato();
	private Menu obm = new Menu("Modificaciones", new String[] { "Nombre", "Sueldo" });

	public Trabajador(int nt)
	{
		this.nt = nt;
		System.out.println("Escribe los datos del trabajador");
		System.out.println("No. de Tarjeta " + this.nt);
		nom = obd.Cadena("Nombre").toUpperCase();
		do
			sue = obd.Doble("Sueldo");
		while (sue < 1);
	}

	public Trabajador(String nom)
	{
		this.nom = nom.toUpperCase();
	}

	public void Mostrar()
	{
		System.out.println(obf.Izq(nt + "", 5) + " - " + obf.Izq(nom, 25) + " - " + obf.Der(obf.Pesos(sue), 12));
	}

	public void Modificar()
	{
		int op;
		do
			switch (op = obm.Opcion())
			{
				case 1:
					System.out.println("Noombre actual: " + nom);
					nom = obd.Cadena("Nuevo nombre");
					break;
				case 2:
					System.out.println("Sueldo actual: " + obf.Pesos(sue));
					do
						sue = obd.Doble("Nuevo sueldo");
					while (sue < 1);
			}
		while (op != obm.Salir());
	}

	public int NoTarjeta()
	{
		return nt;
	}

	public String Nombre()
	{
		return nom;
	}

	public double Sueldo()
	{
		return sue;
	}
}
