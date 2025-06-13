package tema3;

import ed.Menu;

public class ColaDSP
{
	public static void main(String[] args)
	{
		int op;
		Menu obm = new Menu("Cola dinamica simple",
				new String[] { "Insertar", "Extraer", "Recorrido", "Buscar", "Borrar" });
		ColaDS obc = new ColaDS();
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
					obc.Borrar();
			}
		while (op != obm.Salir());
	}
}
