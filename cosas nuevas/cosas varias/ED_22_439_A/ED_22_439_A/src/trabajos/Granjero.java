package trabajos;

import ed.Datos;

public class Granjero
{
	public int Meses()
	{
		int mes;
		do
			mes = new Datos().Entero("Cual es la cantidad de meses");
		while (mes < 0);
		return mes;
	}

	public int Conejos(int mes)
	{
		if (mes == 0)
			return 2;
		else
			return 2 * this.Conejos(mes - 1);
	}

	public void Mostrar(int cone)
	{
		System.out.println("Total de conejos: " + cone);
	}
}
