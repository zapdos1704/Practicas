import express from 'express'
import { createSucursal, deleteSucursal, getAllSucursales, getSucursal, updateSucursal } from '../controllers/SucursalController.js'
const router = express.Router()

//crear las rutas para clientes
router.get('/', getAllSucursales)
router.get('/:id', getSucursal)
router.post('/', createSucursal)
router.put('/:id', updateSucursal)
router.delete('/:id', deleteSucursal)


export default router