package trabajos;

import ed.Menu;

public class ArbolAP
{
	public static void main(String[] args)
	{
		int op;
		Menu obm = new Menu("Pila Dinamica Stack", new String[] { "Insertar", "Recorrido", "Buscar", "Eliminar" });
		ArbolA oba = new ArbolA();

		do
			switch (op = obm.Opcion())
			{
				case 1:
					oba.Insertar();
					break;
				case 2:
					oba.Recorrer();
					break;
				case 3:
					oba.Buscar();
					break;
				case 4:
					oba.Eliminar();
			}
		while (op != obm.Salir());
	}
}
