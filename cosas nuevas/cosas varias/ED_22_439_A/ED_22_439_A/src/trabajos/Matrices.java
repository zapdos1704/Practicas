package trabajos;

import ed.Datos;

public class Matrices
{
	private int ren, col, pos, n, num, den;
	private String[][] mat, mati;
	private Datos obd = new Datos();

	public Matrices()
	{
		System.out.println("Tamaño de la matriz");
		do
			ren = obd.Entero("Tamaño de la matriz");
		while (ren < 2);
		mat = new String[ren][ren];
		mati = new String[ren][ren];
		for (ren = 0; ren < mat.length; ren++)
		{
			for (col = 0; col < mat[ren].length; col++)
			{
				n = obd.Entero(ren + "/" + col);
				mat[ren][col] = n + "";
				mati[ren][col] = "0";
			}
			mati[ren][ren] = "1";
		}
	}

	public void Resultante()
	{
		boolean ban;
		int re = 0, nu = 0, nuo = 0;
		Fracciones obf = new Fracciones();
		for (pos = 0; pos < mat.length; pos++)
		{
			ban = true;
			for (ren = 0; ren < mat.length; ren++)
			{
				if (ban)
					if (mat[re][re].indexOf("/") != -1)
					{
						num = Integer.parseInt(mat[re][re].substring(0, mat[re][re].indexOf("/")));
						den = 1;
					}
					else
					{
						num = Integer.parseInt(mat[re][re]);
						den = Integer.parseInt(mat[re][re]);
						System.out.println(den);
					}
				System.out.println(num + "s\n");
				if (!ban)
				{
					nu = Integer.parseInt(mat[ren][pos]);
					nuo = Integer.parseInt(mati[ren][pos]);
					System.out.println(nu + "as");
					System.out.println(nuo + "er");
				}
				for (col = 0; col < mat[ren].length; col++)
				{
					if (ban)
					{
						mat[re][col] = obf.Fraccion(num, 3, 0, 0, "div");
						mati[re][col] = obf.Fraccion(num, Integer.parseInt(mati[re][col]), 0, 0, "div");
						System.out.println(mat[re][col]);
						System.out.println(mati[re][col]);
					}
					else
					{
						if (mat[ren][col].indexOf("/") == -1 && mati[ren][col].indexOf("/") == -1)
						{

						}
						else
						{
							mat[ren][col] = obf.Fraccion(
									Integer.parseInt(mat[ren][col].substring(0, mat[ren][col].indexOf("/"))),
									Integer.parseInt(mat[ren][col].substring(mat[ren][col].indexOf("/"))),
									Integer.parseInt(mat[re][col].substring(0, mat[re][col].indexOf("/") * (-1 * nu))),
									Integer.parseInt(mat[re][col].substring(mat[re][col].indexOf("/"))), "s");
							mati[ren][col] = obf.Fraccion(
									Integer.parseInt(mati[ren][col].substring(0, mati[ren][col].indexOf("/") - 1)),
									Integer.parseInt(mati[ren][col].substring(mati[ren][col].indexOf("/"))),
									Integer.parseInt(
											mati[re][col].substring(0, mati[re][col].indexOf("/") * (-1 * nu))),
									Integer.parseInt(mati[re][col].substring(mati[re][col].indexOf("/"))), "s");
						}

					}
					System.out.println();
				}
				ban = false;
			}
			re++;
		}
	}

	public void Mostrar()
	{
		for (ren = 0; ren < mat.length; ren++)
		{
			for (col = 0; col < mat[ren].length; col++)
				System.out.print(mat[ren][col] + "\t");
			System.out.println();
		}
		System.out.println();
		for (ren = 0; ren < mat.length; ren++)
		{
			for (col = 0; col < mat[ren].length; col++)
				System.out.print(mati[ren][col] + "\t");
			System.out.println();
		}
	}
}
