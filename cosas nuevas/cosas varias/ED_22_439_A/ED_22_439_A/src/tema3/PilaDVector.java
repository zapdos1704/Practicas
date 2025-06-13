package tema3;

import java.util.Vector;
import ed.Datos;

public class PilaDVector
{
	private Vector<String> pila = new Vector<String>(5, 2);
	private Vector<String> aux = new Vector<String>(5, 2);
	private String nom, na;
	private boolean ban;
	private Datos obd = new Datos();

	private boolean Vacia()
	{
		return pila.isEmpty();
	}

	public void Insertar()
	{
		nom = obd.Cadena("Insertar nombre").toUpperCase();
		pila.add(nom);
		System.out.println("Nombre almacenado");
	}

	public void Extraer()
	{
		if (!this.Vacia())
		{
			nom = pila.remove(pila.size() - 1);
			System.out.println("Elemento extraido " + nom);
		}
		else
			System.out.println("Pila vacia....");
	}

	public void Recorrido()
	{
		if (!this.Vacia())
		{
			System.out.println("Contenido de la pila....");
			while (!this.Vacia())
			{
				nom = pila.remove(pila.size() - 1);
				System.out.println(nom);
				aux.add(nom);
			}
			while (!aux.isEmpty())
				pila.add(aux.remove(aux.size() - 1));
		}
		else
			System.out.println("Pila vacia...");
	}

	public void Busqueda()
	{
		if (!this.Vacia())
		{
			ban = true;
			na = obd.Cadena("Nombre a buscar").toUpperCase();
			while (!this.Vacia())
			{
				nom = pila.remove(pila.size() - 1);
				if (nom.indexOf(na) != -1)
				{
					if (ban)
						System.out.println("Nombres encontrados...");
					System.out.println(nom);
					ban = false;
				}
				aux.add(nom);
			}
			while (!aux.isEmpty())
				pila.add(aux.remove(aux.size() - 1));
			if (ban)
				System.out.println("El nombre a buscar no esta en la pila...");
		}
		else
			System.out.println("Pila vacia...");
	}

	public void Modificar()
	{
		if (!this.Vacia())
		{
			ban = true;
			na = obd.Cadena("Nombre a buscar").toUpperCase();
			while (!this.Vacia())
			{
				nom = pila.remove(pila.size() - 1);
				if (nom.indexOf(na) != -1)
				{
					System.out.println(nom);
					nom = obd.Cadena("Nuevo nombre...");
					ban = false;
				}
				aux.add(nom);
				if (!ban)
					break;
			}
			while (!aux.isEmpty())
				pila.add(aux.remove(aux.size() - 1));
			if (ban)
				System.out.println("El nombre a buscar no esta en la pila...");
		}
		else
			System.out.println("Pila vacia...");
	}
	
	public void Borrar()
	{
		pila.clear();
		System.out.println("Pila borrada...");
	}
	
	
}
