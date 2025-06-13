package tema3;

import ed.Datos;

public class BicolaNom
{
	private Datos obd = new Datos();
	private String cola[], nom;
	private int tam, pos;

	public BicolaNom()
	{
		do
			tam = obd.Entero("Tamaño de la bicola");
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
			cola[0] = obd.Cadena("Nombre a insertar").toUpperCase();
			System.out.println("Nombre insertado con exito");
		}
		else
			System.out.println("Bicola llena");
	}

	public void InsertarFin()
	{
		if (!this.Llena())
		{
			cola[++tam] = obd.Cadena("Nombre a insertar").toUpperCase();
			System.out.println("Nombre insertado con exito");
		}
		else
			System.out.println("Bicola llena");
	}

	public void ExtraerIn()
	{
		if (!this.Vacia())
		{
			System.out.println("Nombre a extraer " + cola[0]);
			for (pos = 0; pos <= tam; pos++)
				cola[pos] = cola[pos + 1];
			tam--;
			System.out.println("Nombe extraido con exito");
		}
		else
			System.out.println("Bicola vacia");
	}

	public void ExtraerFin()
	{
		if (!this.Vacia())
		{
			System.out.println("Nombre a extraer " + cola[tam--]);
			System.out.println("Nombre extraido con exito");
		}
		else
			System.out.println("Bicola vacia");
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
			nom = obd.Cadena("Nombre o Apellido a busar en la bicola").toUpperCase();
			for (pos = 0; pos <= tam; pos++)
				if (cola[pos].contains(nom))
					con++;
			System.out.println("El nombre " + nom + " se encontro " + con);
		}
		else
			System.out.println("Bicola vacia");
	}

	public void Borrar()
	{
		tam = -1;
		System.out.println("Bicola borrada");
	}
}
