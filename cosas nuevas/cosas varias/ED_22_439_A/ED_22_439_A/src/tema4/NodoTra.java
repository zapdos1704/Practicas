package tema4;

public class NodoTra
{
	private Trabajador tra;
	private NodoTra der, izq;

	public NodoTra(Trabajador tra)
	{
		this.tra = tra;
		der = izq = null;
	}

	public Trabajador getTra()
	{
		return tra;
	}

	public void setTra(Trabajador tra)
	{
		this.tra = tra;
	}

	public NodoTra getDer()
	{
		return der;
	}

	public void setDer(NodoTra der)
	{
		this.der = der;
	}

	public NodoTra getIzq()
	{
		return izq;
	}

	public void setIzq(NodoTra izq)
	{
		this.izq = izq;
	}
	
}
