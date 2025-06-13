package tema3;

public class NodoCSM
{
	private MateriaOp mat;
	private NodoCSM sig;

	public NodoCSM(MateriaOp mat)
	{
		this.mat = mat;
		sig = null;
	}

	public MateriaOp getMat()
	{
		return mat;
	}

	public void setMat(MateriaOp mat)
	{
		this.mat = mat;
	}

	public NodoCSM getSig()
	{
		return sig;
	}

	public void setSig(NodoCSM sig)
	{
		this.sig = sig;
	}

}
