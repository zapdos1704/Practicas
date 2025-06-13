import UsuarioModel from "../models/UsuariosModel.js"

//crear los metodos para realizar el CRUD 

//mostrar todos los registros
export const getAllUsuarios = async (req, res) => {
    try {
        const Usuarios = await UsuarioModel.findAll()
        res.json(Usuarios)
    }
    catch (error) {
        res.json({ message: error.message })
    }
}

//mostrar solamente un cliente
export const getUsuario = async (req, res) => {
    try {
        const Usuarios = await UsuarioModel.findOne({ where: { id: req.params.id } })
        res.json(Usuarios)
    }
    catch (error) {
        res.json({ message: error.message })
    }
}

// crear un cliente
export const createUsuarios = async (req, res) => {
    try {
        await UsuarioModel.create(req.body)
        res.json({ message: 'registrado correctamente' })
    } catch (error) {
        res.json({ message: error.message })
    }
}

//actualizar un cliente
export const updateUsuarios = async (req, res) => {
    try {
        await UsuarioModel.update(req.body, { where: { id: req.params.id } })
        res.json({ message: 'actualizado correctamente' })
    } catch (error) {
        res.json({ message: error.message })
    }
}

//eliminar un cliente
export const deleteUsuarios = async (req, res) => {
    try {
        await UsuarioModel.destroy({ where: { id: req.params.id } })
        res.json({ message: 'eliminado correctamente' })
    } catch (error) {
        res.json({ message: error.message })
    }
}

//validar un cliente
export const loginCliente = async (req, res) => {
    try {
        res.json({ message: 'validando correctamente' })
    } catch (error) {
        res.json({ message: error.message })
    }
}