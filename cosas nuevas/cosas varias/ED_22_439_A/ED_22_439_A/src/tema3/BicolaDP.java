package tema3;

import ed.Menu;

public class BicolaDP
{
	public static void main(String[] args)
	{
		int op;
		Menu obm = new Menu("Bicola Dinamica", new String[] { "Insertar Inicio", "Insertar Fin", "Extraer Inicio",
				"Extraer Fin", "Recorrido", "Buscar", "Borrar" });
		BicolaD obb = new BicolaD();
		do
			switch (op = obm.Opcion())
			{
				case 1:
					obb.InsertarIn();
					break;
				case 2:
					obb.InsertarFin();
					break;
				case 3:
					obb.ExtraerIn();
					break;
				case 4:
					obb.ExtraerFin();
					break;
				case 5:
					obb.Recorrido();
					break;
				case 6:
					obb.Busqueda();
					break;
				case 7:
					obb.Borrar();
			}
		while (op != obm.Salir());
	}
}
