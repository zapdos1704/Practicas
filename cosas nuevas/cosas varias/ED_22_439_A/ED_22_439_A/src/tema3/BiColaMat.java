package tema3;

import ed.Datos;

public class BiColaMat
{
	private int clv, tam, pos;
	private boolean ban;
	private MateriaOp bicola[];
	private Datos obd = new Datos();

	public BiColaMat()
	{
		do
			tam = obd.Entero("Tamaño");
		while (tam < 1);
		bicola = new MateriaOp[tam];
		tam = -1;
	}

	private boolean Vacia()
	{
		return tam == -1;
	}

	private boolean Llena()
	{
		return tam == bicola.length - 1;
	}

	private int Clave()
	{
		if (!this.Vacia())
			return bicola[tam].Clave() > bicola[0].Clave() ? bicola[tam].Clave() + 1 : bicola[0].Clave() + 1;
		else
			return 1;
	}

	public void InsertarIn()
	{
		if (!this.Llena())
		{
			clv = this.Clave();
			for (pos = ++tam; pos > 0; pos--)
				bicola[pos] = bicola[pos - 1];
			bicola[0] = new MateriaOp();
			bicola[0].Nueva(clv);
			System.out.println("Materia creada exitosamente...");
		}
		else
			System.out.println("Bicola llena...");
	}

	public void InsertarFin()
	{
		if (!this.Llena())
		{

			bicola[++tam] = new MateriaOp();
			bicola[tam].Nueva(this.Clave());
			System.out.println("Materia creada exitosamente...");
		}
		else
			System.out.println("Bicola llena...");
	}

	public void ExtraerIn()
	{
		if (!this.Vacia())
		{
			System.out.println("Materia a extraer es");
			bicola[0].Mostrar();
			for (pos = 0; pos < tam; pos++)
				bicola[pos] = bicola[pos + 1];
			tam--;
		}
		else
			System.out.println("Bicola vacia...");
	}

	public void ExtraerFin()
	{
		if (!this.Vacia())
		{
			System.out.println("Materia a extraer es");
			bicola[tam].Mostrar();
			tam--;
		}
		else
			System.out.println("Bicola vacia...");
	}

	public void Recorrido()
	{
		if (!this.Vacia())
		{
			System.out.println("Materias alamacenadas en el la bicola...");
			for (pos = 0; pos <= tam; pos++)
				bicola[pos].Mostrar();
			System.out.println("\nFin de la bicola...");
		}
		else
			System.out.println("Bicola vacia...");
	}

	public void BusquedaClv()
	{
		if (!this.Vacia())
		{
			ban = true;
			do
				clv = obd.Entero("Clave a buscar");
			while (clv < 1);
			for (pos = 0; pos <= tam; pos++)
				if (bicola[pos].Clave() == clv)
				{
					System.out.println("Materia encontrada...");
					bicola[pos].Mostrar();
					ban = false;
					break;
				}
			if (ban)
				System.out.println("No se encontro ninguna materia con esa clave...");
		}
		else
			System.out.println("Bicola vacia...");
	}

	public void BusquedaPlan()
	{
		if (!this.Vacia())
		{
			ban = true;
			clv = obd.Entero("Plan a buscar");
			for (pos = 0; pos <= tam; pos++)
				if (bicola[pos].Plan() == clv)
				{
					if (ban)
						System.out.println("Materia encontrada...");
					bicola[pos].Mostrar();
					ban = false;
				}
			if (ban)
				System.out.println("No se encontro ninguna materia con esa clave...");
		}
		else
			System.out.println("Bicola vacia...");
	}

	public void Borrar()
	{
		tam = -1;
		System.out.println("Borrado exitosamente...");
	}
}
