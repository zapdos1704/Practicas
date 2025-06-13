package tema3;

import ed.Menu;

public class BicolaP
{
	public static void main(String[] args)
	{
		int op;
		Menu obm = new Menu("Bicola", new String[] { "InsertarIn", "InsertarFin", "ExtraerIn", "ExtraerFin",
				"Recorrido", "Buscar", "Borrar" });
		Bicola obp = new Bicola();
		do
			switch (op = obm.Opcion())
			{
				case 1:
					obp.InsertarIn();
					break;
				case 2:
					obp.InsertarFin();
					break;
				case 3:
					obp.ExtraerIn();
					break;
				case 4:
					obp.ExtraerFin();
					break;
				case 5:
					obp.Recorrido();
					break;
				case 6:
					obp.Buscar();
					break;
				case 7:
					obp.Borrar();
			}
		while (op != obm.Salir());

	}
}