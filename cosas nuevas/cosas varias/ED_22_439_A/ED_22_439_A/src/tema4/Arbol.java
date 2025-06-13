package tema4;

import ed.Datos;
import java.util.LinkedList;

public class Arbol
{
	private Nodo2 raiz, nvo, act, ant, may, suc, asu;
	private Datos obd = new Datos();
	private LinkedList<Nodo2> lista = new LinkedList<Nodo2>();
	private LinkedList<Integer> listao = new LinkedList<Integer>();
	private int nivel;

	public Arbol()
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
				&& act.getNum() != nvo.getNum(); ant = act, act = nvo.getNum() > act.getNum() ? act.getDer()
						: act.getIzq(), nivel++)
			;
	}

	private void ListaOrdenada(Nodo2 act)
	{
		if (act != null)
		{
			this.ListaOrdenada(act.getIzq());
			listao.addLast(act.getNum());
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

	public void Insertar(int num)
	{
		nvo = new Nodo2(num);
		if (!this.Vacio())
		{
			this.Existe();
			if (nvo.getNum() > ant.getNum())
				ant.setDer(nvo);
			else
				ant.setIzq(nvo);
		}
		else
			raiz = nvo;
	}

	public void Insertar()
	{
		nvo = new Nodo2(obd.Entero("Ingresa un numero"));
		if (!this.Vacio())
		{
			this.Existe();
			if (act == null)
				if (nvo.getNum() > ant.getNum())
					ant.setDer(nvo);
				else
					ant.setIzq(nvo);
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
			nvo = new Nodo2(obd.Entero("Ingresa un numero a extraer"));
			this.Existe();
			if (act == null)
			{
				if (act.getDer() != null && act.getIzq() != null)
				{
					for (may = act.getIzq(), nvo = may; may.getDer() != null; may = may.getDer())
						;
					may.setDer(act.getDer());
				}
				else
					if (act.getIzq() != null)
						nvo = act.getIzq();
					else
						nvo = act.getDer();
				if (ant != raiz)
					if (ant.getIzq() == act)
						ant.setIzq(nvo);
					else
						ant.setDer(nvo);
				else
					raiz = nvo;
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
			nvo = new Nodo2(obd.Entero("Ingresa un numero a extraer"));
			this.Existe();
			if (act == null)
			{
				if (act.getIzq() != null)
				{
					for (suc = act.getIzq(), asu = act; suc.getDer() != null; asu = act, suc = suc.getDer())
						;
					act.setNum(suc.getNum());
					if (asu == act)
						act.setIzq(suc.getIzq());
					else
						asu.setDer(act.getIzq());
				}
				else
					if (act.getDer() != null)
					{
						for (suc = act.getDer(), asu = act; suc.getIzq() != null; asu = act, suc = suc.getIzq())
							;
						act.setNum(suc.getNum());
						if (asu == act)
							act.setDer(suc.getDer());
						else
							asu.setIzq(act.getDer());
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
				System.out.println(act.getNum());
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
				System.out.println(act.getNum());
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
			nvo = new Nodo2(obd.Entero("Cual es el numero a buscar"));
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
