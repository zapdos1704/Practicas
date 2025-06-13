package trabajos;

public class TiendaVec
{
	private java.util.Vector<Tienda> tien = new java.util.Vector<Tienda>(10, 3);
	private ed.Formato obf = new ed.Formato();
	private ed.Datos obd = new ed.Datos();
	private Tienda ref;
	private int pos, clv, cl;
	private boolean ban;

	public void Nuevo()
	{
		if (tien.add(new Tienda(++cl)))
			System.out.println("Producto agregado correctamente....\n");
		else
			System.out.println("No se puedo agregar el producto...\n");
	}

	public void Consulta()
	{
		for (Tienda a : tien)
			a.Mostrar();
	}

	public void Buscar()
	{
		if (!tien.isEmpty())
		{
			ban = true;
			do
				clv = obd.Entero("Clave del producto a buscar");
			while (clv < 1 || clv > cl);
			for (pos = 0; pos < tien.size(); pos++)
			{
				ref = tien.get(pos);
				if (ref.Clave() == clv)
				{
					System.out.println("Producto encontrado...\n");
					System.out.println(obf.Der("CLV", 4) + " | " + obf.Izq("NOMBRE", 25) + " | "
							+ obf.Der("EXISTENCIA", 6) + " | " + obf.Der("PRECIO DE COMPRA", 14) + " | "
							+ obf.Izq("GAN%", 4) + " | " + obf.Der("PRECIO DE VENTA", 12));
					ref.MostrarBus();
					ban = false;
					break;
				}
			}
			if (ban)
				System.out.println("No hay un producto con esa clave");
			System.out.println("..................................................\n");
		}
		else
			System.out.println("No hay ningun producto registrado...\n");
	}

	public void Modificar()
	{
		if (!tien.isEmpty())
		{
			ban = true;
			do
				clv = obd.Entero("Clave del producto a modificar");
			while (clv < 1 || clv > cl);
			for (pos = 0; pos < tien.size(); pos++)
			{
				ref = tien.get(pos);
				if (ref.Clave() == clv)
				{
					System.out.println("Datos del producto a modificar...\n");
					System.out.println(obf.Der("CLV", 4) + " | " + obf.Izq("NOMBRE", 25) + " | "
							+ obf.Der("EXISTENCIA", 6) + " | " + obf.Der("PRECIO DE COMPRA", 14) + " | "
							+ obf.Izq("GAN%", 4) + " | " + obf.Der("PRECIO DE VENTA", 12));
					ref.MostrarBus();
					System.out.println();
					ref.Modificar();
					System.out.println("Producto modificado...");
					ban = false;
					break;
				}
			}
			if (ban)
				System.out.println("No hay un producto con esa clave");
			System.out.println("..................................................\n");
		}
		else
			System.out.println("No hay ningun producto registrado...\n");
	}

	public void Eliminar()
	{
		if (!tien.isEmpty())
		{
			ban = true;
			do
				clv = obd.Entero("Clave del producto a eliminar");
			while (clv < 1);
			for (pos = 0; pos < tien.size(); pos++)
			{
				ref = tien.get(pos);
				if (ref.Clave() == clv)
				{
					System.out.println("Producto encontrado...\n");
					ref.Mostrar();
					if (ref.Exis() == 0)
					{
						tien.remove(pos);
						if (clv == cl)
							cl--;
						System.out.println("Producto eliminado...\n");
						ban = false;
					}
					else
						System.out.println("No se puede eliminar el producto porque todavia tiene existencia...");
					break;
				}
			}
			if (ban)
				System.out.println("No se pudo eliminar este producto...\n");
			System.out.println("..................................................\n");
		}
		else
			System.out.println("No hay ningun producto registrado...\n");
	}
}
