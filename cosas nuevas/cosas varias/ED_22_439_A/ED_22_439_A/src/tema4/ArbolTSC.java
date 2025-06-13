package tema4;

import java.util.Iterator;
import java.util.TreeSet;
import ed.Datos;

public class ArbolTSC
{
	private TreeSet<Cuenta> arbol = new TreeSet<Cuenta>(new ComparaCuenta());
	private Cuenta cue = null, act;
	private Datos obd = new Datos();
	private double mon;

	private int NoCuenta()
	{
		int num = 0;
		if (!arbol.isEmpty())
			num = arbol.last().NoCuenta();
		return num + 1;
	}

	public void Insertar()
	{
		if (arbol.add(new Cuenta(this.NoCuenta())))
			System.out.println("Se agrego correctamente...");
	}

	public void Recorrido()
	{
		;
		if (!arbol.isEmpty())
		{
			System.out.println("Contenido del arbol...");
			for (Iterator<Cuenta> it = arbol.iterator(); it.hasNext();)
				it.next().MostrarT();
			System.out.println("Fin del arbol...");
		}
		else
			System.out.println("No hay elementos en el arbol...");
	}

	public void Eliminar()
	{
		if (!arbol.isEmpty())
		{
			cue = new Cuenta("eliminar");
			if (arbol.contains(cue))
			{
				for (Iterator<Cuenta> it = arbol.iterator(); it.hasNext();)
				{
					act = it.next();
					if (act.NoCuenta() == cue.NoCuenta())
						break;
				}
				if (act.Saldo() == 0)
				{
					System.out.println("Cuenta eliminada...");
					arbol.remove(cue);
				}
				else
					System.out.println("No se puede eliminar...");
				act.Mostrar();

			}
			else
				System.out.println("No se encuentra esa cadena en el arbol...");
		}
		else
			System.out.println("No hay elementos en el arbol...");
	}

	public void Retiro()
	{
		if (!arbol.isEmpty())
		{
			cue = new Cuenta("retirar");
			if (arbol.contains(cue))
			{
				for (Iterator<Cuenta> it = arbol.iterator(); it.hasNext();)
				{
					act = it.next();
					if (act.NoCuenta() == cue.NoCuenta())
						break;
				}
				System.out.println("Datos de la cuenta...");
				act.MostrarT();
				do
					mon = obd.Doble("Cantidad de retirar");
				while (mon < 1 || mon > act.Saldo());
				System.out.println("Cuenta...");
				act.Movimiento(mon);
				act.MostrarT();
				arbol.remove(act);
				arbol.add(act);
			}
			else
				System.out.println("No se encuentra esa cadena en el arbol...");
		}
		else
			System.out.println("No hay elementos en el arbol...");
	}

	public void Deposito()
	{
		if (!arbol.isEmpty())
		{
			cue = new Cuenta("buscar");
			if (arbol.contains(cue))
			{
				for (Iterator<Cuenta> it = arbol.iterator(); it.hasNext();)
				{
					act = it.next();
					if (act.NoCuenta() == cue.NoCuenta())
						break;
				}
				System.out.println("Datos de la cuenta...");
				act.MostrarT();
				do
					mon = obd.Doble("Cantidad a depositar");
				while (mon < 1);
				System.out.println("Cuenta...");
				act.Movimiento(mon * -1);
				act.MostrarT();
				arbol.remove(act);
				arbol.add(act);
			}
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
			cue = new Cuenta("buscar");
			if (arbol.contains(cue))
			{
				for (Iterator<Cuenta> it = arbol.iterator(); it.hasNext();)
				{
					act = it.next();
					if (act.NoCuenta() == cue.NoCuenta())
						break;
				}
				System.out.println("Cuenta...");
				act.Mostrar();
			}
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
