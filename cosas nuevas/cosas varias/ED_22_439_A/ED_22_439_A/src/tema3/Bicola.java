package tema3;

import ed.Datos;

public class Bicola
{
	private Datos obd = new Datos();
	private String nom, cola[];
	private int tam, pos;

	public void Nombres()
	{
		do
			tam = obd.Entero("Tamaño");
		while (tam < 1);
		cola = new String[tam];
		tam = -1;
	}

	private boolean Vacia()
	{
		return tam == -1;
	}

	private boolean Llena()
	{
		return tam == cola.length - 1;
	}

	public void InsertarIn()
	{
		if (!this.Llena())
		{
			for (pos = ++tam; pos > 0; pos--)
				cola[pos] = cola[pos - 1];
			cola[0] = obd.Cadena("Introduzca un nombre: ").toUpperCase();
			System.out.println("Nombre insertado con exito");
		}
		else
			System.out.println("La cola esta llena");
	}

	public void InsertarFin()
	{
		if (!this.Llena())
		{
			cola[++tam] = obd.Cadena("Intruzaca un nombre").toUpperCase();
			System.out.println("Nombre insertado con exito");
		}
		else
			System.out.println("Cola llena...");
	}

	public void ExtraerIn()
	{
		if (!this.Vacia())
		{
			System.out.println("Nombre extraido " + cola[0]);
			for (pos = 0; pos <= tam; pos++)
				cola[pos] = cola[pos + 1];
			tam--;
		}
		else
			System.out.println("Cola vacía");
	}

	public void ExtraerFin()
	{
		if (!this.Vacia())
		{
			System.out.println("Nombre extraido" + cola[tam--]);
		}
		else
			System.out.println("Cola vacía");
	}

	public void Recorrido()
	{
		if (!this.Vacia())
		{
			System.out.println("Nombres de la bicola");
			for (pos = 0; pos <= tam; pos++)
				System.out.println(cola[pos]);
			System.out.println("Fin de la bicola");
		}
		else
			System.out.println("Bicola vacia");
	}

	public void Buscar()
	{
		int con = 0;
		if (!this.Vacia())
		{
			nom = obd.Cadena("Nombre a buscar en la bicola").toUpperCase();
			for (pos = 0; pos <= tam; pos++)
				if (cola[pos].equals(nom))
					con++;
			System.out.println("El nombre " + nom + " se encontro " + con + " veces..");
		}
		else
			System.out.println("Bicola vacia");
	}

	public void Borrar()
	{
		tam = -1;
		System.out.println("Bicola borrada...");
	}
}
