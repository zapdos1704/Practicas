package tema5;

import ed.Datos;
import java.util.Random;
import java.util.Arrays;
import java.util.LinkedList;

public class Interno
{
	private int arr[], tam, pos, aux, x, factor;
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
		this.RadixSort();
		System.out.println("\nArreglo ordenado");
		this.Mostrar(arr);
	}

	public void Aleatorios()
	{
		Random obr = new Random();
		for (pos = 1; pos < arr.length; pos++)
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

	public void Burbuja()
	{
		for (x = 0; x < arr.length; x++)
			for (pos = 0; pos < arr.length - 1; pos++)
				if (arr[pos] > arr[pos + 1])
				{
					aux = arr[pos];
					arr[pos] = arr[pos + 1];
					arr[pos + 1] = aux;
				}
	}

	public void BurbujaM()
	{
		for (pos = 0; pos < arr.length - 1; pos++)
			for (x = pos + 1; x < arr.length; x++)
				if (arr[pos] > arr[x])
				{
					aux = arr[pos];
					arr[pos] = arr[x];
					arr[x] = aux;
				}
	}

	public void QuickSort()
	{
		this.QuickSort(0, arr.length - 1);
	}

	public void QuickSort(int ini, int fin)
	{
		int izq = ini, der = fin, pivote = arr[(ini + fin) / 2];
		while (izq <= der)
		{
			while (arr[izq] < pivote)
				izq++;
			while (arr[der] > pivote)
				der--;
			if (izq <= der)
			{
				aux = arr[izq];
				arr[izq] = arr[der];
				arr[der] = aux;
				izq++;
				der--;
			}
		}
		if (ini < der)
			this.QuickSort(ini, der);
		if (fin > izq)
			this.QuickSort(izq, fin);
	}

	public void ShellShort()
	{
		int sal;
		for (sal = arr.length / 2; sal > 0; sal /= 2)
			for (x = sal; x < arr.length; x++)
				for (pos = x; pos >= sal && arr[pos - sal] > arr[pos]; pos -= sal)
				{
					aux = arr[pos - sal];
					arr[pos - sal] = arr[pos];
					arr[pos] = aux;
				}
	}

	public void RadixSort()
	{
		int may = arr[0], nd, dig;
		@SuppressWarnings("unchecked")
		LinkedList<Integer> colas[] = new LinkedList[10];
		for (x = 0; x < colas.length; x++)
			colas[x] = new LinkedList<Integer>();
		for (pos = 1; pos < arr.length; pos++)
			if (arr[pos] > may)
				may = arr[pos];
		nd = (may + "").length();
		for (dig = 1, factor = 1; dig <= nd; dig++, factor *= 10)
		{
			for (pos = 0; pos < arr.length; pos++)
				colas[arr[pos] / factor % 10].add(arr[pos]);
			for (x = 0, pos = -1; x < colas.length; x++)
				while (!colas[x].isEmpty())
					arr[++pos] = colas[x].removeFirst();
		}
	}

	public void Java()
	{
		Arrays.sort(arr);
	}
}
