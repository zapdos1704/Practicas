package tema2;

import ed.Datos;

public class Potencias
{
	private Datos obd = new Datos();

	public int Numeros()
	{
		return obd.Entero("Cual es el numero");
	}

	public int Poten()
	{
		int num;
		do
			num = new Datos().Entero("Cual es la potencia");
		while (num < 0);
		return num;
	}

	public int Potencia(int num, int pot)
	{
		if (pot == 0)
			return 1;
		else
			return num * this.Potencia(num, pot - 1);
	}

	public void Mostrar(int res)
	{
		System.out.println("Resultado " + res);
	}
}
