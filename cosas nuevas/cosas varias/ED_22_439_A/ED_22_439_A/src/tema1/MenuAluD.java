package tema1;

public class MenuAluD
{
	public static void main(String[] args)
	{
		int op;
		ed.Menu obm = new ed.Menu("Control de alumnos",
				new String[] { "Insertar", "Consultar", "Modificar", "Buscar", "Eliminar" });
		AlumnoAluD oba = new AlumnoAluD();

		do
			switch (op = obm.Opcion())
			{
				case 1:
					oba.Insertar();
					break;
				case 2:
					oba.Consulta();
					break;
				case 3:
					oba.Modificar();
					break;
				case 4:
					oba.Buscar();
					break;
				case 5:
					oba.Eliminar();
			}
		while (op != obm.Salir());
	}
}
