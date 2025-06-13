package tema1;

public class Trabajador
{
	private int nt;
	private String nom, pue;
	private double sue;

	public int getNoTarjeta()
	{
		return nt;
	}

	public void setNoTarjeta(int nt)
	{
		this.nt = nt;
	}

	public String getNombre()
	{
		return nom;
	}

	public void setNombre(String nom)
	{
		this.nom = nom;
	}

	public String getPuesto()
	{
		return pue;
	}

	public void setPuesto(String pue)
	{
		this.pue = pue;
	}

	public double getSueldo()
	{
		return sue;
	}

	public void setSueldo(double sue)
	{
		this.sue = sue;
	}

}
