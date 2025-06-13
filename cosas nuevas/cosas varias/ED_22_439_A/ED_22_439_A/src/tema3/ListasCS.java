package tema3;

import ed.Datos;

public class ListasCS
{
	private NodoCS ini, fin, nvo, act, ant;
	private int pos, con, num;
	private Datos obd = new Datos();

	public ListasCS()
	{
		ini = fin = null;
	}

	private boolean Vacia()
	{
		return ini == null;
	}

	private int Cantidad()
	{
		int con = 0;
		if (!this.Vacia())
			for (nvo = ini, con = 1; nvo != fin; nvo = nvo.getSig(), con++)
				;
		return con;
	}

	public void InsertarIni()
	{
		nvo = new NodoCS(obd.Entero("Numero"));
		if (!this.Vacia())
		{
			nvo.setSig(ini);
			ini = nvo;
		}
		else
			ini = fin = nvo;
	}

	public void InsertarFin()
	{
		nvo = new NodoCS(obd.Entero("Numero"));
		if (!this.Vacia())
		{
			fin.setSig(nvo);
			nvo.setSig(ini);
			fin = nvo;
		}
		else
			ini = fin = nvo;
	}

	public void InsertarPos()
	{
		int con = 0;
		if (!this.Vacia())
		{
			do
				pos = obd.Entero("Posicion a ingresar el nombre");
			while (pos < 1);
			if (pos == 1)
				this.InsertarIni();
			else
				if (pos > this.Cantidad())
					this.InsertarFin();
				else
				{
					nvo = new NodoCS(obd.Entero("Numero"));
					for (act = ini, con = 1; con < pos; ant = act, act = act.getSig(), con++)
						;
					ant.setSig(nvo);
					nvo.setSig(act);
				}

		}
		else
		{
			nvo = new NodoCS(obd.Entero("Numero"));
			ini = fin = nvo;
		}
	}

	public void ExtraerIni()
	{
		if (!this.Vacia())
		{
			System.out.println("Nombre extraido del inicio " + ini.getNum());
			ini = ini.getSig();
		}
		else
			System.out.println("Lista de nombres vacia...");
	}

	public void ExtraerFin()
	{
		if (!this.Vacia())
		{
			System.out.println("Nombre extraido del fin " + fin.getNum());
			if (ini != fin)
			{
				for (ant = ini; ant.getSig() != fin; ant = ant.getSig())
					;
				fin = ant;
				fin.setSig(ini);
			}
			else
				ini = null;
		}
	}

	public void ExtraerPos()
	{
		if (!this.Vacia())
		{
			do
				pos = obd.Entero("Posicion a extraer");
			while (pos < 1 || pos > this.Cantidad());
			if (pos == 1)
				this.ExtraerIni();
			else
				if (pos == this.Cantidad())
					this.ExtraerFin();
				else
				{
					for (ant = ini, con = 1; con < pos - 1; ant = ant.getSig(), con++)
						;
					System.out.println("Nombre extraido " + ant.getSig().getNum());
					ant.setSig(ant.getSig().getSig());
				}
		}
	}

	public void Recorrido()
	{
		if (!this.Vacia())
		{
			System.out.println("Nombres de la lista...");
			for (nvo = ini; nvo != null; nvo = nvo.getSig())
				System.out.println(nvo.getNum());
			System.out.println("Fin de la lista...");
		}
		else
			System.out.println("Lista simple vacia...");
	}

	public void Buscar()
	{
		String msj = "";
		if (!this.Vacia())
		{
			num = obd.Entero("Numero a buscar");
			for (act = ini, con = 0, pos = 1; act != null; act = act.getSig(), pos++)
				if (act.getNum() == num)
				{
					con++;
					msj += pos + "  ";
				}
			if (con != 0)
			{
				System.out.println("Posiciones en donde se encontraron: " + msj);
				System.out.println("Cantidad de veces encontrado: " + con);
			}
			else
				System.out.println("El nombre no esta en la lista...");
		}
		else
			System.out.println("Lista simple vacia...");
	}

	public void Modificar()
	{
		if (!this.Vacia())
		{
			do
				pos = obd.Entero("Posicion a extraer");
			while (pos < 1 || pos > this.Cantidad());
			for (act = ini, con = 1; con < pos; act = ant.getSig(), con++)
				;
			System.out.println("Nombre a modificar: " + act.getNum());
			act.setNum(obd.Entero("Numero a modificar"));
		}
		else
			System.out.println("Lista simple vacia...");
	}

	public void Borrar()
	{
		ini = fin = null;
		System.out.println("Lista de nombres borrada...");
	}
}
