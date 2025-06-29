package tema4;

import java.util.LinkedList;
import ed.Datos;

public class ArbolTra
{
	private NodoTra raiz, nvo, act, ant, may, suc, asu;
	private LinkedList<NodoTra> lista = new LinkedList<NodoTra>();
	private LinkedList<Trabajador> listao = new LinkedList<Trabajador>();
	private Datos obd = new Datos();
	private int nivel;

	public ArbolTra()
	{
		raiz = null;
	}

	private boolean Vacio()
	{
		return raiz == null;
	}

	public int NoTarjeta()
	{
		int num = 0;
		if (!this.Vacio())
		{
			listao.clear();
			this.ListaOrdenada(raiz);
			for (Trabajador tra : listao)
				if (tra.NoTarjeta() > num)
					num = tra.NoTarjeta();
		}
		return num + 1;
	}

	private void Existe()
	{
		for (act = raiz, ant = null, nivel = 0; act != null && !act.getTra().Nombre().equals(
				nvo.getTra().Nombre()); ant = act, act = nvo.getTra().Nombre().compareTo(act.getTra().Nombre()) > 0
						? act.getDer()
						: act.getIzq(), nivel++)
			;
	}

	private void ListaOrdenada(NodoTra act)
	{
		if (act != null)
		{
			this.ListaOrdenada(act.getIzq());
			listao.addLast(act.getTra());
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

	public void Insertar(Trabajador tra)
	{
		nvo = new NodoTra(tra);
		if (!this.Vacio())
		{
			this.Existe();
			if (nvo.getTra().Nombre().compareTo(ant.getTra().Nombre()) > 0)
				ant.setDer(nvo);
			else
				ant.setIzq(nvo);
		}
		else
			raiz = nvo;
	}

	public void Insertar()
	{
		nvo = new NodoTra(new Trabajador(this.NoTarjeta()));
		if (!this.Vacio())
		{
			this.Existe();
			if (act == null)
				if (nvo.getTra().Nombre().compareTo(ant.getTra().Nombre()) > 0)
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
			nvo = new NodoTra(new Trabajador(obd.Cadena("Nombre a extraer")));
			this.Existe();
			if (act != null)
			{
				System.out.println("Trabajador eliminado...");
				nvo.getTra().Mostrar();
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
				System.out.println("El nodo no existe...");
		}
		else
			System.out.println("El arbol esta vacio...");
	}

	public void EliminarCopiado()
	{
		if (!this.Vacio())
		{
			nvo = new NodoTra(new Trabajador(obd.Cadena("Nombre a eliminar")));
			this.Existe();
			if (act != null)
			{
				System.out.println("Trabajador eliminado...");
				nvo.getTra().Mostrar();
				if (act.getIzq() != null)
				{
					for (suc = act.getIzq(), asu = act; suc.getDer() != null; asu = act, suc = suc.getDer())
						;
					act.setTra(suc.getTra());
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
						act.setTra(suc.getTra());
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
				System.out.println("El nodo no existe...");
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
				act.getTra().Mostrar();
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
				act.getTra().Mostrar();
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
			nvo = new NodoTra(new Trabajador(obd.Cadena("Nombre a buscar")));
			this.Existe();
			if (act != null)
			{
				System.out.println("Numero encontrado en el nivel " + nivel);
				nvo.getTra().Mostrar();
			}
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
