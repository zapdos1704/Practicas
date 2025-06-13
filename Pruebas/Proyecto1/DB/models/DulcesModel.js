import { DataTypes } from "sequelize";
import db from "../dataBase/db.js";

const DulceModel = db.define(
    'Dulces',
    {
        // Model attributes are defined here
        tipo: {
            type: DataTypes.STRING,
        },
        precio: {
            type: DataTypes.INTEGER,

        },
        exis: {
            type: DataTypes.INTEGER,
        },
        nombre: {
            type: DataTypes.STRING,
        }


    })
export default DulceModel
