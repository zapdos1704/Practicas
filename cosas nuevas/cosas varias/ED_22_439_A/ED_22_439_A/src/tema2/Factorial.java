package tema2;

import ed.Datos;

public class Factorial
{
	public int Numero()
	{
		int num;
		num = new Datos().Entero("Cual es el numero");
		if (num < 0)
			num = this.Numero();
		return num;
	}

	public int Calcular(int num)
	{
		if (num < 2)
			return 1;
		else
			return num * this.Calcular(num - 1);
	}

	public void Mostrar(int fac)
	{
		System.out.println("Resultado: " + fac);
	}
}
