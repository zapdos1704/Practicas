package tema1;

public class VectorDinamico
{
	public static void main(String[] args)
	{
		int op, pos, num;
		ed.Menu obm = new ed.Menu("Vectores dinamicos",
				new String[] { "Insertar", "Consultar", "Modificar", "Buscar", "Eliminar" });
		java.util.Vector<Integer> vec = new java.util.Vector<Integer>(5, 2);
		do
			switch (op = obm.Opcion())
			{
				case 1:
					vec.add(obm.obd.Entero("Escribe un número entero"));
					break;
				case 2:
					if (!vec.isEmpty())
					{
						System.out.println("Contenido del vector...");
						/*
						 * for (pos = 0; pos < vec.size(); pos++) System.out.println(vec.get(pos));
						 */
						for (Integer ele : vec)
							System.out.println(ele);
					}
					else
						System.out.println("No hay datos en el vector");
					break;
				case 3:
					if (!vec.isEmpty())
					{
						num = obm.obd.Entero("Nuevo número");
						do
							pos = obm.obd.Entero("Posicion de elemento a modificar");
						while (pos < 1 || pos > vec.size());
						vec.set(--pos, num);
					}
					else
						System.out.println("No hay datos en el vector");
					break;
				case 4:
					if (!vec.isEmpty())
					{
						num = obm.obd.Entero("Número a buscar");
						pos = vec.indexOf(num);
						if (pos != -1)
							System.out.println("Número encontrado en la posición " + (++pos));
						else
							System.out.println("El número no se encuentra en el vector");
					}
					else
						System.out.println("No hay datos en el vector");
					break;
				case 5:
					if (!vec.isEmpty())
					{
						do
							pos = obm.obd.Entero("Posición");
						while (pos < 1 || pos > vec.size());
						num = vec.remove(pos);
						System.out.println("Número eliminado " + num);
					}
					else
						System.out.println("No hay datos en el vector");
			}
		while (op != obm.Salir());
	}
}
