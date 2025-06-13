package tema3;

import ed.Datos;
import java.util.Stack;

public class PilaEdads
{
	private int edad;
	private Datos obd = new Datos();
	private Stack<Integer> pila, aux;

	private void Intercambio()
	{
		if (!pila.empty())
			pila.push(aux.pop());
	}

	public PilaEdads()
	{
		pila = new Stack<Integer>();
		aux = new Stack<Integer>();
	}

	public void Insertar()
	{
		do
			edad = obd.Entero("Nueva edad");
		while (edad < 0);
		pila.push(edad);
		System.out.println("Edad almacenada con exito...");
	}

	public void Extraer()
	{
		if (!pila.empty())
			System.out.println("Edad extraida " + pila.pop());
		else
			System.out.println("Pila vacia...");
	}

	public void Recorrido()
	{
		if (!pila.empty())
		{
			System.out.println("Contenido de la pila...");
			while (!pila.empty())
			{
				edad = pila.pop();
				System.out.println(edad);
				aux.push(edad);
			}
			System.out.println("Fin de la pila...");
			this.Intercambio();
		}
		else
			System.out.println("Pila vacia...");
	}

	public void Buscar()
	{
		int con = 0, bus;
		if (!pila.empty())
		{
			do
				bus = obd.Entero("Edad a buscar");
			while (bus < 0);
			while (!pila.empty())
			{
				edad = pila.pop();
				if (bus == edad)
					con++;
				aux.push(edad);
			}
			System.out.println("La edad se encontro " + con + " veces...");
			this.Intercambio();
		}
		else
			System.out.println("Pila vacia...");
	}

	public void Borrar()
	{
		pila = new Stack<Integer>();
		System.out.println("Pila borrada...");
	}
}
