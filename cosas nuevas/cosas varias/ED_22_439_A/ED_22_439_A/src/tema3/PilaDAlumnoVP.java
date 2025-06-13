package tema3;

import ed.Menu;

public class PilaDAlumnoVP
{
	public static void main(String[] args)
	{
		int op;
		PilaDAlumnoV obp = new PilaDAlumnoV();
		Menu obm = new Menu("Pila Dinamica de Alumnos",
				new String[] { "Insertar", "Extraer", "Recorrido", "Buscar", "Modificar", "Borrar" });
		do
			switch (op = obm.Opcion())
			{
				case 1:
					obp.Insertar();
					break;
				case 2:
					obp.Extraer();
					break;
				case 3:
					obp.Recorrido();
					break;
				case 4:
					obp.Busqueda();
					break;
				case 5:
					obp.Modificar();
					break;
				case 6:
					obp.Borrar();

			}
		while (op != obm.Salir());
	}
}
