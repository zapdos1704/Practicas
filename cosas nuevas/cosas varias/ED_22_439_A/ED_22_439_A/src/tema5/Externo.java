package tema5;

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

	public void MezclaDirecta()
	{
		int par = 1, can = this.Cantidad();
		do
		{
			this.Particion(par);
			this.Fusion(par);
			par *= 2;
		}
		while (par < can);
		this.Mostrar(archivo);
	}

	private void Particion(int par)
	{
		int con;
		try
		{
			oba = new RandomAccessFile(archivo, "r");
			oba1 = new RandomAccessFile(archivo1, "rw");
			oba1.setLength(0);
			oba2 = new RandomAccessFile(archivo2, "rw");
			oba2.setLength(0);
			try
			{
				while (true)
				{
					for (con = 1; con <= par; con++)
						oba1.writeInt(oba.readInt());
					for (con = 1; con <= par; con++)
						oba2.writeInt(oba.readInt());
				}
			}
			catch (EOFException e)
			{
				oba.close();
				oba1.close();
				oba2.close();
			}
		}
		catch (IOException e)
		{
		}
	}

	public void Fusion(int par)
	{
		int num1, num2, con1, con2, con = 0;

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
					con1 = 1;
					con2 = 1;
					do
					{
						if (num1 < num2)
						{
							oba.writeInt(num1);
							con1++;
							con++;
							num1 = oba1.readInt();
						}
						else
						{
							oba.writeInt(num2);
							con2++;
							num2 = oba2.readInt();
						}
					}
					while (con1 <= par && con2 <= par);
					if (con1 <= par)
						while (con1 <= par)
						{
							oba.writeInt(num1);
							con1++;
							con++;
							num1 = oba1.readInt();
						}
					else
						while (con2 <= par)
						{
							oba.writeInt(num2);
							con2++;
							num2 = oba2.readInt();
						}
				}
			}
			catch (EOFException e)
			{
				if (oba1.length() / 4 == con)
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
}
