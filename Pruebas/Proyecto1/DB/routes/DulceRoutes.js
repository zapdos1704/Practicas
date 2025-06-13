import express from 'express'
import { createDulce, deleteDulce, getAllDulces, getDulce, updateDulce } from '../controllers/DulceController.js'
const router = express.Router()

//crear las rutas para clientes
router.get('/', getAllDulces)
router.get('/:id', getDulce)
router.post('/', createDulce)
router.put('/:id', updateDulce)
router.delete('/:id', deleteDulce)


export default router