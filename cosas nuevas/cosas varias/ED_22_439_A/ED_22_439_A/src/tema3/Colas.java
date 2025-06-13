package tema3;

import ed.Datos;

public class Colas
{
	private String nom[], nomb;
	private int tam, pos;
	private Datos obd = new Datos();

	public Colas()
	{
		do
			tam = obd.Entero("Cual es el tamaño de la cola");
		while (tam < 1);
		nom = new String[tam];
		tam = -1;
	}

	private boolean Vacia()
	{
		return tam == -1;
	}

	private boolean LLena()
	{
		return nom.length - 1 == tam;
	}

	public void Insertar()
	{
		if (!this.LLena())
		{
			nom[++tam] = obd.Cadena("Nombre del estudiante").toUpperCase();
			System.out.println("Nombre insertado con exito...");
		}
		else
			System.out.println("La cola esta llena...");
	}

	public void Extraer()
	{
		if (!this.Vacia())
		{
			System.out.println("Nombre extraido " + nom[0]);
			if (tam != 0)
			{
				for (pos = 0; pos < tam; pos++)
					nom[pos] = nom[pos + 1];
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
			for (pos = 0; pos <= tam; pos++)
				System.out.println(nom[pos]);
			System.out.println("Fin de la estructura...");
		}
		else
			System.out.println("Cola vacia...");
	}

	public void Buscar()
	{
		boolean ban = true;
		if (!this.Vacia())
		{
			nomb = obd.Cadena("Nombre a buscar");
			for (pos = 0; pos <= tam; pos++)
				if (nom[pos].contains(nomb))
				{
					if (ban)
						System.out.println("Alumnos encontrados...");
					System.out.println(nom[pos]);
					ban = false;
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
		System.out.println("Cola borrada...");
	}
}
