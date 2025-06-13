import DulceModel from "../models/DulcesModel.js"

//crear los metodos para realizar el CRUD 

//mostrar todos los registros
export const getAllDulces = async (req, res) => {
    try {
        const Dulces = await DulceModel.findAll()
        res.json(Dulces)
    }
    catch (error) {
        res.json({ message: error.message })
    }
}

//mostrar solamente un cliente
export const getDulce = async (req, res) => {
    try {
        const Dulces = await DulceModel.findOne({ where: { id: req.params.id } })
        res.json(Dulces)
    }
    catch (error) {
        res.json({ message: error.message })
    }
}

// crear un cliente
export const createDulce = async (req, res) => {
    try {
        await DulceModel.create(req.body)
        res.json({ message: 'registrado correctamente' })
    } catch (error) {
        res.json({ message: error.message })
    }
}

//actualizar un cliente
export const updateDulce = async (req, res) => {
    try {
        await DulceModel.update(req.body, { where: { id: req.params.id } })
        res.json({ message: 'actualizado correctamente' })
    } catch (error) {
        res.json({ message: error.message })
    }
}

//eliminar un cliente
export const deleteDulce = async (req, res) => {
    try {
        await DulceModel.destroy({ where: { id: req.params.id } })
        res.json({ message: 'eliminado correctamente' })
    } catch (error) {
        res.json({ message: error.message })
    }
}

