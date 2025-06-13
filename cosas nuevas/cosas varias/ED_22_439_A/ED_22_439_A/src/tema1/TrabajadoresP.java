package tema1;

public class TrabajadoresP
{
	public static void main(String[] args)
	{
		int op;
		Trabajadores obt = new Trabajadores();
		ed.Menu obm = new ed.Menu("Control de empleados",
				new String[] { "Nuevo", "Lista", "Buscar", "Modificar", "Eliminar" });
		do
			switch (op = obm.Opcion())
			{
				case 1:
					obt.Nuevo();
					break;
				case 2:
					obt.Consulta();
					break;
				case 3:
					obt.Buscar();
					break;
				case 4:
					obt.Modificar();
					break;
				case 5:
					obt.Eliminar();
			}
		while (op != obm.Salir());
	}
}
