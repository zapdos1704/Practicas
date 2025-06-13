package tema2;

import ed.Datos;

public class Productos
{
	public int Numeros(String msj)
	{
		int num;
		do
			num = new Datos().Entero(msj);
		while (num < 0);
		return num;
	}

	public int Multiplicacion(int num1, int num2)
	{
		if (num1 == 0)
			return 0;
		else
			return num2 + this.Multiplicacion(num1 - 1, num2);
	}

	public void Mostrar(int res)
	{
		System.out.println("La multiplicacion es: " + res);
	}
}
