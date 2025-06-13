package trabajos;

public class Fracciones
{
	int num, den, a, b, m;

	public String Fraccion(int num, int den, int num1, int den1, String ti)
	{
		boolean ban = true;
		String val = "";
		if (den != 0)
		{
			if (ti.equals("m"))
			{
				this.den = den1 * den;
				this.num = num1 * num;
				ban = false;
			}
			if (ti.equals("s"))
			{
				if (den1 == den)
				{
					this.den = den;
					this.num = num1 + num;
				}
				else
				{
					this.den = den1 * den;
					this.num = (num1 * den) + (num * den1);
				}
				ban = false;
			}
			if (ti.equals("div"))
			{
				this.den = den + den1;
				this.num = num + num1;
				if (this.den == this.num)
				{
					val = "1";
				}
				else
					ban = false;
			}
			if (!ban)
			{
				m = this.MaximoCD(this.den, this.num);
				this.den /= m;
				this.num /= m;
				if (this.den < 0 && this.num < 0)
				{
					this.den *= -1;
					this.num *= -1;
				}
				if (this.den < 0 && this.num > 0)
				{
					this.den *= -1;
					this.num *= -1;
				}
				val = this.num + "/" + this.den;
			}
		}
		else
			System.out.println("Infinito");
		return val;

	}

	private int MaximoCD(int num1, int num2)
	{
		int res = 0;
		a = Math.max(num1, num2);
		b = Math.min(num1, num2);
		do
		{
			res = b;
			b = a % b;
			a = res;
		}
		while (b != 0);

		return res;
	}

}
