package trabajos;

public class Empleados
{
	private int clv, hrs;
	private String nom;
	private double ph, sueb, ret, suet;
	private ed.Datos obd = new ed.Datos();
	private ed.Formato obf = new ed.Formato();

	public Empleados(int clv)
	{
		this.clv = clv;
		System.out.println("Clave: " + this.clv);
		nom = obd.Cadena("Nombre completo").toUpperCase();
		do
			hrs = obd.Entero("Horas trabajadas a la semana (8 a 45)");
		while (hrs < 8 || hrs > 45);
		do
			ph = obd.Doble("Precio por hora");
		while (ph < 1);
		sueb = ph * hrs;
		ret = sueb * .17;
		suet = sueb - ret;
	}

	public void Mostrar()
	{
		System.out.println(obf.Izq(clv + "", 5) + " | " + obf.Izq(nom, 25) + " | " + obf.Der(obf.Pesos(sueb), 15) + " | "
				+ obf.Der(obf.Pesos(ret), 13) + " | " + obf.Der(obf.Pesos(suet), 15));
	}

	public String Nombre()
	{
		return nom;
	}
}
