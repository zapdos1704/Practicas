package tema3;

public class NodoST
{
	private Trabajador dato;
	private NodoST sig;

	public NodoST(Trabajador dato)
	{
		this.dato = dato;
		sig = null;
	}

	public Trabajador getDato()
	{
		return dato;
	}

	public void setDato(Trabajador dato)
	{
		this.dato = dato;
	}

	public NodoST getSig()
	{
		return sig;
	}

	public void setSig(NodoST sig)
	{
		this.sig = sig;
	}
}
