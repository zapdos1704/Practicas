package tema1;

public class Trabajadores
{
	private Trabajador lista[];
	private int pos, can, nt;
	private String nom;
	private boolean ban = true;
	private ed.Datos obd = new ed.Datos();
	private ed.Formato obf = new ed.Formato();
	private ed.Menu obm = new ed.Menu("Datos a modificar", new String[] { "Nombre", "Puesto", "Sueldo" });

	public Trabajadores()
	{
		do
			can = obd.Entero("Cantidad de trabajadores");
		while (can < 1);
		lista = new Trabajador[can];
		can = -1;
	}

	public void Nuevo()
	{
		if (can + 1 < lista.length)
		{
			NoTarjeta();
			lista[++can] = new Trabajador();
			System.out.println("Escribe los datos del trabajador...");
			lista[can].setNoTarjeta(nt);
			System.out.println("No. de Trajeta " + lista[can].getNoTarjeta());
			lista[can].setNombre(obd.Cadena("Nombre").toUpperCase());
			lista[can].setPuesto(obd.Cadena("Puesto").toUpperCase());
			do
				lista[can].setSueldo(obd.Doble("Sueldo"));
			while (lista[can].getSueldo() < 1);
		}
		else
			System.out.println("No hay espacio para otro trabajador...");
	}

	public void Consulta()
	{
		if (can != -1)
		{
			System.out.println("Lista de trabajadores...");
			for (pos = 0; pos <= can; pos++)
				this.Mostrar();
		}
	}

	private void Mostrar()
	{
		System.out.println(obf.Izq(lista[pos].getNoTarjeta() + "", 4) + " " + obf.Izq(lista[pos].getNombre(), 25) + " "
				+ obf.Izq(lista[pos].getPuesto(), 15) + " " + obf.Der(obf.Pesos(lista[pos].getSueldo()), 15));
	}

	public void Buscar()
	{
		if (can != -1)
		{
			ban = true;
			nom = obd.Cadena("Nombre a buscar").toUpperCase();
			for (pos = 0; pos <= can; pos++)
				if (lista[pos].getNombre().indexOf(nom) != -1)
				{
					if (ban)
						System.out.println("Trabajador encontrado...");
					this.Mostrar();
					ban = false;
				}
			if (ban)
				System.out.println("No hay ninguna trabajador con ese nombre...");
		}
		else
			System.out.println("No hay ningun trabajador registrado...");
	}

	public void Modificar()
	{
		if (can != -1)
		{
			int op;
			do
				nt = obd.Entero("No. de Trajeta a modificar");
			while (nt < 1 || nt > lista[can].getNoTarjeta());
			for (pos = 0; pos <= can; pos++)
				if (lista[pos].getNoTarjeta() == nt)
				{
					System.out.println("Datos del trabajador...");
					this.Mostrar();
					do
						switch (op = obm.Opcion())
						{
							case 1:
								lista[pos].setNombre(obd.Cadena("Nuevo nombre").toUpperCase());
								break;
							case 2:
								lista[pos].setPuesto(obd.Cadena("Nuevo puesto").toUpperCase());
								break;
							case 3:
								do
									lista[pos].setSueldo(obd.Doble("Nuevo sueldo"));
								while (lista[pos].getSueldo() < 1);
						}
					while (op != obm.Salir());
					break;
				}
			if (pos > can)
				System.out.println("El numero de tarjeta que usted ingreso no existe...");
		}
		else
			System.out.println("No hay ningun trabajador registrado...");
	}

	public void Eliminar()
	{
		if (can != -1)
		{
			do
				nt = obd.Entero("No. de Trajeta a eliminar");
			while (nt < 1 || nt > lista[can].getNoTarjeta());
			for (pos = 0; pos <= can; pos++)
				if (lista[pos].getNoTarjeta() == nt)
				{
					System.out.println("Trabajador eliminado...");
					this.Mostrar();
					for (; pos < can; pos++)
						lista[pos] = lista[pos + 1];
					can--;
					ban = false;
					break;
				}
			if (ban)
				System.out.println("El No. de tarjeta no existe...");
		}
	}

	private void NoTarjeta()
	{
		if (can != -1)
			nt = lista[can].getNoTarjeta() + 1;
		else
			nt = 1;
	}
}
