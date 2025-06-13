package trabajos;

import java.util.LinkedList;

public class ArbolA
{
	private NodoA raiz, nvo, act, ant;
	private LinkedList<NodoA> lista = new LinkedList<NodoA>();
	private int nivel;

	public ArbolA()
	{
		raiz = null;
	}

	public boolean Vacio()
	{
		return raiz == null;
	}

	public int Clave()
	{
		int num = 0;
		if (!this.Vacio())
		{
			for (act = raiz; act != null; act = act.getDer(),num = act.getArt().Clave())
				;
		}
		return num + 1;
	}

	private void Existe()
	{
		for (act = raiz, ant = null, nivel = 0; act != null
				&& act.getArt().Clave() != nvo.getArt().Clave(); ant = act, act = act.getDer(), nivel++)
			;
	}

	public void Insertar()
	{
		nvo = new NodoA(new Articulo(this.Clave()));
		if (!this.Vacio())
		{
			this.Existe();
			if (act == null)
				ant.setDer(nvo);
			else
				System.out.println("El nodo ya existe...");
		}
		else
			raiz = nvo;
	}

	public void Recorrer()
	{
		if (!this.Vacio())
		{
			lista.addLast(raiz);
			System.out.println("Contenido del arbol...");
			while (!lista.isEmpty())
			{
				act = lista.removeFirst();
				act.getArt().Mostrar();
				if (act.getDer() != null)
					lista.addLast(act.getDer());
			}
			System.out.println("Fin del arbol...");
		}
		else
			System.out.println("El arbol esta vacio...");
	}

	public void Buscar()
	{
		if (!this.Vacio())
		{
			nvo = new NodoA(new Articulo());
			this.Existe();
			if (act != null)
			{
				System.out.println("Numero encontrado en el nivel " + nivel);
				act.getArt().Mostrar();
			}
			else
				System.out.println("El numero no esta en el arbol...");
		}
		else
			System.out.println("El arbol esta vacio...");
	}

	public void Eliminar()
	{
		if (!this.Vacio())
		{
			nvo = new NodoA(new Articulo());
			this.Existe();
			if (act != null)
			{
				System.out.println("Trabajador eliminado...");
				act.getArt().Mostrar();
				nvo = act.getDer();
				if (act != raiz)
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
}
