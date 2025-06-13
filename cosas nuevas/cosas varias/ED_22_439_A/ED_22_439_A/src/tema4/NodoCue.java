package tema4;

public class NodoCue
{
	private Cuenta cue;
	private NodoCue izq, der, pad;

	public NodoCue(Cuenta cue)
	{
		this.cue = cue;
		izq = der = null;
		pad = null;
	}

	public Cuenta getCue()
	{
		return cue;
	}

	public void setCue(Cuenta cue)
	{
		this.cue = cue;
	}

	public NodoCue getIzq()
	{
		return izq;
	}

	public void setIzq(NodoCue izq)
	{
		this.izq = izq;
	}

	public NodoCue getDer()
	{
		return der;
	}

	public void setDer(NodoCue der)
	{
		this.der = der;
	}

	public NodoCue getPad()
	{
		return pad;
	}

	public void setPad(NodoCue pad)
	{
		this.pad = pad;
	}

}
