package tema3;

import java.util.Vector;
import ed.Datos;

public class BicolaD
{
	private Vector<String> bicola = new Vector<String>(5, 2);
	private Datos obd = new Datos();

	private boolean Vacia()
	{
		return bicola.isEmpty();
	}

	public void InsertarIn()
	{
		bicola.add(0, obd.Cadena("Nombre de la ciudad").toUpperCase());
		System.out.println("Ciudad agregada exitosamente...");
	}

	public void InsertarFin()
	{
		bicola.add(obd.Cadena("Nombre de la ciudad").toUpperCase());
		System.out.println("Ciudad agregada exitosamente...");
	}

	public void ExtraerIn()
	{
		if (!this.Vacia())
			System.out.println("Ciudad a extraer es: " + bicola.remove(0));
		else
			System.out.println("Bicola vacia...");

	}

	public void ExtraerFin()
	{
		if (!this.Vacia())
			System.out.println("Ciudad a extraer es: " + bicola.remove(bicola.size() - 1));
		else
			System.out.println("Bicola vacia...");
	}

	public void Recorrido()
	{
		if (!this.Vacia())
		{
			System.out.println("Contenido de la bicola...");
			for (String c : bicola)
				System.out.println(c);
			System.out.println("Fin de la lista...");
		}
		else
			System.out.println("Bicola vacia...");
	}

	public void Busqueda()
	{
		int pos = 1;
		String cd;
		boolean ban = true;
		if (!this.Vacia())
		{
			cd = obd.Cadena("Ciudad a buscar").toUpperCase();
			for (String c : bicola)
			{
				if (c.equals(cd))
				{
					if (ban)
						System.out.println("La ciudad esta en las posiciones...");
					System.out.println(pos);
					ban = false;
				}
				pos++;
			}
			if (ban)
				System.out.println("No existe ninguna ciudad con ese nombre");
		}
		else
			System.out.println("Bicola vacia...");
	}

	public void Borrar()
	{
		bicola.clear();
		System.out.println("Bicola de ciudades ha sido borrada...");
	}
}
