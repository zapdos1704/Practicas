package tema2;

public class ArregloP
{
	public static void main(String[] args)
	{
		Arreglo oba = new Arreglo();
		int tam = oba.Vec();
		oba.Aleatorios(tam);
		System.out.println("Contenido del arreglo....");
		oba.Mostrar(tam);
	}
}
