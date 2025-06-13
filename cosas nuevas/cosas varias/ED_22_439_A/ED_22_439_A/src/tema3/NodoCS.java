package tema3;

public class NodoCS
{
	private int num;
	private NodoCS sig;
	
	public NodoCS(int num)
	{
		this.num=num;
		sig=null;
	}

	public int getNum()
	{
		return num;
	}

	public void setNum(int num)
	{
		this.num = num;
	}

	public NodoCS getSig()
	{
		return sig;
	}

	public void setSig(NodoCS sig)
	{
		this.sig = sig;
	}
	
	
}
