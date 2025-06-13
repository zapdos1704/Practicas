package ed1;

import java.text.NumberFormat;

public class Formato
{
	private int c;

	public String Pesos(double can)
	{
		return NumberFormat.getCurrencyInstance().format(can);
	}

	public String Izq(String dato, int tam)
	{
		if (dato.length() < tam)
		{
			for (c = dato.length(); c < tam; c++)
				dato += " ";
			return dato;
		}
		else
			return dato.substring(0, tam);
	}

	public String Der(String dato, int tam)
	{
		if (dato.length() < tam)
		{
			for (c = dato.length(); c < tam; c++)
				dato = " " + dato;
			return dato;
		}
		else
			return dato.substring(0, tam);
	}
}
