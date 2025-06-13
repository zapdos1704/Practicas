package trabajos;

import java.util.Stack;
import java.util.Vector;
import ed.*;

public class Cuentas
{
	private Stack<Cuenta> pila, aux;
	private Vector<Movimientos> cola = new Vector<Movimientos>(5, 2);
	private Datos obd = new Datos();
	private Formato obf = new Formato();
	private Cuenta cue;
	private Movimientos mov;
	private double dep, ret;
	private int clv;
	private boolean ban;

	public Cuentas()
	{
		pila = new Stack<Cuenta>();
		aux = new Stack<Cuenta>();
	}

	private void Titulos()
	{
		System.out.println(obf.Izq("CLV", 5) + " | " + obf.Izq("NOMBRE", 25) + " | " + obf.Izq("FECHA", 20) + " | "
				+ obf.Izq("SALDO", 13));
	}

	private void Titulos2()
	{
		System.out.println("\n\t" + obf.Izq("CLV", 5) + " | " + obf.Izq("TIPO", 10) + " | " + obf.Izq("MONTO", 13)
				+ " | " + obf.Izq("FECHA", 20));
	}

	private boolean Vacia()
	{
		return pila.empty();
	}

	private int Clave()
	{
		if (!pila.empty())
			return pila.peek().Clave() + 1;
		else
			return 1;
	}

	private void Intercambio()
	{
		while (!aux.empty())
			pila.push(aux.pop());
	}

	public void Insertar()
	{
		cue = new Cuenta(this.Clave());
		pila.push(cue);
		cola.add(new Movimientos(cue.Clave(), "DEPOSITO", cue.Saldo(), cue.Fecha()));
		System.out.println("Cuenta agregada exitosamente...");
	}

	public void Consultar()
	{
		if (!this.Vacia())
		{
			ban = true;
			do
				clv = obd.Entero("Cuenta a buscar");
			while (clv < 1);
			while (!this.Vacia())
			{
				cue = pila.pop();
				if (cue.Clave() == clv)
				{
					this.Titulos();
					cue.Mostrar();
					for (Movimientos movs : cola)
						if (clv == movs.Clave())
						{
							if (ban)
								this.Titulos2();
							movs.Mostrar();
							ban = false;
						}
					ban = false;
					aux.push(cue);
					break;
				}
				aux.push(cue);
			}
			if (ban)
				System.out.println("No hay niniguna cuenta con ese numero de cuenta...");
			this.Intercambio();
		}
		else
			System.out.println("No hay cuentas registradas...");
	}

	public void Deposito()
	{
		if (!this.Vacia())
		{
			ban = true;
			do
				clv = obd.Entero("Cuenta a buscar");
			while (clv < 1);
			while (!pila.empty())
			{
				cue = pila.pop();
				System.out.println();
				if (clv == cue.Clave())
				{
					do
						dep = obd.Doble("Monto del deposito");
					while (dep < 1);
					mov = new Movimientos(cue.Clave(), "DEPOSITO", dep, cue.Fec());
					dep = cue.Saldo() + mov.Monto();
					cue.Saldo(dep);
					cola.add(mov);
					aux.push(cue);
					ban = false;
					break;
				}
				aux.push(cue);
			}
			if (ban)
				System.out.println("No hay niniguna cuenta con ese numero de cuenta...");
			this.Intercambio();
			System.out.println();
		}
		else
			System.out.println("No hay cuentas registradas...");
	}

	public void Retiro()
	{
		if (!this.Vacia())
		{
			ban = true;
			do
				clv = obd.Entero("Cuenta a buscar");
			while (clv < 1);
			while (!pila.empty())
			{
				cue = pila.pop();
				System.out.println();
				if (clv == cue.Clave())
				{
					do
						ret = obd.Doble("Monto del deposito");
					while (ret < 1 || ret > cue.Saldo());
					mov = new Movimientos(cue.Clave(), "RETIRO", ret, cue.Fec());
					ret = cue.Saldo() - mov.Monto();
					cue.Saldo(ret);
					cola.add(mov);
					aux.push(cue);
					ban = false;
					break;
				}
				aux.push(cue);
			}
			if (ban)
				System.out.println("No hay niniguna cuenta con ese numero de cuenta...");
			this.Intercambio();
			System.out.println();
		}
		else
			System.out.println("No hay cuentas registradas...");
	}
}
