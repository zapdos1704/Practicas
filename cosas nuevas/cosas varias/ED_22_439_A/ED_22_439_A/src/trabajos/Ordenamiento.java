package trabajos;

import java.util.Random;
import ed.Datos;

public class Ordenamiento
{
	private String arr[], aux;
	private int tam, pos, x;
	private Datos obd = new Datos();

	public Ordenamiento()
	{
		do
			tam = obd.Entero("Tamaño del arreglo");
		while (tam < 1);
		arr = new String[tam];
		this.AleatoriosNumCtrl();
		this.Mostrar();
		System.out.println("\nShellshort");
		this.ShellShort();
		this.Mostrar();
		System.out.println("\nBurbuja");
		this.BurbujaM();
		this.Mostrar();
	}

	private void AleatoriosNumCtrl()
	{
		Random obr = new Random();
		for (pos = 0; pos < arr.length; pos++)
			do
			{
				arr[pos] = ((obr.nextInt(24) + 100) + "").substring(1) + ((obr.nextInt(9999) + 1) + 420000) + "";
				for (x = 0; x < pos && arr[x] != arr[pos]; x++)
					;
			}
			while (x < pos);
	}

	public void ShellShort()
	{
		int sal;
		for (sal = arr.length / 2; sal > 0; sal /= 2)
			for (x = sal; x < arr.length; x++)
				for (pos = x; pos >= sal && arr[pos - sal].compareTo(arr[pos]) < 0; pos -= sal)
				{
					aux = arr[pos - sal];
					arr[pos - sal] = arr[pos];
					arr[pos] = aux;
				}
	}

	public void BurbujaM()
	{
		for (pos = 0; pos < arr.length - 1; pos++)
			for (x = pos + 1; x < arr.length; x++)
				if (arr[pos].compareTo(arr[x]) > 0)
				{
					aux = arr[pos];
					arr[pos] = arr[x];
					arr[x] = aux;
				}
	}

	public void Mostrar()
	{
		for (pos = 0; pos < arr.length; pos++)
		{
			System.out.print(arr[pos] + " | ");
			if ((pos + 1) % 5 == 0)
				System.out.println();
		}
	}
}
