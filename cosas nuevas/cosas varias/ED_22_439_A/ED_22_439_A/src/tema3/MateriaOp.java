package tema3;

import ed.*;

public class MateriaOp
{
	private Materia mat;
	private Datos obd = new Datos();

	public void Nueva(int clv)
	{
		mat = new Materia();
		mat.setClv(clv);
		System.out.println("Escribe los datos de la materia...");
		System.out.println("Clave " + mat.getClv());
		mat.setNom(obd.Cadena("Nombre de la materia").toUpperCase());
		do
			mat.setHt(obd.Entero("Horas teoricas"));
		while (mat.getHt() < 1);
		do
			mat.setHp(obd.Entero("Horas practicas"));
		while (mat.getHp() < 1);
		mat.setCred(mat.getHt() + "-" + mat.getHp() + "-" + (mat.getHp() + mat.getHt()));
		do
			mat.setPlan(obd.Entero("Plan de estudios"));
		while (mat.getPlan() < 1);
	}

	public void Modificar()
	{
		int op;
		Menu obm = new Menu("Modificaciones", new String[] { "Nombre", "Creditos", "Plan" });
		do
			switch (op = obm.Opcion())
			{
				case 1:
					System.out.println("Nombre de la materia " + mat.getNom());
					mat.setNom(obd.Cadena("Nuevo Nombre").toUpperCase());
					break;
				case 2:
					System.out.println("Creditos de la materia " + mat.getCred());
					do
						mat.setHt(obd.Entero("Horas teoricas"));
					while (mat.getHt() < 1);
					do
						mat.setHp(obd.Entero("Horas practicas"));
					while (mat.getHp() < 1);
					mat.setCred(mat.getHt() + "-" + mat.getHp() + "-" + (mat.getHp() + mat.getHt()));
					break;
				case 3:
					System.out.println("Plan de estudio " + mat.getPlan());
					do
						mat.setPlan(obd.Entero("Nuevo plan"));
					while (mat.getPlan() < 1);
			}
		while (op != obm.Salir());
	}

	public void Mostrar()
	{
		Formato obf = new Formato();
		System.out.println(obf.Izq(mat.getClv() + "", 5) + " | " + obf.Izq(mat.getNom(), 25) + " | "
				+ obf.Izq(mat.getCred() + "", 10) + " | " + obf.Izq(mat.getPlan() + "", 6));
	}

	public int Clave()
	{
		return mat.getClv();
	}

	public int Plan()
	{
		return mat.getPlan();
	}

	public String Nombre()
	{
		return mat.getNom();
	}
}
