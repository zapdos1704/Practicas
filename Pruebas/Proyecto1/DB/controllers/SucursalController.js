import SucursalModel from "../models/SucursalesModel.js"

//crear los metodos para realizar el CRUD 

//mostrar todos los registros
export const getAllSucursales = async (req, res) => {
    try {
        const Sucursales = await SucursalModel.findAll()
        res.json(Sucursales)
    }
    catch (error) {
        res.json({ message: error.message })
    }
}

//mostrar solamente un cliente
export const getSucursal = async (req, res) => {
    try {
        const Sucursal = await SucursalModel.findOne({ where: { id: req.params.id } })
        res.json(Sucursal)
    }
    catch (error) {
        res.json({ message: error.message })
    }
}

// crear un cliente
export const createSucursal = async (req, res) => {
    try {
        await SucursalModel.create(req.body)
        res.json({ message: 'registrado correctamente' })
    } catch (error) {
        res.json({ message: error.message })
    }
}

//actualizar un cliente
export const updateSucursal = async (req, res) => {
    try {
        await SucursalModel.update(req.body, { where: { id: req.params.id } })
        res.json({ message: 'actualizado correctamente' })
    } catch (error) {
        res.json({ message: error.message })
    }
}

//eliminar un cliente
export const deleteSucursal = async (req, res) => {
    try {
        await SucursalModel.destroy({ where: { id: req.params.id } })
        res.json({ message: 'eliminado correctamente' })
    } catch (error) {
        res.json({ message: error.message })
    }
}

