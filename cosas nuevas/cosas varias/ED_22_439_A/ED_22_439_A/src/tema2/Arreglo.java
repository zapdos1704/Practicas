package tema2;

import ed.Datos;

public class Arreglo
{
	private int vec[];
	private java.util.Random obr = new java.util.Random();

	public int Vec()
	{
		int can;
		do
			can = new Datos().Entero("Tamaño del arreglo");
		while (can < 1);
		vec = new int[can];
		return can;
	}

	public void Aleatorios(int pos)
	{
		if (pos != 1)
			this.Aleatorios(pos - 1);
		vec[pos - 1] = obr.nextInt(100);
	}

	public void Mostrar(int pos)
	{
		if (pos != 1)
			this.Mostrar(pos - 1);
		System.out.println(pos + " [" + vec[pos - 1] + "] ");
	}
}
