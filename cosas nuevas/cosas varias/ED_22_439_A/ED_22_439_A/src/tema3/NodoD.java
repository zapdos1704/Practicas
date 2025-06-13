package tema3;

public class NodoD
{
	private String pro;
	private NodoD ant, sig;

	public NodoD(String pro)
	{
		ant = null;
		this.pro = pro.toUpperCase();
		sig = null;
	}

	public String getPro()
	{
		return pro;
	}

	public void setPro(String pro)
	{
		this.pro = pro;
	}

	public NodoD getAnt()
	{
		return ant;
	}

	public void setAnt(NodoD ant)
	{
		this.ant = ant;
	}

	public NodoD getSig()
	{
		return sig;
	}

	public void setSig(NodoD sig)
	{
		this.sig = sig;
	}
}
