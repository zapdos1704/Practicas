import { DataTypes } from "sequelize";
import db from "../dataBase/db.js";

const SucursalModel = db.define(
    'Sucursales',
    {
        // Model attributes are defined here
        telefono: {
            type: DataTypes.STRING,
            unique: true
        },
        ubicacion: {
            type: DataTypes.STRING,
            unique: true
        },
        est: {
            type: DataTypes.BOOLEAN,
        }

    })
export default SucursalModel
