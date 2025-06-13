package tema3;

public class PilaDVectorP
{
	public static void main(String[] args)
	{
		int op;
		ed.Menu obm = new ed.Menu("Vector",
				new String[] { "Insertar", "Extraer", "Recorrido", "Busqueda", "Modificar", "Borrar" });
		PilaDVector obp = new PilaDVector();

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
					obp.Modificar();
					break;
				case 6:
					obp.Borrar();
			}
		while (op != obm.Salir());
	}
}
