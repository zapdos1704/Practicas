package trabajos;

public class TiendaVecP
{
	public static void main(String[] args)
	{
		int op;
		ed.Menu obm = new ed.Menu("Control de productos",
				new String[] { "Nuevo", "Consultar", "Buscar", "Modificar", "Eliminar" });
		TiendaVec obt = new TiendaVec();
		do
			switch (op = obm.Opcion())
			{
				case 1:
					obt.Nuevo();
					break;
				case 2:
					obt.Consulta();
					break;
				case 3:
					obt.Buscar();
					break;
				case 4:
					obt.Modificar();
					break;
				case 5:
					obt.Eliminar();
			}
		while (op != obm.Salir());
	}
}
