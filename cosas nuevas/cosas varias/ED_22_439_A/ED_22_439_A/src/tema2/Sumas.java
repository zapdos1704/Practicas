package tema2;

import ed.Datos;

public class Sumas
{
	public int Numero()
	{
		int num;
		do
			num = new Datos().Entero("Número final");
		while (num < 1);
		return num;
	}

	public int Suma(int num)
	{
		if (num == 1)
			return 1;
		else
			return num + this.Suma(num - 1);
	}

	public void Mostrar(int res)
	{
		System.out.println("La suma es " + res);
	}
}
