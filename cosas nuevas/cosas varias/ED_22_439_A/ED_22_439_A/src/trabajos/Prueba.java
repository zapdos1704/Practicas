package trabajos;

import java.util.Stack;
import java.util.Vector;

import ed.Datos;
import ed.Formato;

public class Prueba
{
	private int nt, tot, op, val;
	private boolean ban, ban2;
	private String msj;
	private Cuentaa c;
	private Datos obd = new Datos();
	private Formato obf = new Formato();
	private Stack<Cuentaa> pila, aux;
	private Vector<Movimiento> cola = new Vector<Movimiento>(5, 1);

	public Prueba()
	{
		pila = new Stack<Cuentaa>();
		aux = new Stack<Cuentaa>();
	}

	private int NoCuenta()
	{
		if (!pila.empty())
			return pila.peek().NoCuenta() + 1;
		else
			return 1;
	}

	private void TituloC()
	{
		System.out.println();
		System.out.println(obf.Izq("NO.CUENTA", 10) + " | " + obf.Izq("NOMBRE", 25) + " | " + obf.Izq("FECHA", 12)
				+ " | " + obf.Izq("SALDO", 20));
	}

	private void TituloM()
	{
		System.out.println(obf.Der("MOVIMIENTO", 10) + " | " + obf.Izq("FECHA", 10) + " | " + obf.Izq("MONTO", 10));
	}

	private void Intercambio()
	{
		while (!aux.empty())
			pila.push(aux.pop());
	}

	public void Insertar()
	{
		pila.push(new Cuentaa(this.NoCuenta()));
		System.out.println();
	}

	public void Consultar()
	{
		ban = true;
		ban2 = true;
		if (op == 3 || op == 4)
			ban2 = false;
		if (!pila.empty())
		{
			do
				nt = obd.Entero("No. de cuenta a consultar: ");
			while (nt < 1);

			while (!pila.empty())
			{
				c = pila.pop();
				if (c.NoCuenta() == nt)
				{
					this.TituloC();
					if (ban2)
						c.Mostrar(c.Saldo());
					else
						c.Mostrar(this.Recorrido());
					System.out.println("Movimientos de la cuenta...");
					this.TituloM();
					System.out.println(obf.Der("Deposito", 10) + " | " + obf.Izq(c.Fecha(), 10) + " | "
							+ obf.Izq(obf.Pesos(c.Saldo()), 10));
					this.Buscar(nt);
					ban = false;
					aux.push(c);
					break;
				}
				aux.push(c);
			}
			if (ban)
				System.out.println("La cuenta no existe...");
			this.Intercambio();
		}
		else
			System.out.println("No hay cuentas en la pila...");
	}

	public void Operacion(int op)
	{
		ban = true;
		if (op == 3)
			msj = "Deposito";
		else
			msj = "Retiro";
		if (!pila.empty())
		{
			do
				nt = obd.Entero("No. de cuenta a hacer el  " + msj + ": ");
			while (nt < 1);

			while (!pila.empty())
			{
				c = pila.pop();
				if (c.NoCuenta() == nt)
				{
					this.InsertarCola(msj);
					ban = false;
					aux.push(c);
					break;
				}
				aux.push(c);
			}
			if (ban)
				System.out.println("La cuenta no existe...");
			this.Intercambio();
		}
		else
			System.out.println("No hay cuentas en la pila...");
	}

	private boolean Vacia()
	{
		return cola.isEmpty();
	}

	public void InsertarCola(String msj)
	{
		cola.add(new Movimiento(msj, this.NoCuenta(), this.Recorrido()));
	}

	public int Recorrido()
	{
		ban = true;
		if (op == 3 || op == 4)
			ban = false;
		if (!this.Vacia())
		{
			for (Movimiento mov : cola)
			{
				if (ban)
					mov.Mostrar();
				else
					op = 0;
				if (mov.Tipo().equals("Deposito"))
					tot += mov.Monto();
				else
					tot -= mov.Monto();
			}
			tot += c.Saldo();
			val = tot;
			System.out.println(val);
			tot = 0;
		}
		return val;
	}

	public void Buscar(int cta)
	{
		if (!this.Vacia())
			for (Movimiento mov : cola)
				if (mov.Ncta() == cta)
				{
					this.Recorrido();
					break;
				}
	}
}
