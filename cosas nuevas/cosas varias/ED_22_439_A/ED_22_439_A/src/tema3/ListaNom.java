package tema3;

import java.util.LinkedList;
import ed.Datos;

public class ListaNom
{
	private String nom;
	private int pos;
	private Datos obd = new Datos();
	private LinkedList<String> lista = new LinkedList<String>();

	public void InsertarIni()
	{
		lista.addFirst(obd.Cadena("Nuevo nombre").toUpperCase());
		System.out.println("Agregdo exitosamente...");
	}

	public void InsertarFin()
	{
		lista.addLast(obd.Cadena("Nuevo nombre").toUpperCase());
		System.out.println("Agregdo exitosamente...");
	}

	public void InsertarPos()
	{
		do
			pos = obd.Entero("Cual es la posicion");
		while (pos < 1 || pos > lista.size());
		lista.add(pos - 1, obd.Cadena("Nombre nuevo"));
		System.out.println("Agregdo exitosamente...");
	}

	public void ExtraerIni()
	{
		if (!lista.isEmpty())
			System.out.println("Nombre eliminado " + lista.removeFirst());
		else
			System.out.println("Lista vacia...");
	}

	public void ExtraerFin()
	{
		if (!lista.isEmpty())
			System.out.println("Nombre eliminado " + lista.removeLast());
		else
			System.out.println("Lista vacia...");
	}

	public void ExtraerPos()
	{
		if (!lista.isEmpty())
		{
			do
				pos = obd.Entero("Cual es la posicion");
			while (pos < 1 || pos > lista.size());
			System.out.println("Nombre eliminado " + lista.remove(pos - 1));
		}
		else
			System.out.println("Lista vacia...");
	}

	public void Recorrido()
	{
		if (!lista.isEmpty())
		{
			System.out.println("Lista de nombres...");
			for (String nom : lista)
				System.out.println(nom);
			System.out.println("Fin de la lista");
		}
		else
			System.out.println("Lista vacia...");
	}

	public void Buscar()
	{
		boolean ban = true;
		if (!lista.isEmpty())
		{
			nom = obd.Cadena("Nombre a buscar");
			for (String no : lista)
				if (no.contains(nom))
				{
					if (ban)
						System.out.println("Nombres encontrados...");
					System.out.println(no);
					ban = false;
				}
			if (ban)
				System.out.println("No se encontro ese nombre o apellido en la lsita");
		}
		else
			System.out.println("Lista vacia...");
	}

	public void Modificar()
	{
		if (!lista.isEmpty())
		{
			nom = obd.Cadena("Nombre a modificar");
			for (pos = 0; pos < lista.size(); pos++)
				if (lista.get(pos).equals(nom))
				{
					lista.set(pos, obd.Cadena("Nuevo nombre"));
				}
			if (pos > lista.size())
				System.out.println("No se encontro ese nombre o apellido en la lsita");
		}
		else
			System.out.println("Lista vacia...");
	}

	public void Borrar()
	{
		lista.remove();
		System.out.println("Lista borrada...");
	}
}
