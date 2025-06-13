package tema4;

import java.util.TreeSet;
import java.util.Iterator;
import ed.Datos;

public class ArbolTS
{
	private TreeSet<String> arbol = new TreeSet<String>();
	private Datos obd = new Datos();
	private String cad;

	public void Insertar()
	{
		if (arbol.add(obd.Cadena("Cadena a insertar en el arbol").toUpperCase()))
			System.out.println("Se agrego correctamente...");
		else
			System.out.println("Ya existe esta cadena en el arbol...");
	}

	public void Recorrido()
	{
		Iterator<String> it = arbol.iterator();
		if (!arbol.isEmpty())
		{
			System.out.println("Contenido del arbol...");
			while (it.hasNext())
				System.out.println(it.next());
			System.out.println("Fin del arbol...");
		}
		else
			System.out.println("No hay elementos en el arbol...");
	}

	public void Eliminar()
	{
		if (!arbol.isEmpty())
		{
			cad = obd.Cadena("Cadena a eliminar").toUpperCase();
			if (arbol.remove(cad))
				System.out.println("Cadena eliminada...");
			else
				System.out.println("No se encuentra esa cadena en el arbol...");
		}
		else
			System.out.println("No hay elementos en el arbol...");
	}

	public void Buscar()
	{
		if (!arbol.isEmpty())
		{
			cad = obd.Cadena("Cadena a buscar");
			if (arbol.contains(cad))
				System.out.println("Cadena encontrada");
			else
				System.out.println("No se encuentra esa cadena en el arbol...");
		}
		else
			System.out.println("No hay elementos en el arbol...");
	}

	public void Borrar()
	{
		arbol.clear();
		System.out.println("Arbol borrado...");
	}
}
