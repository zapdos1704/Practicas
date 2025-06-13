package tema3;

import ed.Menu;

public class PilaTraStackP
{
	public static void main(String[] args)
	{
		int op, op1;
		Menu obm = new Menu("Pila Dinamica Stack",
				new String[] { "Insertar", "Extraer", "Recorrido", "Buscar", "Borrar" });
		Menu obm1 = new Menu("Busqueda", new String[] { "No. Tarjeta", "Nombre", "Rango de Sueldo" });
		PilaTraStack obp = new PilaTraStack();

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
					do
						switch (op1 = obm1.Opcion())
						{
							case 1:
								obp.Busqueda(op1);
								break;
							case 2:
								obp.Busqueda(op1);
								break;
							case 3:
								obp.Busqueda(op1);
						}
					while (op1 != obm1.Salir());
					break;
				case 5:
					obp.Borrar();
			}
		while (op != obm.Salir());
	}
}
