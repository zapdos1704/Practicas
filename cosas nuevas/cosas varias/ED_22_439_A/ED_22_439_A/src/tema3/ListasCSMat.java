package tema3;

import ed.Datos;

public class ListasCSMat
{
	private NodoCSM nvo, ini, fin, ant, act;
	private int pos, con, num;
	private Datos obd = new Datos();

	public ListasCSMat()
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
		nvo = new NodoCSM(new MateriaOp());
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
		nvo = new NodoCSM(new MateriaOp());
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
					nvo = new NodoCSM(new MateriaOp());
					for (act = ini, con = 1; con < pos; ant = act, act = act.getSig(), con++)
						;
					ant.setSig(nvo);
					nvo.setSig(act);
				}

		}
		else
		{
			nvo = new NodoCSM(new MateriaOp());
			ini = fin = nvo;
		}
	}

	public void ExtraerIni()
	{
		if (!this.Vacia())
		{
			System.out.println("Materia extraida al inicio");
			ini.getMat().Mostrar();
			ini = ini.getSig();
		}
		else
			System.out.println("Lista de nombres vacia...");
	}

	public void ExtraerFin()
	{
		if (!this.Vacia())
		{
			System.out.println("Materia extraida del fin ");
			fin.getMat().Mostrar();
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
					System.out.println("Materia extraida ");
					ant.getSig().getMat().Mostrar();
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
				nvo.getMat().Mostrar();
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
				num = obd.Entero("Materia a buscar");
			while (num < 1);
			for (act = ini, con = 0, pos = 1; act != null; act = act.getSig(), pos++)
				if (act.getMat().Clave() == num)
				{
					System.out.println("Materia encontrada...");
					act.getMat().Mostrar();
					break;
				}
			if (act == null)
				System.out.println("La clave de la materia no esta en la lista...");
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
			act.getMat().Modificar();
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
