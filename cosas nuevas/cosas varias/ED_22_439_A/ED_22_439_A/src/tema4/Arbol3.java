package tema4;

import java.util.LinkedList;

import ed.Datos;

public class Arbol3
{
	private Nodo3 raiz, nvo, act, ant, may, suc, asu;
	private Datos obd = new Datos();
	private LinkedList<Nodo3> lista = new LinkedList<Nodo3>();
	private LinkedList<String> listao = new LinkedList<String>();
	private int nivel;

	public Arbol3()
	{
		raiz = null;
	}

	private boolean Vacio()
	{
		return raiz == null;
	}

	private void Existe()
	{
		for (act = raiz, ant = null; act != null
				&& !act.getCad().equals(nvo.getCad()); ant = act, act = nvo.getCad().compareTo(act.getCad()) > 0
						? act.getDer()
						: act.getIzq(), nivel++)
			;
	}

	private void ListaOrdenada(Nodo3 act)
	{
		if (act != null)
		{
			this.ListaOrdenada(act.getIzq());
			listao.addLast(act.getCad());
			this.ListaOrdenada(act.getDer());
		}

	}

	private void Balanceoo(int ini, int fin)
	{
		int cen;
		if (ini <= fin)
		{
			cen = (ini + fin) / 2;
			this.Insertar(listao.get(cen));
			this.Balanceoo(ini, cen - 1);
			this.Balanceoo(cen + 1, fin);
		}
	}

	public void Insertar(String cad)
	{
		nvo = new Nodo3(cad);
		if (!this.Vacio())
		{
			this.Existe();
			if (nvo.getCad().compareTo(ant.getCad()) > 0)
				ant.setDer(nvo);
			else
				ant.setIzq(nvo);
			nvo.setPad(ant);
		}
		else
			raiz = nvo;
	}

	public void Insertar()
	{
		nvo = new Nodo3(obd.Cadena("Ingrese una cadena"));
		if (!this.Vacio())
		{
			this.Existe();
			if (act == null)
			{
				if (nvo.getCad().compareTo(ant.getCad()) > 0)
					ant.setDer(nvo);
				else
					ant.setIzq(nvo);
				nvo.setPad(ant);
				System.out.println("El nodo fue agregado exitosamente...");
			}
			else
				System.out.println("El nodo ya existe...");
		}
		else
			raiz = nvo;
	}

	public void ExtraerFusion()
	{
		if (!this.Vacio())
		{
			nvo = new Nodo3(obd.Cadena("Cadena a eliminar"));
			this.Existe();
			if (act == null)
			{
				if (act.getDer() != null && act.getIzq() != null)
				{
					for (may = act.getIzq(), nvo = may; may.getDer() != null; may = may.getDer())
						;
					may.setDer(act.getDer());
					act.getDer().setPad(may);
				}
				else
				{
					if (act.getIzq() != null)
						nvo = act.getIzq();
					else
						nvo = act.getDer();
					nvo.setPad(ant);
				}
				if (ant != raiz)
				{
					if (ant.getIzq() == act)
						ant.setIzq(nvo);
					else
						ant.setDer(nvo);
					nvo.setPad(ant);
				}
				else
				{
					raiz = nvo;
					raiz.setPad(null);
				}
			}
			else
				System.out.println("El nodo ya existe...");
		}
		else
			System.out.println("El arbol esta vacio...");
	}

	public void EliminarCopiado()
	{
		if (!this.Vacio())
		{
			nvo = new Nodo3(obd.Cadena("Ingrese una cadena"));
			this.Existe();
			if (act == null)
			{
				if (act.getIzq() != null)
				{
					for (suc = act.getIzq(), asu = act; suc.getDer() != null; asu = act, suc = suc.getDer())
						;
					act.setCad(suc.getCad());
					if (asu == act)
						act.setIzq(suc.getIzq());
					else
						asu.setDer(act.getIzq());
					if (suc.getIzq() != null)
						suc.getIzq().setPad(suc.getPad());
				}
				else
					if (act.getDer() != null)
					{
						for (suc = act.getDer(), asu = act; suc.getIzq() != null; asu = act, suc = suc.getIzq())
							;
						act.setCad(suc.getCad());
						if (asu == act)
							act.setDer(suc.getDer());
						else
							asu.setIzq(act.getDer());
						if (suc.getDer() != null)
							suc.getDer().setPad(suc.getPad());
					}
					else
						if (ant != null)
							if (ant.getIzq() == act)
								ant.setIzq(null);
							else
								ant.setDer(null);
						else
							raiz = null;
			}
			else
				System.out.println("El nodo ya existe...");
		}
		else
			System.out.println("El arbol esta vacio...");
	}

	public void RecorridoAmplitud()
	{
		if (!this.Vacio())
		{
			lista.addLast(raiz);
			System.out.println("Contenido del arbol...");
			while (!lista.isEmpty())
			{
				act = lista.removeFirst();
				System.out.println(act.getCad());
				if (act.getIzq() != null)
					lista.addLast(act.getIzq());
				if (act.getDer() != null)
					lista.addLast(act.getDer());
			}
			System.out.println("Fin del arbol...");
		}
		else
			System.out.println("El arbol esta vacio...");
	}

	public void RecorridoProfundidad()
	{
		if (!this.Vacio())
		{
			System.out.println("Contenido del arbol...");
			lista.addLast(raiz);
			while (!lista.isEmpty())
			{
				act = lista.removeLast();
				System.out.println(act.getCad());
				if (act.getDer() != null)
					lista.add(act.getDer());
				if (act.getIzq() != null)
					lista.add(act.getIzq());
			}
			System.out.println("Fin del arbol...");
		}
		else
			System.out.println("El arbol esta vacio...");
	}

	public void Busqueda()
	{
		if (!this.Vacio())
		{
			nvo = new Nodo3(obd.Cadena("Ingrese una cadena"));
			this.Existe();
			if (act != null)
				System.out.println("Numero encontrado en el nivel " + nivel);
			else
				System.out.println("El numero no esta en el arbol...");
		}
		else
			System.out.println("El arbol esta vacio...");
	}

	public void Balanceo()
	{
		listao.clear();
		if (!this.Vacio())
		{
			this.ListaOrdenada(raiz);
			raiz = null;
			this.Balanceoo(0, listao.size() - 1);
		}
	}

	public void Borrar()
	{
		raiz = null;
		System.out.println("Arbol borrado...");
	}
}
