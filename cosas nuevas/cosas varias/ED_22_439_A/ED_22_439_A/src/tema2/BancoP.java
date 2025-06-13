package tema2;

public class BancoP
{
	public static void main(String[] args)
	{
		Banco obb = new Banco();
		obb.Mostrar(obb.Inversion(obb.Mes(), obb.Por(), obb.Dinero()));
	}
}
