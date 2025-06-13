package tema3;

import ed.Datos;

public class ListaSimpleTra
{
	private NodoST ini, fin, nvo, act, ant;
	private Datos obd = new Datos();
	private int pos, con, nt;

	public ListaSimpleTra()
	{
		ini = fin = null;
	}

	private boolean Vacia()
	{
		return ini == null;
	}

	private int NoTarjeta()
	{
		int num = 0;
		if (!this.Vacia())
		{
			for (act = ini, num = act.getDato().NoTarjeta(); act != null; act = act
					.getSig(), num = act.getDato().NoTarjeta() > num ? act.getDato().NoTarjeta() : num)
				;
			return num + 1;
		}
		else
			return 1;
	}

	private int Cantidad()
	{
		int con = 0;
		if (!this.Vacia())
			for (nvo = ini, con = 0; nvo != null; nvo = nvo.getSig(), con++)
				;
		return con;
	}

	public void InsertarIni()
	{
		nvo = new NodoST(new Trabajador(this.NoTarjeta()));
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
		nvo = new NodoST(new Trabajador(this.NoTarjeta()));
		if (!this.Vacia())
		{
			fin.setSig(nvo);
			fin = nvo;
		}
		else
			ini = fin = nvo;
	}

	public void InsertarPos()
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
				nvo = new NodoST(new Trabajador(this.NoTarjeta()));
				for (ant = ini, con = 1; con < pos - 1; ant = ant.getSig(), con++)
					;
				nvo.setSig(ant.getSig());
				ant.setSig(nvo);
			}
	}

	public void ExtraerIni()
	{
		if (!this.Vacia())
		{
			System.out.println("Trabajador eliminado del inicio...");
			ini.getDato().Mostrar();
			ini = ini.getSig();
		}
		else
			System.out.println("Lista de nombres vacia...");
	}

	public void ExtraerFin()
	{
		if (!this.Vacia())
		{
			System.out.println("Trabajador eliminado del final...");
			fin.getDato().Mostrar();
			if (ini != fin)
			{
				for (ant = ini; ant.getSig() != fin; ant = ant.getSig())
					;
				fin = ant;
				fin.setSig(null);
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
					System.out.println("Trabajador eliminado de la posicion " + pos + "...");
					ant.getSig().getDato().Mostrar();
					ant.setSig(ant.getSig().getSig());
				}
		}
	}

	public void Recorrido()
	{
		if (!this.Vacia())
		{
			System.out.println("Nombres de la lista...");
			for (act = ini; act != null; act = act.getSig())
				act.getDato().Mostrar();
			System.out.println("Fin de la lista...");
		}
		else
			System.out.println("Lista simple vacia...");
	}

	public void Buscar()
	{
		if (!this.Vacia())
		{
			do
				nt = obd.Entero("No. Tarjeta a buscar");
			while (nt < 1);

			for (act = ini, con = 0, pos = 1; act != null; act = act.getSig(), pos++)
				if (act.getDato().NoTarjeta() == nt)
				{
					act.getDato().Mostrar();
					break;
				}
			if (act == null)
				System.out.println("El trabajador no esta en la lista...");
		}
		else
			System.out.println("Lista simple vacia...");
	}

	public void Modificar()
	{
		if (!this.Vacia())
		{
			do
				nt = obd.Entero("No. Tarjeta a modificar");
			while (nt < 1);

			for (act = ini, con = 0, pos = 1; act != null; act = act.getSig(), pos++)
				if (act.getDato().NoTarjeta() == nt)
				{
					act.getDato().Modificar();
					break;
				}
			if (act == null)
				System.out.println("El trabajador no esta en la lista...");
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
