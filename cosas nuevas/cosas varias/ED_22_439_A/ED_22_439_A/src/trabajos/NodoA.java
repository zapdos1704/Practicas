package trabajos;

public class NodoA
{
	private Articulo art;
	private NodoA der, izq;

	public NodoA(Articulo art)
	{
		this.art = art;
		der = izq = null;
	}

	public Articulo getArt()
	{
		return art;
	}

	public void setArt(Articulo art)
	{
		this.art = art;
	}

	public NodoA getDer()
	{
		return der;
	}

	public void setDer(NodoA der)
	{
		this.der = der;
	}

	public NodoA getIzq()
	{
		return izq;
	}

	public void setIzq(NodoA izq)
	{
		this.izq = izq;
	}
}
