package tema3;

import ed.Menu;

public class ColaSAluP
{
	public static void main(String[] args)
	{
		int op;
		Menu obm = new Menu("Cola simple de alumnos",
				new String[] { "Insertar", "Extraer", "Recorrido", "Buscar", "Modificar", "Borrar" });
		ColaSAlu obc = new ColaSAlu();
		do
			switch (op = obm.Opcion())
			{
				case 1:
					obc.Insertar();
					break;
				case 2:
					obc.Extraer();
					break;
				case 3:
					obc.Recorrido();
					break;
				case 4:
					obc.Buscar();
					break;
				case 5:
					obc.Modificar();
					break;
				case 6:
					obc.Borrar();
			}
		while (op != obm.Salir());
	}
}
