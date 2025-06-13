package tema4;

public class ComparaCuenta implements java.util.Comparator<Cuenta>
{

	@Override
	public int compare(Cuenta cue1, Cuenta cue2)
	{
		return cue1.NoCuenta() - cue2.NoCuenta();
	}

}
