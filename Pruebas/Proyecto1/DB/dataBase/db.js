import { Sequelize } from 'sequelize'
import MyConfig from './config.js'

// Option 3: Passing parameters separately (other dialects)
const db = new Sequelize(MyConfig.mysql.database, MyConfig.mysql.user, MyConfig.mysql.password, {
    host: MyConfig.mysql.host,
    dialect: 'mysql',/* | 'postgres' | 'sqlite' | 'mariadb' | 'mssql' | 'db2' | 'snowflake' | 'oracle' */
    //poll de conexion para permitir mas de 1 conexion simultanea
    pool: {
        max: 5,
        idle: 3000,
        acquire: 6000
    }
});

export default db;