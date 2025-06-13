package trabajos;

public class Tienda
{
	private int clv, exi, porg;
	private String nom;
	private double prec, prev;
	private ed.Datos obd = new ed.Datos();
	private ed.Formato obf = new ed.Formato();

	public Tienda(int clv)
	{
		System.out.println("Datos del producto...");
		this.clv = clv;
		System.out.println("Clave: " + clv);
		nom = obd.Cadena("Nombre del producto").toUpperCase();
		do
			exi = obd.Entero("Existencia del producto");
		while (exi < 1);
		do
			prec = obd.Doble("Precio de compra");
		while (prec < 1);
		do
			porg = obd.Entero("Porcentaje de ganancia");
		while (porg < 20 || porg > 50);
		prev = prec * (porg * .01 + 1);
	}

	public void Mostrar()
	{
		System.out.println(obf.Der(clv + "", 4) + " | " + obf.Izq(nom, 25) + " | " + obf.Der(exi + "", 6) + " | "
				+ obf.Der(obf.Pesos(prev), 14));
	}

	public void MostrarBus()
	{
		System.out.println(obf.Der(clv + "", 4) + " | " + obf.Izq(nom, 25) + " | " + obf.Der(exi + "", 6) + " | "
				+ obf.Der(obf.Pesos(prec), 14) + " | " + obf.Izq(porg + "%", 4) + " | " + obf.Der(obf.Pesos(prev), 12));
	}

	public void Modificar()
	{
		int op;
		ed.Menu obm = new ed.Menu("Modificaciones",
				new String[] { "Nombre del producto", "Existencia", "Precio de compra", "Porcentaje de ganancia" });
		do
			switch (op = obm.Opcion())
			{
				case 1:
					System.out.println("Nombre del producto: " + nom);
					nom = obd.Cadena("Nuevo nombre del producto").toUpperCase();
					break;
				case 2:
					System.out.println("Existencia: " + exi);
					do
						exi = obd.Entero("Nueva existencia");
					while (exi < 0);
					break;
				case 3:
					System.out.println("Precio de compra: " + prec);
					do
						prec = obd.Doble("Nuevo precio de compra");
					while (prec < 1);
					prev = prec * (porg * .01 + 1);
					break;
				case 4:
					System.out.println("Porcentaje de compra: " + porg);
					do
						porg = obd.Entero("Nuevo porcentaje de ganancia");
					while (porg < 20 || porg > 50);
					prev = prec * (porg * .01 + 1);
			}
		while (op != obm.Salir());
	}

	public int Clave()
	{
		return clv;
	}

	public int Exis()
	{
		return exi;
	}
}
