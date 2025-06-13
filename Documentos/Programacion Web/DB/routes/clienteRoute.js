import express from 'express'
import { createCliente, deleteCliente, getAllClientes, getCliente, updateCliente } from '../controllers/clienteControl.js'
const router = express.Router()

//crear las rutas para clientes
router.get('/', getAllClientes)
router.get('/:id', getCliente)
router.post('/', createCliente)
router.put('/:id', updateCliente)
router.delete('/:id', deleteCliente)


export default router