package tema3;

import ed.Menu;

public class PilaEdadsP
{
	public static void main(String[] args)
	{
		int op;
		Menu obm = new Menu("Pila Dinamica Stack",
				new String[] { "Insertar", "Extraer", "Recorrido", "Buscar", "Borrar" });
		PilaEdads obp = new PilaEdads();

		do
			switch (op = obm.Opcion())
			{
				case 1:
					obp.Insertar();
					break;
				case 2:
					obp.Extraer();
					;
					break;
				case 3:
					obp.Recorrido();
					;
					break;
				case 4:
					obp.Buscar();
					;
					break;
				case 5:
					obp.Borrar();
			}
		while (op != obm.Salir());
	}
}
