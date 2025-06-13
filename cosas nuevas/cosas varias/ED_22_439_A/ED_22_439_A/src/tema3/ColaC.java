package tema3;

import ed.Datos;

public class ColaC
{
	private int cola[], ini, fin, tam;
	private Datos obd = new Datos();

	public ColaC()
	{
		do
			tam = obd.Entero("Tamaño de la cola circular");
		while (tam < 1);
		cola = new int[tam];
		ini = -1;
		fin = -1;
	}

	private boolean Vacia()
	{
		return ini == -1;
	}

	private boolean Llena()
	{
		return ini == 0 && fin == cola.length - 1 || ini - 1 == fin;
	}

	public void Insertar()
	{
		if (!this.Llena())
		{
			if (!this.Vacia())
				if (fin + 1 == cola.length)
					fin = 0;
				else
					fin++;
			else
				ini = fin = 0;
			cola[fin] = obd.Entero("Dame un numero entero");
		}
		else
			System.out.println("Cola llena...");
	}

	public void Extraer()
	{
		if (!this.Vacia())
		{
			System.out.println("Numero a extraido " + cola[ini]);
			if (ini == fin)
				ini = fin = -1;
			else
				if (ini == cola.length - 1)
					ini = 0;
				else
					ini++;
		}
		else
			System.out.println("Cola vacia...");
	}

	public void Recorrido()
	{
		if (!this.Vacia())
		{
			System.out.println("Contenido de la cola");
			if (fin >= ini)
				for (tam = ini; tam <= fin; tam++)
					System.out.println(cola[tam]);
			else
			{
				for (tam = ini; tam != fin; tam = tam == cola.length - 1 ? 0 : tam + 1)
					System.out.println(cola[tam]);
				System.out.println(cola[tam]);
			}
		}
		else
			System.out.println("Cola vacia...");
	}

	public void Buscar()
	{
		int con = 0, num;
		if (!this.Vacia())
		{
			num = obd.Entero("Numero a buscar");
			for (tam = ini; tam != fin; tam = tam == cola.length - 1 ? 0 : tam + 1)
				if (num == cola[tam])
					con++;
			if (num == cola[tam])
				con++;
			System.out.println("El numero " + num + " se encontro " + con + " veces...");
		}
		else
			System.out.println("Cola vacia...");
	}

	public void Borrar()
	{
		ini = fin = -1;
		System.out.println("Cola circular borrada...");
	}
}
