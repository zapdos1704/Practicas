package tema1;

public class AlumnoD
{
	private String nc, nom;
	private int pro;
	private ed.Datos obd = new ed.Datos();
	private ed.Formato obf = new ed.Formato();

	public AlumnoD()
	{
		System.out.println("Escribe los datos del alumno:");
		nc = obd.Cadena("No. Control");
		nom = obd.Cadena("Nombre").toUpperCase();
		do
			pro = obd.Entero("Promedio");
		while (pro < 1 || pro > 100);
	}

	public void Mostrar()
	{
		System.out.println(obf.Der(nc, 10) + " | " + obf.Izq(nom, 25) + " | " + obf.Der(pro + "", 4));
	}

	public void Modificar()
	{
		int op;
		ed.Menu obm = new ed.Menu("Modificaciones", new String[] { "No Control", "Nombre", "Promedio" });
		do
			switch (op = obm.Opcion())
			{
				case 1:
					System.out.println("No Control " + nc);
					nc = obd.Cadena("Nuevo No Control");
					break;
				case 2:
					System.out.println("Nombre " + nom);
					nom = obd.Cadena("Nuevo Nombre").toUpperCase();
					break;
				case 3:
					System.out.println("Promedio " + pro);
					do
						pro = obd.Entero("Nuevo Promedio");
					while (pro < 1 || pro > 100);
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

	public int Promedio()
	{
		return pro;
	}
}
