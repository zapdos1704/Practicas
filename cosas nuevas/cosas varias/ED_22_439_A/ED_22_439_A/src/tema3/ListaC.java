package tema3;
import ed.*;

public class ListaC
{
	private Datos obd = new Datos();
	private int clv;
	private NodoC ini, fin, nvo, act;

	public ListaC()
	{
		ini = fin = null;
	}

	private boolean Vacia()
	{
		return ini == null;
	}

	private int Clave()
	{
		return this.Vacia() ? 1 : fin.getPro().Clave() + 1;
	}

	public void Insertar()
	{
		nvo = new NodoC(new Producto(this.Clave()));
		if (!this.Vacia())
		{
			fin.setSig(nvo);
			nvo.setAnt(fin);
			fin = nvo;
			fin.setSig(ini);
			ini.setAnt(fin);
		}
		else
		{
			ini = fin = nvo;
			ini.setAnt(fin);
			ini.setSig(fin);
			fin.setSig(ini);
			fin.setAnt(ini);
		}
	}

	public void Extraer()
	{
		if (!this.Vacia())
		{
			do
				clv = obd.Entero("Clave del producto a extraer");
			while (clv < 1);
			for (act = ini; act != fin && act.getPro().Clave() != clv; act = act.getSig())
				;
			if (act.getPro().Clave() == clv)
			{
				System.out.println("Producto eliminado");
				act.getPro().Mostrar();
				if (ini != fin)
				{
					if (act == ini)
					{
						ini = ini.getSig();
						ini.setAnt(fin);
						fin.setSig(ini);
					}
					else
						if (act == fin)
						{
							fin = fin.getAnt();
							fin.setSig(ini);
							ini.setAnt(fin);
						}
						else
						{
							act.getAnt().setSig(act.getSig());
							act.getSig().setAnt(act.getAnt());
						}
				}
				else
					ini = null;
			}
			else
				System.out.println("La calve a extraer no esta en la lista");
		}
		else
			System.out.println("Lista vacia");
	}

	public void Recorrido()
	{
		if (!this.Vacia())
		{
			System.out.println("Lista de productos");
			for (act = ini; act != fin; act = act.getSig())
				act.getPro().Mostrar();
			act.getPro().Mostrar();
			System.out.println("Fin de la lista");
		}
		else
			System.out.println("Lista vacia");
	}

	public void Buscar()
	{
		if (!this.Vacia())
		{
			do
				clv = obd.Entero("Clave del producto a buscar");
			while (clv < 1);
			System.out.println("Lista de productos");
			for (act = ini; act != fin && act.getPro().Clave() != clv; act = act.getSig())
				;
			if (act.getPro().Clave() == clv)
			{
				System.out.println("Datos del prosucto");
				act.getPro().Mostrar();
			}
			else
				System.out.println("El producto no existe");
		}
		else
			System.out.println("Lista vacia");
	}

	public void Modificar()
	{
		if (!this.Vacia())
		{
			do
				clv = obd.Entero("Clave del producto a modificar");
			while (clv < 1);
			System.out.println("Lista de productos");
			for (act = ini; act != fin && act.getPro().Clave() != clv; act = act.getSig())
				;
			if (act.getPro().Clave() == clv)
			{
				act.getPro().Modificar();
				System.out.println("Producto modificado con exito");
			}
			else
				System.out.println("El producto no existe");
		}
		else
			System.out.println("Lista vacia");
	}

	public void Borrar()
	{
		ini = null;
		System.out.println("Lista de productos borrada");
	}
}
