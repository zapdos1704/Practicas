package tema3;

import ed.Menu;

public class ListaCDEP
{
	public static void main(String[] args)
	{
		int op;
		Menu obm = new Menu("Lista Circular Doble de numeros",
				new String[] { "Insertar", "Extraer", "Recorrido", "Buscar", "Modificar", "Borrar" });
		ListaC obl = new ListaC();
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
					break;
				case 4:
					obl.Buscar();
					break;
				case 5:
					obl.Modificar();
					break;
				case 6:
					obl.Borrar();
			}
		while (op != obm.Salir());
	}
}
