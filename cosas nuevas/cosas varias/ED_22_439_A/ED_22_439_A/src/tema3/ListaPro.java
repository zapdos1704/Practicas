package tema3;

import java.util.LinkedList;
import ed.Datos;

public class ListaPro
{
	private Datos obd = new Datos();
	private LinkedList<MateriaOp> lista = new LinkedList<MateriaOp>();
	private MateriaOp mat;
	private int pos, clv, may, con;

	private int Clave()
	{
		if (!lista.isEmpty())
		{
			may = 0;
			for (pos = 1; pos < lista.size(); pos++)
				if (lista.get(pos).Clave() > lista.get(pos - 1).Clave())
					may = pos;
			return lista.get(may).Clave() + 1;
		}
		else
			return 1;
	}

	private void Alfabeto(MateriaOp mata)
	{
		may = -1;
		for (pos = 0; pos < lista.size(); pos++)
			if (mata.Nombre().compareTo(lista.get(pos).Nombre()) >= 0)
				may = pos;
		lista.add(may + 1, mata);
	}

	public void Insertar()
	{
		mat = new MateriaOp();
		mat.Nueva(this.Clave());
		if (!lista.isEmpty())
			this.Alfabeto(mat);
		else
			lista.add(mat);

	}

	public void Extraer()
	{
		if (!lista.isEmpty())
		{
			do
				clv = obd.Entero("Clave de la materia a extraer");
			while (clv < 1 || clv > this.Clave() - 1);
			for (pos = 0; pos < lista.size(); pos++)
				if (lista.get(pos).Clave() == clv)
				{
					System.out.println("Materia extraido de la lista");
					lista.remove(pos).Mostrar();
					break;
				}
			if (pos > lista.size())
				System.out.println("La materia no se encontro en la lista...");
		}
		else
			System.out.println("Lista de materias vacia...");
	}

	public void Recorrido()
	{
		if (!lista.isEmpty())
		{
			System.out.println("lista de materias");
			for (MateriaOp mat : lista)
				mat.Mostrar();
			System.out.println("fin de la lista...");
		}
		else
			System.out.println("lista de materias vacia...");
	}

	public void Buscar()
	{
		if (!lista.isEmpty())
		{
			do
				clv = obd.Entero("clave de la materia a buscar...");
			while (clv < 1 || clv > this.Clave() - 1);
			for (pos = 0; pos < lista.size(); pos++)
				if (lista.get(pos).Clave() == clv)
				{
					System.out.println("materia encontrado en la lista");
					lista.get(pos).Mostrar();
					break;
				}
			if (pos == lista.size())
				System.out.println("la materia no se encontro en la lista...");
		}
		else
			System.out.println("lista de materias vacia...");
	}

	public void Modificar()
	{
		boolean ban = true;
		if (!lista.isEmpty())
		{
			mat = new MateriaOp();
			do
				clv = obd.Entero("clave de la materia a buscar...");
			while (clv < 1 || clv > this.Clave() - 1);
			for (con = 0; con < lista.size(); con++)
			{
				if (lista.get(con).Clave() == clv)
				{
					lista.get(con).Modificar();
					this.Alfabeto(lista.get(con));
					lista.remove(con);
					ban = false;
					break;
				}
			}
			if (ban)
				System.out.println("la materia no se encontro en la lista...");
		}
		else
			System.out.println("lista de materias vacia...");

	}
}