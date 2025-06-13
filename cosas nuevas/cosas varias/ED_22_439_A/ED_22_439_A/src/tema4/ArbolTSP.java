package tema4;

import ed.Menu;

public class ArbolTSP
{
	public static void main(String[] args)
	{
		int op;
		Menu obm = new Menu("Tree Set Arboles",
				new String[] { "Insertar", "Recorrido", "Buscar", "Eliminar", "Borrar" });
		ArbolTS oba = new ArbolTS();

		do
			switch (op = obm.Opcion())
			{
				case 1:
					oba.Insertar();
					break;
				case 2:
					oba.Recorrido();
					break;
				case 3:
					oba.Buscar();
					break;
				case 4:
					oba.Eliminar();
					break;
				case 5:
					oba.Borrar();
			}
		while (op != obm.Salir());
	}
}
