package tema3;

public class NodoDAlu
{
	private Alumno alu;
	private NodoDAlu ant, sig;

	public NodoDAlu(Alumno alu)
	{
		ant = null;
		this.alu = alu;
		sig = null;
	}

	public Alumno getAlu()
	{
		return alu;
	}

	public void setAlu(Alumno alu)
	{
		this.alu = alu;
	}

	public NodoDAlu getAnt()
	{
		return ant;
	}

	public void setAnt(NodoDAlu ant)
	{
		this.ant = ant;
	}

	public NodoDAlu getSig()
	{
		return sig;
	}

	public void setSig(NodoDAlu sig)
	{
		this.sig = sig;
	}

}
