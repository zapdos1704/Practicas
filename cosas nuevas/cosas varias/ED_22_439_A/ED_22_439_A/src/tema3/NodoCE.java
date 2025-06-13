package tema3;

public class NodoCE
{
	private int num;
	private NodoCE sig, ant;

	public NodoCE(int num)
	{
		this.num = num;
		sig = null;
	}

	public int getNum()
	{
		return num;
	}

	public void setNum(int num)
	{
		this.num = num;
	}

	public NodoCE getSig()
	{
		return sig;
	}

	public void setSig(NodoCE sig)
	{
		this.sig = sig;
	}

	public NodoCE getAnt()
	{
		return ant;
	}

	public void setAnt(NodoCE ant)
	{
		this.ant = ant;
	}
}
