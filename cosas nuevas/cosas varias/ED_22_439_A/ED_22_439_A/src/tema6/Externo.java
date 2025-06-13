package tema6;

import java.io.RandomAccessFile;
import ed.Datos;
import java.util.Random;
import java.io.IOException;
import java.io.EOFException;

public class Externo
{
	private String archivo = "archivo.isc", archivo1 = "archivo1.isc", archivo2 = "archivo2.isc";
	private RandomAccessFile oba, oba1, oba2;
	private Datos obd = new Datos();
	private Random obr = new Random();

	public Externo()
	{
		int can, con, num;
		do
			can = obd.Entero("Cantida de datos");
		while (can < 1);
		this.Borrar();
		for (con = 0; con < can; con++)
		{
			do
				num = obr.nextInt(can * 100) + 1;
			while (this.Existe(num));
			try
			{
				oba = new RandomAccessFile(archivo, "rw");
				oba.seek(con * 4);
				oba.writeInt(num);
				oba.close();
			}
			catch (IOException e)
			{
			}
		}
		this.Mostrar(archivo);
		this.MezclaNatural();
	}

	private boolean Existe(int num)
	{
		int can, con;
		boolean ban = false;
		try
		{
			oba = new RandomAccessFile(archivo, "r");
			can = (int) oba.length() / 4;
			for (con = 1; con <= can; con++)
				if (oba.readInt() == num)
					break;
			oba.close();
			ban = con <= can;
		}
		catch (IOException e)
		{
		}
		return ban;
	}

	private void Borrar()
	{
		try (RandomAccessFile oba = new RandomAccessFile(archivo, "rw"))
		{
			oba.setLength(0);
		}
		catch (IOException e)
		{
		}
	}

	private void Mostrar(String archivo)
	{
		int num, con = 1;
		try
		{
			oba = new RandomAccessFile(archivo, "r");
			try
			{
				System.out.println("\nContenido del archivo...");
				while (true)
				{
					num = oba.readInt();
					System.out.print(num + "\t");
					if (con % 5 == 0)
						System.out.println();
					con++;
				}
			}
			catch (EOFException e)
			{
				oba.close();
			}
			oba.close();
		}
		catch (IOException e)
		{
		}
	}

	public void MezclaNatural()
	{
		int can;
		do
		{
			can = this.Particion();
			if (can != 0)
				this.Fusion();
		}
		while (can != 0);
		this.Mostrar(archivo);
	}

	private int Particion()
	{
		int num, ant = 0, can = 0, con;
		boolean arch = true;
		try
		{
			oba = new RandomAccessFile(archivo, "r");
			can = (int) oba.length() / 4;
			oba1 = new RandomAccessFile(archivo1, "rw");
			oba1.setLength(0);
			oba2 = new RandomAccessFile(archivo2, "rw");
			oba2.setLength(0);
			for (con = 1; con <= can; con++)
			{
				num = oba.readInt();
				if (num < ant)
					arch = !arch;
				if (arch)
					oba1.writeInt(num);
				else
					oba2.writeInt(num);
				ant = num;
			}
			can = (int) oba2.length() / 4;
			oba.close();
			oba1.close();
			oba2.close();

		}
		catch (IOException e)
		{
		}
		return can;
	}

	private void Fusion()
	{
		int num1, num2, con = 0;
		try
		{
			oba = new RandomAccessFile(archivo, "rw");
			oba.setLength(0);
			oba1 = new RandomAccessFile(archivo1, "r");
			oba2 = new RandomAccessFile(archivo2, "r");
			num1 = oba1.readInt();
			num2 = oba2.readInt();
			try
			{
				while (true)
				{
					if (num1 < num2)
					{
						oba.writeInt(num1);
						con++;
						num1 = oba1.readInt();
					}
					else
					{
						oba.writeInt(num2);
						num2 = oba2.readInt();
					}
				}
			}
			catch (EOFException e)
			{
				if (con == oba1.length() / 4)
					try
					{
						while (true)
						{
							oba.writeInt(num2);
							num2 = oba2.readInt();
						}
					}
					catch (EOFException ee)
					{
						oba1.close();
						oba2.close();
					}
				else
					try
					{
						while (true)
						{
							oba.writeInt(num1);
							num1 = oba1.readInt();
						}
					}
					catch (EOFException ee)
					{
						oba1.close();
						oba2.close();
					}
				oba.close();
			}
		}
		catch (IOException e)
		{
		}
	}

	public void Secuencial()
	{
		int num, pos, can;
		do
			num = obd.Entero("Numero a buscar");
		while (num < 1);
		try
		{
			oba = new RandomAccessFile(archivo, "r");
			can = (int) oba.length() / 4;
			for (pos = 1; pos <= can && oba.readInt() != num; pos++)
				;
			if (pos <= can)
				System.out.println("Encontrado en la posicion " + pos + "...");
			else
				System.out.println("El numero no esta en el archivo...");
			oba.close();
		}
		catch (IOException e)
		{
		}
	}

	private int Cantidad()
	{
		int can = 0;
		try (RandomAccessFile oba = new RandomAccessFile(archivo, "rw"))
		{
			can = (int) oba.length() / 4;
		}
		catch (IOException e)
		{
		}
		return can;
	}

	public void Binaria()
	{
		int num, pos, can = this.Cantidad();
		do
			num = obd.Entero("\nCual es el numero a buscar");
		while (num < 1);
		try
		{
			oba = new RandomAccessFile(archivo, "r");
			pos = this.Binaria(0, can - 1, num);
			oba.seek(pos * 4);
			if (oba.readInt() == num)
				System.out.println("Encontrado en la posicion " + (pos + 1) + "...");
			else
				System.out.println("El numero no esta en el archivo...");
			oba.close();
		}
		catch (IOException e)
		{
		}
	}

	public int Binaria(int ini, int fin, int num) throws IOException
	{
		int cen = (ini + fin) / 2, x;
		oba.seek(cen * 4);
		x = oba.readInt();
		if (x != num && ini <= fin)
			if (num > x)
				cen = this.Binaria(cen + 1, fin, num);
			else
				cen = this.Binaria(ini, cen - 1, num);
		return cen;
	}
}
