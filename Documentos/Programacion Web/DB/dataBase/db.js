import { Sequelize } from 'sequelize';
import MyConfig from './config.js';

// Option 3: Passing parameters separately (other dialects)
const db = new Sequelize(
    MyConfig.mysql.database,
    MyConfig.mysql.user,
    MyConfig.mysql.password,
    {
        host: MyConfig.mysql.host,
        dialect: 'mysql',
        // se crea el pull de conexiones mas de 1 
        pool: {
            max: 5,
            idle: 30000,
            acquire: 60000,
        },
    }

);

export default db;

