package tema3;

import java.util.Stack;
import ed.*;

public class PilaTraStack
{
	private Stack<Trabajador> pila, aux;
	private Datos obd = new Datos();
	private int nt;
	private String nom;
	private double sue1, sue2;
	private boolean ban;
	private Trabajador ref;

	private void Mensaje()
	{
		Formato obf = new Formato();
		System.out.println(obf.Izq("NUMT", 5) + " - " + obf.Izq("NOMBRE", 25) + " - " + obf.Der("SUELDO", 12));
	}

	private int Clave()
	{
		if (!pila.empty())
			return pila.peek().NoTarjeta() + 1;
		else
			return 1;
	}

	private void Intercambio()
	{
		if (!pila.empty())
			pila.push(aux.pop());
	}

	public PilaTraStack()
	{
		pila = new Stack<Trabajador>();
		aux = new Stack<Trabajador>();
	}

	public void Insertar()
	{
		pila.push(new Trabajador(this.Clave()));
		System.out.println("Trabajador agregado correctamente...");
	}

	public void Extraer()
	{
		if (!pila.empty())
		{
			System.out.println("Trabajador eliminado...");
			this.Mensaje();
			pila.pop().Mostrar();
		}
		else
			System.out.println("No hay trabajadores en la pila...");
	}

	public void Recorrido()
	{
		if (!pila.empty())
		{
			ban = true;
			System.out.println("Lista de trabajadores...");
			while (!pila.empty())
			{
				if (ban)
				{
					System.out.println();
					this.Mensaje();
				}
				ref = pila.pop();
				aux.push(ref);
			}
			System.out.println("Fin de la lista...");
			this.Intercambio();
		}
		else
			System.out.println("No hay trabajadores en la pila...");
	}

	public void Busqueda(int op)
	{
		if (!pila.empty())
		{
			ban = true;
			switch (op)
			{
				case 1:
					do
						nt = obd.Entero("No. de tarjeta del trabajador a buscar");
					while (nt < 1);
					break;
				case 2:
					nom = obd.Cadena("Nombre o apellido del trabajador a buscar").toUpperCase();
					break;
				case 3:
					do
					{
						sue1 = obd.Doble("Sueldo inicial");
						sue2 = obd.Doble("Sueldo final");
					}
					while (sue1 < 1 || sue1 > sue2);
			}
			while (!pila.empty())
			{
				ref = pila.pop();
				if (op == 1)
				{
					if (ref.NoTarjeta() == nt)
					{
						System.out.println("Trabajador encontrado...");
						this.Mensaje();
						ref.Mostrar();
						ban = false;
						break;
					}
				}
				if (op == 2)
				{
					if (ref.Nombre().contains(nom))
					{
						if (ban)
						{
							System.out.println("Trabajadores encontrados...");
							this.Mensaje();
						}
						ref.Mostrar();
						ban = false;
					}
				}
				if (op == 3)
				{
					if (ref.Sueldo() >= sue1 && ref.Sueldo() <= sue2)
					{
						if (ban)
						{
							System.out.println("Lista de trabajadores encontrados...");
							this.Mensaje();
						}
						ref.Mostrar();
						ban = false;
					}
				}
				aux.push(ref);
			}
			if (ban)
				System.out.println("Lo que busca no existe...");
			this.Intercambio();
		}
		else
			System.out.println("No hay trabajadores en la pila...");
	}

	public void Modificar()
	{
		if (!pila.empty())
		{
			do
				nt = obd.Entero("No. de tarjeta del trabajador a modificar");
			while (nt < 1);
			while (!pila.empty())
			{
				ref = pila.pop();
				if (ref.NoTarjeta() == nt)
				{
					ref.Modificar();
					aux.push(ref);
					ban = false;
					break;
				}
				aux.push(ref);
			}
			if (ban)
				System.out.println("El trabajador no existe...");
			this.Intercambio();
		}
		else
			System.out.println("No hay trabajadores en la pila...");
	}

	public void Borrar()
	{
		pila = new Stack<Trabajador>();
		System.out.println("Pila borrada...");
	}
}
