package tema1;

public class Precios
{
	private int di, dir, can, pos;
	private ed.Datos obd = new ed.Datos();

	public Precios()
	{
		do
			can = obd.Entero("Tamaño del vector");
		while (can < 1);
		do
			di = obd.Entero("Direccion inicial");
		while (di < 1);
	}

	public void Direcciones()
	{
		System.out.println("Direcciones de memoria...");
		for (pos = 0; pos < can; pos++)
		{
			dir = di + pos * 8;
			this.Mostrar();
		}
	}

	private void Mostrar()
	{
		System.out.println(" [" + pos + "] " + dir);
	}
}
