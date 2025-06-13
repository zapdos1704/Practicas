package tema4;

public class Nodo2
{
	private int num;
	private Nodo2 der,izq;
	
	public Nodo2(int num)
	{
		this.num=num;
		der=izq=null;
	}

	public int getNum()
	{
		return num;
	}

	public void setNum(int num)
	{
		this.num = num;
	}

	public Nodo2 getDer()
	{
		return der;
	}

	public void setDer(Nodo2 der)
	{
		this.der = der;
	}

	public Nodo2 getIzq()
	{
		return izq;
	}

	public void setIzq(Nodo2 izq)
	{
		this.izq = izq;
	}
	
	
}
