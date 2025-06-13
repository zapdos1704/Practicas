package tema3;

import java.util.Vector;
import ed.Datos;
import ed.Formato;

public class PilaDAlumnoV
{
	private Vector<Alumno> pila, aux;
	private Alumno alu;
	private String nc;
	private boolean ban;
	private Datos obd = new Datos();

	public PilaDAlumnoV()
	{
		pila = new Vector<Alumno>(4, 2);
		aux = new Vector<Alumno>(4, 2);
	}

	private boolean Vacia()
	{
		return pila.isEmpty();
	}

	private void Intercambio()
	{
		while (!aux.isEmpty())
			pila.add(aux.remove(aux.size() - 1));
	}

	private void Mensaje()
	{
		Formato obf = new Formato();
		System.out.println(obf.Izq("NO.CTRL", 10) + " | " + obf.Izq("NOMBRE", 25) + " | " + obf.Der("PROM", 5));
	}

	public void Insertar()
	{
		pila.add(new Alumno());
		System.out.println("Alumno almacenado en la pila...");
	}

	public void Extraer()
	{
		if (!this.Vacia())
		{
			System.out.println("Alumno eliminado...");
			this.Mensaje();
			pila.remove(pila.size() - 1).Mostrar();
		}
		else
			System.out.println("Pila vacia...");
	}

	public void Recorrido()
	{
		int con = 1;
		if (!this.Vacia())
		{
			System.out.println("Alumno eliminado...");
			while (!this.Vacia())
			{
				alu = pila.remove(pila.size() - 1);
				alu.Mostrar();
				if (con == 5)
				{
					obd.Enter();
					con = 0;
				}

				con++;
				aux.add(alu);
			}
			this.Intercambio();

		}
		else
			System.out.println("Pila vacia...");
	}

	public void Busqueda()
	{
		if (!this.Vacia())
		{
			ban = true;
			nc = obd.Cadena("Nombre o apellido a buscar").toUpperCase();
			while (!this.Vacia())
			{
				alu = pila.remove(pila.size() - 1);
				if (alu.Nombre().contains(nc))
				{
					if (ban)
						System.out.println("Alumnos encontrados...");
					this.Mensaje();
					alu.Mostrar();
					ban = false;
				}
				aux.add(alu);
			}
			this.Intercambio();
			if (ban)
				System.out.println("El alumno no esta en la pila...");
		}
		else
			System.out.println("Pila vacia...");
	}

	public void Modificar()
	{
		if (!this.Vacia())
		{
			ban = true;
			nc = obd.Cadena("No. Control a Modificar").toUpperCase();
			while (!this.Vacia())
			{
				alu = pila.remove(pila.size() - 1);
				if (alu.NoControl().equals(nc))
				{
					System.out.println("Alumno encontrado...");
					this.Mensaje();
					alu.Mostrar();
					alu.Modificar();
					ban = false;
					aux.add(alu);
					break;
				}
				aux.add(alu);
			}
			this.Intercambio();
			if (ban)
				System.out.println("El alumno no esta en la pila...");
		}
		else
			System.out.println("Pila vacia...");
	}

	public void Borrar()
	{
		pila.clear();
		System.out.println("Pila borrada...");
	}
}
