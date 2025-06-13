package trabajos;

import ed.*;

public class Cuenta
{
	private int clv, dia, mes, año;
	private String nom, fecha;
	private double saldo;
	private Datos obd = new Datos();
	private Formato obf = new Formato();

	public Cuenta(int clv)
	{
		this.clv = clv;
		System.out.println("Datos de la cuenta...");
		System.out.println("Cuenta: " + this.clv);
		nom = obd.Cadena("Nombre del cliente").toUpperCase();
		System.out.println("Fecha de creacion de la cuenta...");
		fecha = this.Fec();
		do
			saldo = obd.Doble("Deposito inicial a la cuenta " + this.clv);
		while (saldo < 1);
	}

	public int Clave()
	{
		return clv;
	}

	public double Saldo()
	{
		return saldo;
	}

	public void Saldo(double sal)
	{
		saldo = sal;
	}

	public void Mostrar()
	{
		System.out.println(obf.Izq(clv + "", 5) + " | " + obf.Izq(nom, 25) + " | " + obf.Izq(fecha, 20) + " | "
				+ obf.Izq(obf.Pesos(saldo), 13));
	}

	public String Fec()
	{
		do
			dia = obd.Entero("DD");
		while (dia < 1 || dia > 31);
		do
			mes = obd.Entero("MM");
		while (mes < 1 || mes > 12);
		do
			año = obd.Entero("AAAA");
		while (año < 1900 || año > 2024);
		return fecha = (dia < 10 ? "0" + dia : dia) + "-" + (mes < 10 ? "0" + mes : mes) + "-" + (año + "");
	}

	public String Fecha()
	{
		return fecha;
	}
}
