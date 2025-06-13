package tema3;

public class NodoC
{
	private Producto pro;
	private NodoC ant, sig;

	public NodoC(Producto pro)
	{
		ant = null;
		this.pro = pro;
		sig = null;
	}

	public Producto getPro()
	{
		return pro;
	}

	public void setPro(Producto pro)
	{
		this.pro = pro;
	}

	public NodoC getAnt()
	{
		return ant;
	}

	public void setAnt(NodoC ant)
	{
		this.ant = ant;
	}

	public NodoC getSig()
	{
		return sig;
	}

	public void setSig(NodoC sig)
	{
		this.sig = sig;
	}
}
