package tema4;

import java.util.LinkedList;
import ed.Datos;

public class ArbolCue
{
	private NodoCue raiz, ant, act, nvo, may;
	private int nivel;
	private double mon;
	private Datos obd = new Datos();
	private LinkedList<Cuenta> listao = new LinkedList<Cuenta>();
	private LinkedList<NodoCue> lista = new LinkedList<NodoCue>();

	public ArbolCue()
	{
		raiz = null;
	}

	private boolean Vacio()
	{
		return raiz == null;
	}

	private boolean Existe()
	{
		for (ant = null, act = raiz, nivel = 1; act != null && act.getCue().NoCuenta() != nvo.getCue()
				.NoCuenta(); ant = act, act = nvo.getCue().NoCuenta() > act.getCue().NoCuenta() ? act.getDer()
						: act.getIzq(), nivel++)
			;
		return act != null;
	}

	private int NoCuenta()
	{
		int cue = 0;
		if (!this.Vacio())
			for (act = raiz; act != null; cue = act.getCue().NoCuenta(), act = act.getDer())
				;
		return cue + 1;
	}

	private void ListaOrdenada(NodoCue act)
	{
		if (act != null)
		{
			this.ListaOrdenada(act.getIzq());
			listao.addLast(act.getCue());
			this.ListaOrdenada(act.getDer());
		}
	}

	private void Balanceo(int ini, int fin)
	{
		int cen;
		if (ini <= fin)
		{
			cen = (ini + fin) / 2;
			this.Insertar(listao.get(cen));
			this.Balanceo(ini, cen - 1);
			this.Balanceo(cen + 1, fin);
		}
	}

	public void Insertar()
	{
		nvo = new NodoCue(new Cuenta(this.NoCuenta()));
		if (!this.Vacio())
		{
			if (!this.Existe())
			{
				if (nvo.getCue().NoCuenta() < ant.getCue().NoCuenta())
					ant.setIzq(nvo);
				else
					ant.setDer(nvo);
				nvo.setPad(ant);
			}
		}
		else
			raiz = nvo;
		System.out.println("Cuenta registrada exitosamente..." + nivel);
	}

	public void Insertar(Cuenta cue)
	{
		nvo = new NodoCue(cue);
		if (!this.Vacio())
		{
			this.Existe();

			if (nvo.getCue().NoCuenta() < ant.getCue().NoCuenta())
				ant.setIzq(nvo);
			else
				ant.setDer(nvo);
			nvo.setPad(ant);
		}
		else
			raiz = nvo;
		System.out.println("Cuenta registrada exitosamente...");
	}

	public void Recorrido()
	{
		if (!this.Vacio())
		{
			System.out.println("Lista de cuentas...");
			lista.addLast(raiz);
			while (!lista.isEmpty())
			{
				act = lista.removeFirst();
				act.getCue().MostrarT();
				if (act.getIzq() != null)
					lista.addLast(act.getIzq());
				if (act.getDer() != null)
					lista.addLast(act.getDer());
			}
		}
		else
			System.out.println("No hay ninguna cuenta registrada...");
	}

	public void Eliminar()
	{
		if (!this.Vacio())
		{
			nvo = new NodoCue(new Cuenta("eliminar"));
			if (this.Existe())
			{
				if (act.getIzq() != null && act.getDer() != null)
				{
					for (may = act.getIzq(), nvo = may; may.getDer() != null; may = act.getDer())
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
				}
				if (act != raiz)
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
				System.out.println("La cuenta no existe...");
		}
		else
			System.out.println("No hay ninguna cuenta registrada...");
	}

	public void Busqueda()
	{
		if (!this.Vacio())
		{
			nvo = new NodoCue(new Cuenta("buscar"));
			if (this.Existe())
			{
				act.getCue().Mostrar();
			}
			else
				System.out.println("La cuenta no existe...");
		}
		else
			System.out.println("No hay cuentas registradas...");
	}

	public void Deposito()
	{
		if (!this.Vacio())
		{
			nvo = new NodoCue(new Cuenta("depositar"));
			if (this.Existe())
			{
				System.out.println("Datos de la cuenta...");
				act.getCue().Mostrar();
				do
					mon = obd.Doble("Cantidad a depositar");
				while (mon < 1);
				act.getCue().Movimiento(mon);
				act.getCue().Mostrar();
			}
			else
				System.out.println("La cuenta no existe...");
		}
		else
			System.out.println("No hay cuentas registradas...");
	}

	public void Retiro()
	{
		if (!this.Vacio())
		{
			nvo = new NodoCue(new Cuenta("retirar"));
			if (this.Existe())
			{
				System.out.println("Datos de la cuenta...");
				act.getCue().Mostrar();
				do
					mon = obd.Doble("Cantidad a retirar");
				while (mon < 1 || mon > act.getCue().Saldo());
				act.getCue().Movimiento(mon * -1);
				act.getCue().Mostrar();
			}
			else
				System.out.println("La cuenta no existe...");
		}
		else
			System.out.println("No hay cuentas registradas...");
	}

	public void Balanceo()
	{
		listao.clear();
		if (!this.Vacio())
		{
			this.ListaOrdenada(raiz);
			raiz = null;
			this.Balanceo(0, listao.size() - 1);
		}
		else
			System.out.println("No hay cuentas registradas...");
	}

	public void Borrar()
	{
		raiz = null;
		System.out.println("Se eliminaron todas las cuentas...");
	}
}
