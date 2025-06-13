package tema3;

public class Nodos
{
	private String dato;
	private Nodos sig;

	public Nodos(String dato)
	{
		this.dato = dato;
		sig = null;
	}

	public String getDato()
	{
		return dato;
	}

	public void setDato(String dato)
	{
		this.dato = dato;
	}

	public Nodos getSig()
	{
		return sig;
	}

	public void setSig(Nodos sig)
	{
		this.sig = sig;
	}

}
