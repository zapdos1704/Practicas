package tema3;

import ed.Datos;

public class PilaPromedios
{
	private String pila[], cal;
	private int pos, tam;
	private Datos obd = new Datos();

	public PilaPromedios()
	{
		do
			tam = obd.Entero("Cual es el tamaño de la pila");
		while (tam < 1);
		pila = new String[tam];
		tam = -1;
	}

	private boolean Vacia()
	{
		return tam == -1;
	}

	private boolean Llena()
	{
		return tam == pila.length - 1;
	}

	public void Insertar()
	{
		int num;
		if (!this.Llena())
		{
			do
				num = obd.Entero("Nueva calificación");
			while (num < 0 || num > 100);
			cal = num < 70 ? "NA" : num + "";
			pila[++tam] = cal;
			System.out.println("Calificación almacenada en la pila");
		}
		else
			System.out.println("No se pueden ingresar mas calificaciones");
	}

	public void Extraer()
	{
		if (!this.Vacia())
			System.out.println("Calificación extraida " + pila[tam--]);
		else
			System.out.println("No hay elementos que extraer");
	}

	public void Recorrido()
	{
		if (!this.Vacia())
		{
			System.out.println("Elementos de la pila");
			for (pos = tam; pos >= 0; pos--)
				System.out.println(pila[pos]);
			System.out.println("Fin de la pila");
		}
		else
			System.out.println("No hay elementos en la pila");

	}

	public void Busqueda()
	{
		int con = 0;
		if (!this.Vacia())
		{
			cal = obd.Cadena("Cual es la calificación a buscar").toUpperCase();
			for (pos = tam; pos >= 0; pos--)
				if (pila[pos].equals(cal))
					con++;
			System.out.println("Cantidad de " + cal + " es: " + con);
		}
		else
			System.out.println("No hay elementos en la pila a buscar");
	}

	public void Eliminar()
	{
		/*
		 * if (!this.Vacia()) tam=-1; else
		 * System.out.println("No hay elementos en la pila");
		 */
		boolean ban = false;
		if (!this.Vacia())
		{
			cal = obd.Cadena("Cual es la calificación a buscar").toUpperCase();
			for (pos = tam; pos >= 0; pos--)
				if (pila[pos].equals(cal))
				{
					for (; pos < tam; pos++)
						pila[pos] = pila[pos + 1];
					tam--;
					ban = true;
					break;
				}
			if (ban)
				System.out.println("La calificación no existe");
		}
		else
			System.out.println("No hay elementos en la pila");
	}
}
