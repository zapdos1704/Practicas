package tema3;

import ed.Menu;

public class ListaProP
{
	public static void main(String[] args)
	{
		int op;
		Menu obm = new Menu("Lista Producto", new String[] { "Insertar", "Extraer", "Recorrido", "Buscar","Modificar" });
		ListaPro obl = new ListaPro();
		do
			switch (op = obm.Opcion())
			{
				case 1:
					obl.Insertar();
					break;
				case 2:
					obl.Extraer();
					break;
				case 3:
					obl.Recorrido();
					;
					break;
				case 4:
					obl.Buscar();
					break;
				case 5:
					obl.Modificar();
			}
		while (op != obm.Salir());
	}
}
