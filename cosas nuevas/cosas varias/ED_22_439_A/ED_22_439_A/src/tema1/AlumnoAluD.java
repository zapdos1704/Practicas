package tema1;

public class AlumnoAluD
{
	private String ncn;
	private boolean ban;
	private int pos;
	private ed.Datos obd = new ed.Datos();
	private ed.Formato obf = new ed.Formato();
	private java.util.Vector<AlumnoD> alu = new java.util.Vector<AlumnoD>(5, 3);

	public void Insertar()
	{
		if (alu.add(new AlumnoD()))
			System.out.println("Alumno agregado correctamente...\n");
		else
			System.out.println("El alumno no se puede almacenar...\n");
	}

	public void Consulta()
	{
		if (!alu.isEmpty())
		{
			System.out.println(obf.Der("NO CTRL", 10) + " | " + obf.Izq("NOMBRE", 25) + " | " + obf.Der("PROMEDIO", 4));
			for (AlumnoD a : alu)
				a.Mostrar();
			System.out.println("Fin de la lista...\n");
		}
		else
			System.out.println("No hay datos de la lista...\n");
	}

	public void Modificar()
	{
		if (!alu.isEmpty())
		{
			ban = true;
			ncn = obd.Cadena("No Control a modificar");
			for (AlumnoD a : alu)
				if (a.NoControl().equals(ncn))
				{
					a.Modificar();
					System.out.println("Alumno modifiado...\n");
					ban = false;
					break;
				}
			if (ban)
				System.out.println("El alumno no existe...\n");
		}
		else
			System.out.println("No hay datos de la lista...\n");
	}

	public void Buscar()
	{
		if (!alu.isEmpty())
		{
			ban = true;
			ncn = obd.Cadena("No Control a buscar");
			for (AlumnoD a : alu)
				if (a.NoControl().equals(ncn))
				{
					System.out.println("Alumno encontrado...\n");
					a.Mostrar();
					ban = false;
					break;
				}
			if (ban)
				System.out.println("El alumno no existe...\n");
		}
		else
			System.out.println("No hay datos de la lista...\n");
	}

	public void Eliminar()
	{
		if (!alu.isEmpty())
		{
			ban = true;
			ncn = obd.Cadena("No Control a buscar");
			pos = 0;
			for (AlumnoD a : alu)
			{
				if (a.NoControl().equals(ncn))
				{
					System.out.println("Alumno encontrado...\n");
					a.Mostrar();
					;
					System.out.println("Alumno eliminado...\n");
					alu.remove(pos);
					ban = false;
					break;
				}
				pos++;
			}

			if (ban)
				System.out.println("El alumno no existe...\n");
		}
		else
			System.out.println("No hay datos de la lista...\n");
	}

}
