package trabajos;

import ed.Menu;

public class MainPrue
{
	public static void main(String[] args)
	{
		int op;
		Menu obm = new Menu("Control de cuentas", new String[] { "Crear", "Consulta", "Deposito", "Retiro" });
		Prueba obc = new Prueba();
		do
			switch (op = obm.Opcion())
			{
				case 1:
					obc.Insertar();
					break;
				case 2:
					obc.Consultar();
					break;
				case 3:
					obc.Operacion(op);;
					break;
				case 4:
					obc.Operacion(op);;
			}
		while (op != obm.Salir());
	}
}
