package tema3;

import ed.Datos;

public class ListaDoble
{
	private NodoD ini, fin, nvo, act;
	private Datos obd = new Datos();
	private String pro;
	private int con, pos;

	public ListaDoble()
	{
		ini = fin = null;
	}

	private boolean Vacia()
	{
		return ini == null;
	}

	public int Cantidad()
	{
		for (act = ini, con = 0; act != null; act = act.getSig(), con++)
			;
		return con;
	}

	public void InsertarIni()
	{
		nvo = new NodoD(obd.Cadena("Nombre del producto a insertar"));
		if (!this.Vacia())
		{
			nvo.setSig(ini);
			ini.setAnt(nvo);
			ini = nvo;
		}
		else
			ini = fin = nvo;
	}

	public void InsertarPos()
	{
		do
			pos = obd.Entero("Posicion a insertar el nuevo producto");
		while (pos < 1);
		if (pos == 1)
			this.InsertarIni();
		else
			if (pos > this.Cantidad())
				this.InsertarFin();
			else
			{
				nvo = new NodoD(obd.Cadena("Nombre del producto a insertar"));
				for (act = ini, con = 1; con < pos; act = act.getSig(), con++)
					;
				act.getAnt().setSig(nvo);
				nvo.setAnt(act.getAnt());
				nvo.setSig(act);
				act.setAnt(nvo);
			}
	}

	public void InsertarFin()
	{
		nvo = new NodoD(obd.Cadena("Nombre del producto a insertar"));
		if (!this.Vacia())
		{
			nvo.setSig(fin);
			fin.setAnt(nvo);
		}
		else
			ini = fin = nvo;
	}

	public void ExtraerIni()
	{
		if (!this.Vacia())
		{
			System.out.println("Producto extraido " + ini.getPro());
			if (ini != fin)
			{
				ini = ini.getSig();
				ini.setAnt(null);
			}
			else
				ini = fin = null;
		}
		else
			System.out.println("No hay productos en la fila");
	}

	public void ExtraerPos()
	{
		if (!this.Vacia())
		{

			do
				pos = obd.Entero("Posicion a extraer el nuevo producto");
			while (pos < 1 || pos > this.Cantidad());
			if (pos == 1)
				this.ExtraerIni();
			else
				if (pos == this.Cantidad())
					this.ExtraerFin();
				else
				{
					for (act = ini, con = 1; con < pos; act = act.getSig(), con++)
						;
					act.getAnt().setSig(act.getSig());
					act.getSig().setAnt(act.getAnt());
				}
		}
		else
			System.out.println("No hay productos en la fila");
	}

	public void ExtraerFin()
	{
		if (!this.Vacia())
		{
			System.out.println("Producto extraido " + fin.getPro());
			if (ini != fin)
			{
				fin = fin.getAnt();
				fin.setAnt(null);
			}
			else
				ini = fin = null;
		}
		else
			System.out.println("No hay productos en la fila");
	}

	public void Recorrido()
	{
		if (!this.Vacia())
		{
			System.out.println("Lista de productos...");
			for (act = ini; act != null; act = act.getSig())
				System.out.println(act.getPro());
			System.out.println("Fin de la lista...");
		}
		else
			System.out.println("No hay productos en la fila");
	}

	public void Buscar()
	{
		boolean ban = true;
		if (!this.Vacia())
		{
			pro = obd.Cadena("Producto a buscar").toUpperCase();
			for (act = ini; act != null; act = act.getSig())
				if (act.getPro().indexOf(pro) != -1)
				{
					System.out.println(act.getPro());
					ban = false;
				}
			if (ban)
				System.out.println("El producto no esta en la lista...");
			else
				System.out.println("Fin de la lista...");
		}
		else
			System.out.println("No hay productos en la fila");
	}

	public void Modificar()
	{
		if (!this.Vacia())
		{
			pro = obd.Cadena("Producto a buscar").toUpperCase();
			for (act = ini; act != null; act = act.getSig())
				if (act.getPro().equals(pro))
				{
					act.setPro(obd.Cadena("Nuevo nombre del producto"));
					break;
				}
			if (act == null)
				System.out.println("El producto no esta en la lista...");
			else
				System.out.println("Fin de la lista...");
		}
		else
			System.out.println("No hay productos en la fila");
	}

	public void Borrar()
	{
		ini = fin = null;
		System.out.println("Lista borrada...");
	}
}
