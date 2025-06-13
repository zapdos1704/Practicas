package trabajos;

import ed.Datos;
import ed.Formato;

public class Movimiento
{
	private Datos obd = new Datos();
	private Formato obf = new Formato();
	private String tipo, fecha;
	private int cta;
	private double monto;

	public Movimiento(String tipo, int cta, int saldo)
	{
		this.cta = cta;
		this.tipo = tipo;
		System.out.println("Movimiento: " + this.tipo);
		fecha = obd.Cadena("Fecha: ").toUpperCase();
		do
			monto = obd.Doble("Monto: ");
		while (monto < 1);
		if (tipo.equals("Retiro"))
			if (monto > saldo)
				System.out.println("NO HAY SUFICIENTE SALDO");
	}

	public void Mostrar()
	{
		System.out.println(obf.Der(tipo, 10) + " | " + obf.Izq(fecha, 10) + " | " + obf.Izq(obf.Pesos(monto), 10));
	}

	public int Ncta()
	{
		return cta;
	}

	public String Tipo()
	{
		return tipo;
	}

	public double Monto()
	{
		return monto;
	}

}
