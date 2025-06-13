package tema2;

import ed.Datos;
import ed.Formato;

public class Banco
{
	private Datos obd = new Datos();

	public int Mes()
	{
		int num;
		num = obd.Entero("Meses a inversion");
		if (num < 0)
			num = this.Mes();
		return num;
	}

	public double Por()
	{
		double num;
		num = obd.Doble("Porcentaje de ganancia");
		if (num < 1 || num > 100)
			num = this.Por();
		num = 1 + num / 100;
		return num;
	}

	public double Dinero()
	{
		double din;
		din = obd.Doble("Monto a depositar");
		if (din < 1)
			din = this.Dinero();
		return din;
	}

	public double Inversion(int mes, double por, double inv)
	{
		if (mes == 0)
			return inv;
		else
			return por * this.Inversion(mes - 1, por, inv);
	}

	public void Mostrar(double invet)
	{
		System.out.println("Inversion total: " + new Formato().Pesos(invet));
	}
}
