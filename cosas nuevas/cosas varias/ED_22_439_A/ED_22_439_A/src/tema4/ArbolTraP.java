package tema4;

import ed.Menu;

public class ArbolTraP
{
	public static void main(String[] args)
	{
		int op;
		Menu obm = new Menu("Pila Dinamica Stack", new String[] { "Insertar", "Extraer Copiado", "Extreaer fusion",
				"Recorrido Amplitud", "Recorrido profundidad", "Buscar", "Balanceo", "Borrar" });
		ArbolTra oba = new ArbolTra();

		do
			switch (op = obm.Opcion())
			{
				case 1:
					oba.Insertar();
					break;
				case 2:
					oba.EliminarCopiado();
					break;
				case 3:
					oba.ExtraerFusion();
					break;
				case 4:
					oba.RecorridoAmplitud();
					break;
				case 5:
					oba.RecorridoProfundidad();
					break;
				case 6:
					oba.Busqueda();
					break;
				case 7:
					oba.Balanceo();
					break;
				case 8:
					oba.Borrar();
			}
		while (op != obm.Salir());
	}
}
