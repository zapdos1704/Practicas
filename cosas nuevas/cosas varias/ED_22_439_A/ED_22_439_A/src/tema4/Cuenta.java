package tema4;

import ed.*;

public class Cuenta
{
	private int nc;
	private String nom;
	private double sal;
	private Datos obd = new Datos();

	public Cuenta(String msj)
	{
		do
			nc = obd.Entero("Cuenta a " + msj);
		while (nc < 1);
	}

	public Cuenta(int nc)
	{
		this.nc = nc;
		System.out.println("Datos de la cuenta...");
		System.out.println("Numero de cuenta: " + nc);
		nom = obd.Cadena("Nombre");
		do
			sal = obd.Doble("Saldo");
		while (sal < 1);
	}

	public void MostrarT()
	{
		Formato obf = new Formato();
		System.out.println(obf.Izq(nc + "", 5) + obf.Izq(nom, 25) + obf.Der(obf.Pesos(sal), 12));
	}

	public void Mostrar()
	{
		Formato obf = new Formato();
		System.out.println(obf.Izq(nom, 25) + obf.Der(obf.Pesos(sal), 12));
	}

	public int NoCuenta()
	{
		return nc;
	}

	public void Movimiento(double mon)
	{
		sal += mon;
	}

	public double Saldo()
	{
		return sal;
	}
}
