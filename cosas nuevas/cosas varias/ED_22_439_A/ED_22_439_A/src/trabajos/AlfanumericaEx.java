package trabajos;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

import ed.Datos;

public class AlfanumericaEx
{
	private String archivo = "archivo.isc", archivo1 = "archivo1.isc", archivo2 = "archivo2.isc";
	private RandomAccessFile oba, oba1, oba2;
	private Datos obd = new Datos();
	private Random obr = new Random();

	public AlfanumericaEx()
	{
		int can, con, pos;
		String cad = "0123456789AEIOU", str = "";
		do
			can = obd.Entero("Cantida de datos");
		while (can < 1);
		this.Borrar();
		for (con = 0; con < can; con++)
		{
			str = "";
			do
			{
				for (pos = 0; pos < 6; pos++)
					str += cad.charAt(obr.nextInt(cad.length()));
			}
			while (this.Existe(str));
			try
			{
				oba = new RandomAccessFile(archivo, "rw");
				oba.seek(con * 8);
				oba.writeUTF(str);
				oba.close();
			}
			catch (IOException e)
			{
			}
		}
		this.Mostrar(archivo);
		this.MezclaNatural();
	}

	private void Mostrar(String archivo)
	{
		int con = 1;
		String cad;
		try
		{
			oba = new RandomAccessFile(archivo, "r");
			try
			{
				System.out.println("\nContenido del archivo...");
				while (true)
				{
					cad = oba.readUTF();
					System.out.print(cad + "\t");
					if (con % 10 == 0)
						System.out.println();
					con++;
				}
			}
			catch (EOFException e)
			{
				oba.close();
			}
		}
		catch (IOException e)
		{
		}
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

	private boolean Existe(String cad)
	{
		int can, con;
		boolean ban = false;
		try
		{
			oba = new RandomAccessFile(archivo, "r");
			can = (int) oba.length() / 8;
			for (con = 1; con <= can; con++)
				if (oba.readUTF().equals(cad))
					break;
			oba.close();
			ban = con <= can;
		}
		catch (IOException e)
		{
		}
		return ban;
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
		int can = 0, con;
		String cad, ant = "";
		boolean arch = true;
		try
		{
			oba = new RandomAccessFile(archivo, "r");
			can = (int) oba.length() / 8;
			oba1 = new RandomAccessFile(archivo1, "rw");
			oba1.setLength(0);
			oba2 = new RandomAccessFile(archivo2, "rw");
			oba2.setLength(0);
			for (con = 1; con <= can; con++)
			{
				cad = oba.readUTF();
				if (cad.compareTo(ant) < 0)
					arch = !arch;
				if (arch)
					oba1.writeUTF(cad);
				else
					oba2.writeUTF(cad);
				ant = cad;
			}
			can = (int) oba2.length() / 8;
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
		int con = 0;
		String cad1, cad2;
		try
		{
			oba = new RandomAccessFile(archivo, "rw");
			oba.setLength(0);
			oba1 = new RandomAccessFile(archivo1, "r");
			oba2 = new RandomAccessFile(archivo2, "r");
			cad1 = oba1.readUTF();
			cad2 = oba2.readUTF();
			try
			{
				while (true)
				{
					if (cad1.compareTo(cad2) < 0)
					{
						oba.writeUTF(cad1);
						con++;
						cad1 = oba1.readUTF();
					}
					else
					{
						oba.writeUTF(cad2);
						cad2 = oba2.readUTF();
					}
				}
			}
			catch (EOFException e)
			{
				if (con == oba1.length() / 8)
					try
					{
						while (true)
						{
							oba.writeUTF(cad2);
							cad2 = oba2.readUTF();
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
							oba.writeUTF(cad1);
							cad1 = oba1.readUTF();
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
