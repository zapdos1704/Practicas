package trabajos;

public class Inversas
{
	private int[][] mat, inv;
	private int pos, ren, col;
	private ed.Datos obd = new ed.Datos();

	public Inversas()
	{
		do
			ren = obd.Entero("Renglones");
		while (ren < 2);
		mat = new int[ren][ren];
		inv = new int[ren][ren];
		for (ren = 0; ren < mat.length; ren++)
			for (col = 0; col < mat[ren].length; col++)
				mat[ren][col] = obd.Entero(ren + "/" + col);
	}

	public void Determinante()
	{
		System.out.println(pos + inv[0][0]);
	}
}
