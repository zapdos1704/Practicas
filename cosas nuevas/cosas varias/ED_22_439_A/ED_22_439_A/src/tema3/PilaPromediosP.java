package tema3;

public class PilaPromediosP
{
	public static void main(String[] args)
	{
		int op;
		PilaPromedios obp = new PilaPromedios();
		ed.Menu obm = new ed.Menu("Control de calificaciones",
				new String[] { "Insertar", "Extraer", "Recorrido", "Buscar", "Eliminar" });
		do
			switch (op = obm.Opcion())
			{
				case 1:
					obp.Insertar();
					break;
				case 2:
					obp.Extraer();
					break;
				case 3:
					obp.Recorrido();
					break;
				case 4:
					obp.Busqueda();
					break;
				case 5:
					obp.Eliminar();
			}
		while (op != obm.Salir());
	}
}
