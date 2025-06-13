package tema3;

import ed.Datos;

public class ListaDobleAlu
{
	private NodoDAlu ini, fin, nvo, act;
	private Datos obd = new Datos();
	private String nc;
	private int con, pos;

	public ListaDobleAlu()
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
		nvo = new NodoDAlu(new Alumno());
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
			pos = obd.Entero("Posicion a insertar el nuevo alumno");
		while (pos < 1);
		if (pos == 1)
			this.InsertarIni();
		else
			if (pos > this.Cantidad())
				this.InsertarFin();
			else
			{
				nvo = new NodoDAlu(new Alumno());
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
		nvo = new NodoDAlu(new Alumno());
		if (!this.Vacia())
		{
			nvo.setSig(fin);
			fin.setAnt(nvo);
			fin = nvo;
		}
		else
			ini = fin = nvo;
	}

	public void ExtraerIni()
	{
		if (!this.Vacia())
		{
			System.out.println("Alumno extraido ");
			ini.getAlu().Mostrar();
			if (ini != fin)
			{
				ini = ini.getSig();
				ini.setAnt(null);
			}
			else
				ini = fin = null;
		}
		else
			System.out.println("No hay alumnos en la fila");
	}

	public void ExtraerPos()
	{
		if (!this.Vacia())
		{

			do
				pos = obd.Entero("Posicion a extraer el nuevo alumno");
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
			System.out.println("No hay alumnos en la fila");
	}

	public void ExtraerFin()
	{
		if (!this.Vacia())
		{
			System.out.println("Alumno extraido ");
			fin.getAlu().Mostrar();
			if (ini != fin)
			{
				fin = fin.getAnt();
				fin.setAnt(null);
			}
			else
				ini = fin = null;
		}
		else
			System.out.println("No hay alumnos en la fila");
	}

	public void Recorrido()
	{
		if (!this.Vacia())
		{
			System.out.println("Lista de alumnos...");
			for (act = ini; act != null; act = act.getSig())
				act.getAlu().Mostrar();
			System.out.println("Fin de la lista...");
		}
		else
			System.out.println("No hay alumnos en la fila");
	}

	public void BuscarNom()
	{
		if (!this.Vacia())
		{
			nc = obd.Cadena("No. de Control del alumno a buscar").toUpperCase();
			for (act = ini; act != null; act = act.getSig())
				if (act.getAlu().NoControl().equals(nc))
				{
					System.out.println("Alumno encontrado...");
					act.getAlu().Mostrar();
					break;
				}
			if (act == null)
				System.out.println("El alumno no esta en la lista...");
			else
				System.out.println("Fin de la lista...");
		}
		else
			System.out.println("No hay alumnos en la fila");
	}

	public void Buscar()
	{
		boolean ban = true;
		if (!this.Vacia())
		{
			nc = obd.Cadena("Nombre o apellido del alumno a buscar").toUpperCase();
			for (act = ini; act != null; act = act.getSig())
				if (act.getAlu().NoControl().contains(nc))
				{
					if (ban)
						System.out.println("Lista de alumnos encontrados...");
					act.getAlu().Mostrar();
					ban = false;
				}
			if (ban)
				System.out.println("El alumno no esta en la lista...");
			else
				System.out.println("Fin de la lista...");
		}
		else
			System.out.println("No hay alumnos en la fila");
	}

	public void Modificar()
	{
		if (!this.Vacia())
		{
			nc = obd.Cadena("Producto a buscar").toUpperCase();
			for (act = ini; act != null; act = act.getSig())
				if (act.getAlu().NoControl().equals(nc))
				{
					act.getAlu().Modificar();
					break;
				}
			if (act == null)
				System.out.println("El alumno no esta en la lista...");
		}
		else
			System.out.println("No hay alumnos en la fila");
	}

	public void Borrar()
	{
		ini = fin = null;
		System.out.println("Lista de alumnos borrada...");
	}
}
