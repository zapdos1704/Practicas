package tema3;

import java.util.Vector;
import ed.Datos;
import ed.Formato;

public class ColaDSPro
{
	private Vector<Producto> cola = new Vector<Producto>(4, 1);
	private Datos obd = new Datos();
	private Formato obf = new Formato();
	private int bus;
	private boolean ban;

	private boolean Vacia()
	{
		return cola.isEmpty();
	}

	private int Clave()
	{
		if (!this.Vacia())
			return cola.get(cola.size() - 1).Clave() + 1;
		else
			return 1;
	}

	private void Mensaje()
	{
		System.out.println(obf.Izq("CLAVE", 5) + " | " + obf.Izq("NOMBRE", 25) + " | " + obf.Der("PRECIO", 12));
	}

	public void Insertar()
	{
		cola.add(new Producto(this.Clave()));
		System.out.println("Materia insertada correctamente...");
	}

	public void Extraer()
	{
		if (!this.Vacia())
			System.out.println("Materia extraida " + cola.remove(0));
		else
			System.out.println("Cola de materias vacia...");
	}

	public void Recorrido()
	{
		if (!this.Vacia())
		{
			System.out.println("Materias de la cola...");
			this.Mensaje();
			for (Producto prod : cola)
				prod.Mostrar();
			System.out.println("Final de la cola...");
		}
		else
			System.out.println("Cola de materias vacia...");
	}

	public void Buscar()
	{
		ban = true;
		if (!this.Vacia())
		{
			do
				bus = obd.Entero("Clave a buscar");
			while (bus < 1);
			for (Producto prod : cola)
				if (prod.Clave() == bus)
				{
					System.out.println("Producto encontrado...");
					this.Mensaje();
					prod.Mostrar();
					ban = false;
					break;
				}
			if (ban)
				System.out.println("El dato no existe en la cola...");
		}
		else
			System.out.println("Cola de materias vacia...");
	}

	public void Modificar()
	{
		ban = true;
		if (!this.Vacia())
		{
			do
				bus = obd.Entero("Clave a modificar");
			while (bus < 1);
			for (Producto prod : cola)
				if (prod.Clave() == bus)
				{
					prod.Modificar();
					ban = false;
					break;
				}
			if (ban)
				System.out.println("El dato no existe en la cola...");
		}
		else
			System.out.println("Cola de materias vacia...");
	}

	public void Borrar()
	{
		cola.clear();
		System.out.println("Cola de materias borrada...");
	}
}
