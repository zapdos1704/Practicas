import express from 'express'
import cors from 'cors'
import db from './dataBase/db.js'
import UsuarioRoutes from './routes/UsuarioRoutes.js'
import DulceRoutes from './routes/DulceRoutes.js'
import SucursalRoutes from './routes/SucursalRoutes.js'

const app = express()
app.use(cors())
app.use(express.json())
app.use('/api/usuarios', UsuarioRoutes) // http://localhost:3000/api/usuarios/
app.use('/api/dulces', DulceRoutes) // http://localhost:3000/api/dulces/
app.use('/api/sucursales', SucursalRoutes) // http://localhost:3000/api/sucursales/

try {
    await db.authenticate();
    console.log('Connection has been established successfully.');
} catch (error) {
    console.error('Unable to connect to the database:', error);
}

app.listen(3000, () => {
    console.log('Servidor funcionando: http://localhost:3000')
})