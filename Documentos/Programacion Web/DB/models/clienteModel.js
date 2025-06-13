import { DataTypes } from "sequelize";
import db from '../dataBase/db.js';

const EstudianteModel = db.define(
	'Estudiante',
	{
		// Model attributes are defined here
		// id se crea automaticamente
		nombreEst: { type: DataTypes.STRING },
		apellidoPatEst: { type: DataTypes.STRING },
		apellidoMatEst: { type: DataTypes.STRING },
		domicilioEst: { type: DataTypes.STRING },
		ciudadEst: { type: DataTypes.STRING },
		telefonoEst: { type: DataTypes.STRING, unique: true },
		correoEst: { type: DataTypes.STRING, unique: true },
		controlEst: { type: DataTypes.STRING, unique: true },
		// fecha de registro se autoguarda
		// la fecha de modificación se autoactualiza
	}
);

export default EstudianteModel;