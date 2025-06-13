package tema1;

public class MatPares
{
	private int di, ren, col;
	private int[][] num, dir;
	private ed.Datos obd = new ed.Datos();

	public MatPares()
	{
		do
			ren = obd.Entero("Cantidad de renglones");
		while (ren < 2);
		do
			col = obd.Entero("Cantidad de columnas");
		while (col < 2);
		do
			di = obd.Entero("Cual es la direccion inicial");
		while (di < 1);
		num = new int[ren][col];
		dir = new int[ren][col];
	}

	public void Aleatorios()
	{
		java.util.Random obr = new java.util.Random();
		for (ren = 0; ren < num.length; ren++)
			for (col = 0; col < num[ren].length; col++)
				num[ren][col] = obr.nextInt(101);
	}

	public void Direcciones()
	{
		for (ren = 0; ren < dir.length; ren++)
			for (col = 0; col < dir[ren].length; col++)
				if (num[ren][col] % 2 == 0)
					dir[ren][col] = di + (num[0].length * ren + col) * 4;
	}

	public void DireccionesR()
	{
		for (ren = 0; ren < dir.length; ren++)
			for (col = 0; col < dir[ren].length; col++)
				if (num[ren][col] % 2 == 0)
					dir[ren][col] = di + (num.length * col + ren) * 4;
	}

	public void Mostrar()
	{
		System.out.println("Datos de la matriz...");
		for (ren = 0; ren < num.length; ren++)
		{
			for (col = 0; col < num[ren].length; col++)
				System.out.print(num[ren][col] + "\t");
			System.out.println();
		}
		System.out.println("\nDirecciones de memoria de los pares...");
		for (ren = 0; ren < dir.length; ren++)
		{
			for (col = 0; col < dir[ren].length; col++)
				System.out.print(dir[ren][col] != 0 ? dir[ren][col] + "\t" : "\t");
			System.out.println();
		}
	}
}
