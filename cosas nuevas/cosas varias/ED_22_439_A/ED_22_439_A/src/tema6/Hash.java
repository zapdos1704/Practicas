package tema6;

public class Hash
{
	public int Modulo(int num, int tam)
	{
		return num % tam;
	}

	public int Cuadratica(int num, int tam)
	{
		int dir = 0, nd = ((tam - 1) + "").length();
		String cua = (int) Math.pow(num, 2) + "";
		do
		{
			if (cua.length() > nd)
				dir = Integer.parseInt(cua.substring(cua.length() / 2 - nd / 2, cua.length() / 2 - nd / 2 + nd));
			else
				dir = Integer.parseInt(cua);
			if (dir >= tam)
				nd--;
		}
		while (dir >= tam);
		return dir;
	}

	public int Plegamiento(int num, int tam)
	{
		int dir = 0, pos, suma, nd = ((tam - 1) + "").length();
		String cad = num + "";
		do
		{
			suma = 0;
			if (cad.length() > nd)
			{
				for (pos = 0; pos < cad.length() - nd; pos += nd)
					suma += Integer.parseInt(cad.substring(pos, pos + nd));
				suma += Integer.parseInt(cad.substring(pos));
				dir = Integer.parseInt((suma + "").substring((suma + "").length() - nd));
			}
			else
				dir = num;
			if (dir >= tam)
				nd--;
		}
		while (dir >= tam);
		return dir;
	}

	public int Truncamiento(int num, int tam)
	{
		int dir = 0, pos, nd = ((tam - 1) + "").length();
		String cad = num + "", sub = "";
		for (pos = 0; pos < cad.length(); pos += 2)
			sub += cad.charAt(pos);
		do
		{
			if (cad.length() > nd)
			{
				dir = Integer.parseInt(sub.substring(0, nd));
			}
			else
				dir = num;
			if (dir >= tam)
				nd--;
		}
		while (dir >= tam);
		return dir;
	}
}
