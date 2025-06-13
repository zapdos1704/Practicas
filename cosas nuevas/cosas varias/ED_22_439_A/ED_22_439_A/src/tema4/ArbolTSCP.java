package tema4;

import ed.Menu;

public class ArbolTSCP
{
	public static void main(String[] args)
	{
		int op;
		Menu obm = new Menu("Pila Dinamica Stack",
				new String[] { "Insertar", "Recorrido", "Buscar", "Depositar", "Retiro", "Eliminar", "Borrar" });
		ArbolTSC oba = new ArbolTSC();

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
					oba.Deposito();
					break;
				case 5:
					oba.Retiro();
					break;
				case 6:
					oba.Eliminar();
					break;
				case 7:
					oba.Borrar();
			}
		while (op != obm.Salir());
	}
}
