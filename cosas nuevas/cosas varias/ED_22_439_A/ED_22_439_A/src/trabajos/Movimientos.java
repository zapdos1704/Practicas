package trabajos;

import ed.Formato;

public class Movimientos
{
	private int clv;
	private double mon;
	private String tip, fecha;
	private Formato obf = new Formato();

	public Movimientos(int clv, String tip, double mon, String fec)
	{
		this.clv = clv;
		this.tip = tip;
		this.mon = mon;
		fecha = fec;
	}

	public int Clave()
	{
		return clv;
	}

	public double Monto()
	{
		return mon;
	}

	public void Mostrar()
	{
		System.out.println("\t" + obf.Izq(clv + "", 5) + " | " + obf.Izq(tip, 10) + " | " + obf.Izq(obf.Pesos(mon), 13)
				+ " | " + obf.Izq(fecha, 20));
	}
}
