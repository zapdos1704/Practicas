import express from 'express'
import { createUsuarios, deleteUsuarios, getAllUsuarios, getUsuario, updateUsuarios } from '../controllers/UsuarioController.js'

const router = express.Router()

//crear las rutas para clientes
router.get('/', getAllUsuarios)
router.get('/:id', getUsuario)
router.post('/', createUsuarios)
router.put('/:id', updateUsuarios)
router.delete('/:id', deleteUsuarios)


export default router