package tema3;

import ed.Datos;
import ed.Formato;

public class ColaSAlu
{
	private int pos, tam;
	private boolean ban;
	private String nomb;
	private Alumno cola[];
	private Datos obd = new Datos();

	public ColaSAlu()
	{
		do
			tam = obd.Entero("Cantidad de alumnos");
		while (tam < 1);
		cola = new Alumno[tam];
		tam = -1;
	}

	private void Titulos()
	{
		Formato obf = new Formato();
		System.out.println(obf.Izq("NO.CTRL", 10) + " | " + obf.Izq("NOMBRE", 25) + " | " + obf.Der("PROM", 5));
	}

	private boolean Vacia()
	{
		return tam == -1;
	}

	private boolean Llena()
	{
		return tam == cola.length - 1;
	}

	public void Insertar()
	{
		if (!this.Llena())
		{
			cola[++tam] = new Alumno();
			System.out.println("Alumno ingresado correctamente...");
		}
		else
			System.out.println("Cola llena...");
	}

	public void Extraer()
	{
		if (!this.Vacia())
		{
			System.out.println("Datos del alumno a extraer:\n");
			cola[0].Mostrar();
			if (tam != 0)
			{
				for (pos = 0; pos < tam; pos++)
					cola[pos] = cola[pos + 1];
				tam--;
			}
			else
				tam = -1;
		}
		else
			System.out.println("Cola vacia...");
	}

	public void Recorrido()
	{
		if (!this.Vacia())
		{
			System.out.println("Nombres de la lista....");
			this.Titulos();
			for (pos = 0; pos <= tam; pos++)
				cola[pos].Mostrar();
			System.out.println("Fin de la estructura...");
		}
		else
			System.out.println("Cola vacia...");
	}

	public void Buscar()
	{
		ban = true;
		if (!this.Vacia())
		{
			nomb = obd.Cadena("Numero de control a buscar");
			for (pos = 0; pos <= tam; pos++)
				if (cola[pos].NoControl().equals(nomb))
				{
					System.out.println("Alumnos encontrados...");
					this.Titulos();
					cola[pos].Mostrar();
					ban = false;
					break;
				}
			if (ban)
				System.out.println("No existe en nombre o apellido del alumno a buscar...");
		}
		else
			System.out.println("Cola vacia...");
	}

	public void Modificar()
	{
		ban = true;
		if (!this.Vacia())
		{
			nomb = obd.Cadena("Numero de control a modificar");
			for (pos = 0; pos <= tam; pos++)
				if (cola[pos].NoControl().contains(nomb))
				{
					cola[pos].Modificar();
					ban = false;
					break;
				}
			if (ban)
				System.out.println("No existe en nombre o apellido del alumno a buscar...");
		}
		else
			System.out.println("Cola vacia...");
	}

	public void Borrar()
	{
		tam = -1;
		System.out.println("Cola de alumnos borrada...");
	}
}
