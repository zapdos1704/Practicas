package trabajos;

import ed.Menu;

public class Main
{
	public static void main(String[] args)
	{
		int op;
		Menu obm = new Menu("Control de cuentas", new String[] { "Crear", "Consulta", "Deposito", "Retiro" });
		Cuentas obc = new Cuentas();
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
					obc.Deposito();
					break;
				case 4:
					obc.Retiro();
			}
		while (op != obm.Salir());
	}
}
