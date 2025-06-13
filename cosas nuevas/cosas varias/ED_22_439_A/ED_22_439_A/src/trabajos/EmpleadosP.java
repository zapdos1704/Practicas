package trabajos;

public class EmpleadosP
{

	public static void main(String[] args)
	{
		int can, op, pos;
		String nomb;
		boolean ban;
		Empleados lista[];
		ed.Datos obd = new ed.Datos();
		ed.Menu obm = new ed.Menu("Control de empleados", new String[] { "Nuevo", "Consulta", "Buscar" });
		do
			can = obd.Entero("Cantidad de Empleados");
		while (can < 1);
		lista = new Empleados[can];
		can = -1;
		do
			switch (op = obm.Opcion())
			{
				case 1:
					if (can + 1 < lista.length)
						lista[++can] = new Empleados(can + 1);
					else
						System.out.println("No hay espacios en la nomina...");
					break;
				case 2:
					if (can != -1)
					{
						System.out.println("\nLista de Empleados...");
						System.out.println("Clave\tNombre\t\t\t     Sueldo Base\tRetención\tSueldo Total");
						for (pos = 0; pos <= can; pos++)
							lista[pos].Mostrar();
					}
					else
						System.out.println("No hay empleados registrados...");
					break;
				case 3:
					if (can != -1)
					{
						ban = true;
						nomb = obd.Cadena("Nombre o apellido del empleado a buscar").toUpperCase();
						for (pos = 0; pos <= can; pos++)
							if (lista[pos].Nombre().indexOf(nomb) != -1)
							{
								if (ban)
									System.out.println("Empleados encontrados...");
								lista[pos].Mostrar();
								ban = false;
							}
						if (ban)
							System.out.println("No existe ningun empleado con ese Nombre o Apellido...");
					}
					else
						System.out.println("No hay empleados registrados...");
			}
		while (op != obm.Salir());
	}

}
