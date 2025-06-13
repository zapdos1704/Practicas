import express from 'express'
import cors from 'cors'
import clienteRoute from './routes/clienteRoute.js'
// Crear la aplicación
const app = express()

//configurción de la aplicación
app.use(cors())
app.use(express.json())
app.use('/api/Estudiante', clienteRoute)

//Probar la conexion al Servidor de la Base de datos
// try {
//     await sequelize.authenticate();
//     console.log('Connection has been established successfully.');
// } catch (error) {
//     console.error('Unable to connect to the database:', error);
// }


// Crear ruta de servicio get
app.get('/', (req, res) => {
    res.send('Bienvenido al back-end de Alumnos version 1.0')
})

//puerto donde se ejecuta el servicio de la aplicacion
app.listen(3000, () => {
    console.log('Servidor esta funcionando el: http://localhost:3000')
})
