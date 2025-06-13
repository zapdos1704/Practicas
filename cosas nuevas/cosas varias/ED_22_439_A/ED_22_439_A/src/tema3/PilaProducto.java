package tema3;

import ed.Datos;
import ed.Formato;

public class PilaProducto
{
	private Producto pila[], aux[], obp;
	private int tam, pos, clv;
	private Formato obf = new Formato();
	private Datos obd = new Datos();

	private boolean Vacia()
	{
		return tam == -1;
	}

	private boolean Llena()
	{
		return tam == pila.length - 1;
	}

	private int Clave()
	{
		if (!this.Vacia())
			return pila[tam].Clave() + 1;
		else
			return 1;
	}

	private void Mensaje()
	{
		System.out.println(obf.Izq("CLAVE", 5) + " | " + obf.Izq("NOMBRE", 25) + " | " + obf.Der("PRECIO", 12));
	}

	public PilaProducto()
	{
		do
			tam = obd.Entero("Tamaño de la pila");
		while (tam < 1);
		pila = new Producto[tam];
		aux = new Producto[tam];
		tam = -1;
	}

	public void Insertar()
	{
		if (!this.Llena())
		{
			clv = this.Clave();
			pila[++tam] = new Producto(clv);
			System.out.println("Producto almacenado...");
		}
		else
			System.out.println("No se pueden agregar mas productos a la pila...");
	}

	public void Extraer()
	{
		if (!this.Vacia())
		{
			System.out.println("Producto Extraido...");
			this.Mensaje();
			pila[tam].Mostrar();
			tam--;
		}
		else
			System.out.println("No hay productos en la pila...");
	}

	public void Operaciones(int op)
	{
		boolean ban = true;
		if (!this.Vacia())
		{
			if (op == 4 || op == 5 || op == 6)
				do
					clv = obd.Entero("Clave del producto a " + (op == 4 ? "Buscar" : op == 5 ? "Modificar" : "Borrar"));
				while (clv < 1);
			for (pos = 0; tam >= 0; pos++, tam--)
			{
				obp = pila[tam];
				switch (op)
				{
					case 3:
						if (ban)
						{
							System.out.println("Contenido de la pila");
							this.Mensaje();
						}
						ban = false;
						obp.Mostrar();
						break;
					case 4:
						if (obp.Clave() == clv)
						{
							System.out.println("Producto encontrado");
							this.Mensaje();
							obp.Mostrar();
							ban = false;
						}
						break;
					case 5:
						if (obp.Clave() == clv)
						{
							obp.Modificar();
							ban = false;
						}
						break;
					case 6:
						if (obp.Clave() == clv)
						{
							System.out.println("Producto encontrado");
							this.Mensaje();
							obp.Mostrar();
							ban = false;
						}
						break;
				}
				if (op != 6)
					aux[pos] = obp;
				else
					if (ban)
						aux[pos] = obp;
					else
						pos--;
				if (!ban && (op == 4 || op == 5 || op == 6))
					break;
			}
			if (ban)
				System.out.println("Fin de la pila...");
			for (tam = op == 3 ? 1 : 0, pos += op != 3 ? -1 : 0; pos >= 0; tam++, pos--)
				pila[tam] = aux[pos];
			tam--;
		}
		else
			System.out.println("No hay productos en la pila...");
	}
}