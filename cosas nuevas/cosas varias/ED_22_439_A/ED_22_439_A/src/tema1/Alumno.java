package tema1;

public class Alumno
{
	private String nc, nom;
	private int edad;
	private char sexo;
	private ed.Datos obd = new ed.Datos();
	private ed.Formato obf = new ed.Formato();

	public Alumno()
	{
		nc = obd.Cadena("No. Control");
		nom = obd.Cadena("Nombre").toUpperCase();
		edad = obd.Entero("Edad");
		sexo = obd.Caracter("Sexo (H/M)");
	}

	public void Mostrar()
	{
		System.out.println(obf.Izq(nc, 10) + "\t" + obf.Izq(nom, 25) + "\t" + obf.Der(edad + "", 4) + "\t"
				+ obf.Izq((sexo + "").toUpperCase(), 3));
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
