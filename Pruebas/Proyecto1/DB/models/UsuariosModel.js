import { DataTypes } from "sequelize";
import db from "../dataBase/db.js";

const UsuarioModel = db.define(
    'Usuarios',
    {
        // Model attributes are defined here
        nombre: {
            type: DataTypes.STRING,
        },
        us: {
            type: DataTypes.STRING,
            unique: true
        },
        email: {
            type: DataTypes.STRING,
            unique: true
        },
        clave: {
            type: DataTypes.STRING,
        },
        telefono: {
            type: DataTypes.STRING,
            unique: true
        },
        est: {
            type: DataTypes.BOOLEAN
        }


    })
export default UsuarioModel
