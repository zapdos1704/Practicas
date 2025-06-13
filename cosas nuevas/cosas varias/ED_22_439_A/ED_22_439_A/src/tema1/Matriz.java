package tema1;

public class Matriz
{
	private int di, ren, col;
	private int mat[][];
	private ed.Datos obd = new ed.Datos();

	public Matriz()
	{
		do
			ren = obd.Entero("Ingresa el numero de renglones y columnas");
		while (ren < 2);
		do
			di = obd.Entero("Cual es la direccion inicial");
		while (di < 1);
		mat = new int[ren][ren];
	}

	public void Direcciones()
	{
		for (ren = 0; ren < mat.length; ren++)
			mat[ren][ren] = di + (mat[0].length * ren + ren) * 4;
	}

	public void DireccionesR()
	{
		for (ren = 0; ren < mat.length; ren++)
			mat[ren][ren] = di + (mat.length * ren + ren) * 4;
	}

	public void Mostrar()
	{
		System.out.println("Direciones de memoria de la diagonal principal...");
		for (ren = 0; ren < mat.length; ren++)
			System.out.println("[" + ren + "," + ren + "] " + mat[ren][ren]);
	}

	public void MMat()
	{
		for (ren = 0; ren < mat.length; ren++)
		{
			for (col = 0; col < mat[ren].length; col++)
				System.out.print(mat[ren][col] + "\t");
			System.out.println();
		}
	}
}
