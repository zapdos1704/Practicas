package tema3;

import ed.Menu;

public class ListasCSMatP
{
	public static void main(String[] args)
	{
		int op1, op2;
		Menu obm = new Menu("Lista Doble Alumno",
				new String[] { "Insertar", "Extraer", "Recorrido", "Buscar", "Borrar" });
		Menu obm2;
		ListasCSMat obl = new ListasCSMat();
		do
			switch (op1 = obm.Opcion())
			{
				case 1:
					obm2 = new Menu("Insertar", new String[] { "Inicio", "Posicion", "Fin" });
					do
						switch (op2 = obm2.Opcion())
						{
							case 1:
								obl.InsertarIni();
								break;
							case 2:
								obl.InsertarPos();
								break;
							case 3:
								obl.InsertarFin();
						}
					while (op2 != obm2.Salir());
					break;
				case 2:
					obm2 = new Menu("Extraer", new String[] { "Inicio", "Posicion", "Fin" });
					do
						switch (op2 = obm2.Opcion())
						{
							case 1:
								obl.ExtraerIni();
								break;
							case 2:
								obl.ExtraerPos();
								break;
							case 3:
								obl.ExtraerFin();
						}
					while (op2 != obm2.Salir());
					break;
				case 3:
					obl.Recorrido();
					break;
				case 4:

					obl.Buscar();
					break;
				case 5:
					obl.Borrar();
			}
		while (op1 != obm.Salir());
	}
}
