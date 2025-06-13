package tema3;

import ed.Datos;

public class ListaCDE
{
	private Datos obd = new Datos();
	private int clv;
	private NodoCE ini, fin, nvo, act;

	public ListaCDE()
	{
		ini = fin = null;
	}

	private boolean Vacia()
	{
		return ini == null;
	}

	public void Insertar()
	{
		nvo = new NodoCE(obd.Entero("Numero"));
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
			clv = obd.Entero("Numero a extraer");
			for (act = ini; act != fin && act.getNum() != clv; act = act.getSig())
				;
			if (act.getNum() == clv)
			{
				System.out.println("Numero eliminado " + act.getNum());
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
				System.out.println(act.getNum());
			System.out.println(act.getNum());
			System.out.println("Fin de la lista");
		}
		else
			System.out.println("Lista vacia");
	}

	public void Buscar()
	{
		int con = 0;
		if (!this.Vacia())
		{
			clv = obd.Entero("Clave del producto a buscar");
			for (act = ini; act != fin; act = act.getSig(), con++)
				if (act.getNum() == clv)
				{
					con++;
				}
			if (act == null)
				System.out.println("No existe ese numero en la lista..");
			else
				System.out.println("Cantidad " + con);
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
			for (act = ini; act != fin && act.getNum() != clv; act = act.getSig())
				;
			if (act.getNum() == clv)
			{
				act.setNum(obd.Entero("Nuevo numero"));
				System.out.println("Numero modificado exitosamente");
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
