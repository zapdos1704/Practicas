package ed1;

public class prueba
{
	public static void main(String[] args)
	{
		Menu obm = new Menu("Menú principal", new String[] { "uno", "dos", "tres", "cuatro" });
		System.out.println(obm.Opcion());
	}
}
