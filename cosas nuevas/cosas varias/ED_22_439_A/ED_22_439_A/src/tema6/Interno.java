package tema6;

import ed.Datos;
import java.util.Random;
import java.util.Arrays;

public class Interno
{
	private int arr[], tam, pos, x;
	private Datos obd = new Datos();

	public Interno()
	{
		do
			tam = obd.Entero("Tamaño del arreglo");
		while (tam < 1);
		arr = new int[tam];
		this.Aleatorios();
		System.out.println("\nArreglo original");
		this.Mostrar(arr);
		this.Java();
		System.out.println("\nArreglo ordenado");
		this.Mostrar(arr);
		this.Binaria();
	}

	public void Aleatorios()
	{
		Random obr = new Random();
		for (pos = 0; pos < arr.length; pos++)
			do
			{
				arr[pos] = obr.nextInt(arr.length * 10) + 1;
				for (x = 0; x < pos && arr[x] != arr[pos]; x++)
					;
			}
			while (x < pos);
	}

	public void Mostrar(int vec[])
	{
		for (pos = 0; pos < arr.length; pos++)
		{
			System.out.print("[" + arr[pos] + "]" + "\t");
			if ((pos + 1) % 5 == 0)
				System.out.println();
		}
	}

	public void Java()
	{
		Arrays.sort(arr);
	}

	public void Secuencial()
	{
		int num;
		do
			num = obd.Entero("Numero a buscar");
		while (num < 1);
		for (pos = 0; pos < arr.length && arr[pos] != num; pos++)
			;
		if (pos != arr.length)
			System.out.println("Encontrado en la posición " + (pos + 1) + "...");
		else
			System.out.println("El numero no esta en el arreglo...");

	}

	public void Binaria()
	{
		int num, pos;
		do
			num = obd.Entero("\nCual es el numero a buscar");
		while (num < 1);
		pos = this.Binaria(0, arr.length - 1, num);
		if (arr[pos] == num)
			System.out.println("Encontrado en la posición " + (pos + 1) + "...");
		else
			System.out.println("El numero no esta en el arreglo...");
	}

	private int Binaria(int ini, int fin, int num)
	{
		int cen = (ini + fin) / 2;
		if (arr[cen] != num && ini <= fin)
			if (num > arr[cen])
				cen = this.Binaria(cen + 1, fin, num);
			else
				cen = this.Binaria(ini, cen - 1, num);
		return cen;
	}
}
