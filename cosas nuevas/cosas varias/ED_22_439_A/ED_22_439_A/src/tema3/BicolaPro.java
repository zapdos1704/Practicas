package tema3;

import java.util.Vector;
import ed.Datos;

public class BicolaPro
{
	private Vector<Producto> bicola = new Vector<Producto>(5, 2);
	private Datos obd = new Datos();
	private int clv;
	private boolean ban;

	private boolean Vacia()
	{
		return bicola.isEmpty();
	}

	private int Clave()
	{
		return !bicola.isEmpty() ? bicola.get(bicola.size() - 1).Clave() > bicola.get(0).Clave()
				? bicola.get(bicola.size() - 1).Clave() + 1
				: bicola.get(0).Clave() + 1 : 1;
	}

	public void InsertarIn()
	{
		bicola.add(0, new Producto(this.Clave()));
		System.out.println("Producto agregado exitosamente...");
	}

	public void InsertarFin()
	{
		bicola.add(new Producto(this.Clave()));
		System.out.println("Producto agregado exitosamente...");
	}

	public void ExtraerIn()
	{
		if (!this.Vacia())
		{
			System.out.println("Producto a extraer es: ");
			bicola.remove(0).Mostrar();
		}
		else
			System.out.println("Bicola vacia...");

	}

	public void ExtraerFin()
	{
		if (!this.Vacia())
		{
			System.out.println("Producto a extraer es: ");
			bicola.remove(bicola.size() - 1).Mostrar();
		}
		else
			System.out.println("Bicola vacia...");
	}

	public void Recorrido()
	{
		if (!this.Vacia())
		{
			System.out.println("Contenido de la bicola...");
			for (Producto pro : bicola)
				pro.Mostrar();
			System.out.println("Fin de la lista...");
		}
		else
			System.out.println("Bicola vacia...");
	}

	public void Busqueda()
	{
		if (!this.Vacia())
		{
			ban = true;
			do
				clv = obd.Entero("Cual es la clave del producto a buscar");
			while (clv < 1);
			for (Producto pro : bicola)
			{
				if (pro.Clave() == clv)
				{
					System.out.println("Producto encontrado...");
					pro.Mostrar();
					ban = false;
					break;
				}
			}
			if (ban)
				System.out.println("No existe ningun producto con esa clave...");
		}
		else
			System.out.println("Bicola vacia...");
	}

	public void Modificar()
	{
		if (!this.Vacia())
		{
			ban = true;
			do
				clv = obd.Entero("Cual es la clave del producto a buscar");
			while (clv < 1);
			for (Producto c : bicola)
			{
				if (c.Clave() == clv)
				{
					System.out.println("Producto encontrado...");
					c.Modificar();
					ban = false;
					break;
				}
			}
			if (ban)
				System.out.println("No existe ningun producto con esa clave...");
		}
		else
			System.out.println("Bicola vacia...");
	}

	public void Borrar()
	{
		bicola.clear();
		System.out.println("Bicola de productos ha sido borrada...");
	}
}
