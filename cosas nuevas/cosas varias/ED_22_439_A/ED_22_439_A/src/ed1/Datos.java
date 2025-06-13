package ed1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Datos
{
	private BufferedReader obb = new BufferedReader(new InputStreamReader(System.in));

	public int Entero(String msj)
	{
		int num = 0;
		try
		{
			System.out.print(msj + "? ");
			num = Integer.parseInt(obb.readLine().trim());
		}
		catch (NumberFormatException e)
		{
			num = this.Entero(msj);
		}
		catch (IOException e)
		{
		}
		return num;
	}

	public double Doble(String msj)
	{
		double num = 0;
		try
		{
			System.out.print(msj + "? ");
			num = Double.parseDouble(obb.readLine().trim());
		}
		catch (NumberFormatException e)
		{
			num = this.Doble(msj);
		}
		catch (IOException e)
		{
		}
		return num;
	}

	public String Cadena(String msj)
	{
		String cad = "";
		try
		{
			System.out.print(msj + "? ");
			cad = obb.readLine().trim();
			if (cad.isEmpty())
			{
				throw new StringIndexOutOfBoundsException();
			}
		}
		catch (StringIndexOutOfBoundsException e)
		{
			cad = this.Cadena(msj);
		}
		catch (IOException e)
		{
		}
		return cad;
	}

	public char Caracter(String msj)
	{
		char car = ' ';
		try
		{
			System.out.print(msj + "? ");
			car = obb.readLine().trim().charAt(0);
		}
		catch (StringIndexOutOfBoundsException e)
		{
			car = this.Caracter(msj);
		}
		catch (IOException e)
		{
		}
		return car;
	}

	public float Flotante(String msj)
	{
		float car = 0;
		try
		{
			System.out.print(msj + "? ");
			car = Float.parseFloat(obb.readLine().trim());
		}
		catch (NumberFormatException e)
		{
			car = this.Flotante(msj);
		}
		catch (IOException e)
		{
		}
		return car;
	}

	public void Enter()
	{
		String cad = "";
		try
		{
			System.out.print("Presiona <<enter>> para continuar...");
			cad = obb.readLine().trim();
			if (!cad.isEmpty())
				throw new StringIndexOutOfBoundsException();
		}
		catch (StringIndexOutOfBoundsException e)
		{
			this.Enter();
		}
		catch (IOException e)
		{
		}
	}
}
