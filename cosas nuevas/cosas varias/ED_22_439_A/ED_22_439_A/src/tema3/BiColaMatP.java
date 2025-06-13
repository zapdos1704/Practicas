package tema3;

import ed.Menu;

public class BiColaMatP
{
	public static void main(String[] args)
	{
		int op;
		Menu obm = new Menu("Bicola estatica materias", new String[] { "Insertar Inicio", "Insertar Fin",
				"Extraer Inicio", "Extraer Fin", "Recorrido", "Buscar Clave", "Buscar Plan", "Borrar" });
		BiColaMat obb = new BiColaMat();
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
					obb.BusquedaClv();
					break;
				case 7:
					obb.BusquedaPlan();
					break;
				case 8:
					obb.Borrar();
			}
		while (op != obm.Salir());
	}
}
