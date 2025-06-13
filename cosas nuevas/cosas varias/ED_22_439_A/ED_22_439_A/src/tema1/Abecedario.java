package tema1;

public class Abecedario
{
	private int di, dir, pos;
	private String cad = "AMRGHO";
	private char arr[] = new char[26];
	private ed.Datos obd = new ed.Datos();

	public Abecedario()
	{
		do
			di = obd.Entero("Direccion inicial");
		while (di < 1);
		for (pos = 0; pos < arr.length; pos++)
			arr[pos] = (char) (65 + pos);
	}

	public void Direcciones()
	{
		for (pos = 0; pos < arr.length; pos++)
		{
			if (cad.indexOf(arr[pos]) != -1)
				dir = di + pos * 2;
			else
				dir = 0;
			this.Mostrar();
		}

	}

	private void Mostrar()
	{
		System.out.println(pos + "\t" + arr[pos] + "\t" + (dir != 0 ? dir : ""));
	}
}
