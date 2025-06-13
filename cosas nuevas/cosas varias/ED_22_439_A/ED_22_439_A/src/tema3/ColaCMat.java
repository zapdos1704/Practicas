package tema3;

import ed.Datos;
import ed.Formato;

public class ColaCMat
{
	private MateriaOp cola[];
	private int ini, fin, tam, clv;
	private Datos obd = new Datos();

	public ColaCMat()
	{
		do
			tam = obd.Entero("Tamaño de la cola circular");
		while (tam < 1);
		cola = new MateriaOp[tam];
		ini = -1;
		fin = -1;
	}

	private boolean Vacia()
	{
		return ini == -1;
	}

	private int Clave()
	{
		if (!this.Vacia())
			return cola[fin].Clave() + 1;
		else
			return 1;
	}

	private boolean Llena()
	{
		return ini == 0 && fin == cola.length - 1 || ini - 1 == fin;
	}

	private void Titulos()
	{
		Formato obf = new Formato();
		System.out.println(obf.Izq("CLV", 5) + " | " + obf.Izq("NOMBRE", 25) + " | " + obf.Izq("CREDITOS", 10) + " | "
				+ obf.Izq("PLAN", 6));
	}

	public void Insertar()
	{
		if (!this.Llena())
		{
			clv = this.Clave();
			if (!this.Vacia())
				if (fin + 1 == cola.length)
					fin = 0;
				else
					fin++;
			else
				ini = fin = 0;
			cola[fin] = new MateriaOp();
			cola[fin].Nueva(clv);
			System.out.println("La materia se guardo exitosamente...");
		}
		else
			System.out.println("Cola llena...");
	}

	public void Extraer()
	{
		if (!this.Vacia())
		{
			System.out.println("Numero a extraido " + cola[ini]);
			if (ini == fin)
				ini = fin = -1;
			else
				if (ini == cola.length - 1)
					ini = 0;
				else
					ini++;
		}
		else
			System.out.println("Cola vacia...");
	}

	public void Recorrido()
	{
		if (!this.Vacia())
		{
			System.out.println("Contenido de la cola");
			for (tam = ini; tam != fin; tam = tam == cola.length - 1 ? 0 : tam + 1)
				cola[tam].Mostrar();
			cola[tam].Mostrar();
		}
		else
			System.out.println("Cola vacia...");
	}

	public void Buscar()
	{
		int num;
		if (!this.Vacia())
		{
			do
				num = obd.Entero("Numero a buscar");
			while (num < cola[ini].Clave() || num > cola[fin].Clave());
			for (tam = ini; tam != fin; tam = tam == cola.length - 1 ? 0 : tam + 1)
				if (num == cola[tam].Clave())
				{
					this.Titulos();
					cola[tam].Mostrar();
					break;
				}
			if (num == cola[tam].Clave())
			{
				this.Titulos();
				cola[tam].Mostrar();
			}
		}
		else
			System.out.println("Cola vacia...");
	}

	public void Modificar()
	{
		int num;
		if (!this.Vacia())
		{
			do
				num = obd.Entero("Numero a buscar");
			while (num < cola[ini].Clave() || num > cola[fin].Clave());
			for (tam = ini; tam != fin; tam = tam == cola.length - 1 ? 0 : tam + 1)
				if (num == cola[tam].Clave())
				{
					cola[tam].Modificar();
					break;
				}
			if (num == cola[tam].Clave())
				cola[tam].Modificar();
		}
		else
			System.out.println("Cola vacia...");
	}

	public void Borrar()
	{
		ini = fin = -1;
		System.out.println("Cola circular borrada...");
	}
}
