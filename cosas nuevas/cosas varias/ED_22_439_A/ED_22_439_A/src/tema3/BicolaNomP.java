package tema3;

public class BicolaNomP
{
	public static void main(String[] args)
	{
		int op;
		BicolaNom obb = new BicolaNom();
		ed.Menu obm = new ed.Menu("Control de nombres", new String[] { "InsertarIn", "InsertarFin", "ExtraerIn",
				"ExtraerFin", "Recorrido", "Buscar", "Borrar" });
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
					obb.Buscar();
					break;
				case 7:
					obb.Borrar();
			}
		while (op != obm.Salir());
	}
}
