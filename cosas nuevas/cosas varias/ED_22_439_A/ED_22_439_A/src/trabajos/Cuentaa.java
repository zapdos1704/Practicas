package trabajos;

import ed.Datos;
import ed.Formato;

public class Cuentaa
{
	private int nc;
	private String nom, fecha;
	private double saldo;
	private Datos obd = new Datos();
	private Formato obf = new Formato();

	public Cuentaa(int nc)
	{
		this.nc = nc;
		System.out.println("Escribe los datos de la cuenta...");
		System.out.println("No. de Cuenta " + this.nc);
		nom = obd.Cadena("Nombre: ").toUpperCase();
		fecha = obd.Cadena("Fecha de apertura: ").toUpperCase();
		do
			saldo = obd.Doble("Saldo inicial: ");
		while (saldo < 1);
	}

	public void Mostrar(double val)
	{
		System.out.println(obf.Izq(nc + "", 10) + " | " + obf.Izq(nom, 25) + " | " + obf.Izq(fecha, 12) + " | "
				+ obf.Izq(obf.Pesos(val), 20));
	}

	public int NoCuenta()
	{
		return nc;
	}

	public String Fecha()
	{
		return fecha;
	}

	public double Saldo()
	{
		return saldo;
	}
}