package tema3;

public class PilaProductoP
{
	public static void main(String[] args)
	{
		int op;
		PilaProducto obp = new PilaProducto();
		ed.Menu obm = new ed.Menu("Control de calificaciones",
				new String[] { "Insertar", "Extraer", "Recorrido", "Buscar", "Modificar", "Eliminar" });
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
				case 4:
				case 5:
				case 6:
					obp.Operaciones(op);
			}
		while (op != obm.Salir());
	}
}
