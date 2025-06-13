const express = require('express');
const { Pool } = require('pg');
const cors = require('cors');

// Inicializar la aplicación Express
const app = express();
const port = process.env.PORT || 3000;

// Middleware
app.use(cors());
app.use(express.json());

// Configuración de la conexión a la base de datos
const pool = new Pool({
	user: process.env.DB_USER || 'admin',
	host: process.env.DB_HOST || 'db',
	database: process.env.DB_NAME || 'dulces_db',
	password: process.env.DB_PASSWORD || 'password',
	port: process.env.DB_PORT || 5432,
});

// Verificar la conexión a la base de datos
pool.connect((err, client, release) => {
	if (err) {
		return console.error('Error al conectar a la base de datos:', err);
	}
	console.log('Conexión a la base de datos establecida con éxito');
	release();
});

// Rutas CRUD

// GET - Obtener todos los dulces
app.get('/api/dulces', async (req, res) => {
	try {
		const result = await pool.query('SELECT * FROM dulces ORDER BY id_dulce');
		res.json(result.rows);
	} catch (err) {
		console.error(err);
		res.status(500).json({ error: 'Error al obtener los dulces' });
	}
});

// GET - Obtener un dulce por ID
app.get('/api/dulces/:id', async (req, res) => {
	const { id } = req.params;
	try {
		const result = await pool.query('SELECT * FROM dulces WHERE id_dulce = $1', [id]);
		if (result.rows.length === 0) {
			return res.status(404).json({ error: 'Dulce no encontrado' });
		}
		res.json(result.rows[0]);
	} catch (err) {
		console.error(err);
		res.status(500).json({ error: 'Error al obtener el dulce' });
	}
});

// POST - Crear un nuevo dulce
app.post('/api/usuarios', async (req, res) => {
	const { nombre, rfc, fecha_nac, fecha_asc, telefono, tipo } = req.body;

	// Validación básica
	if (!nombre || !rfc || !tipo) {
		return res.status(400).json({ error: 'Los campos nombre, rfc y tipo son obligatorios' });
	}

	try {
		const result = await pool.query(
			'INSERT INTO usuarios (nombre, rfc, fecha_nac, fecha_asc, telefono, tipo) VALUES ($1, $2, $3, $4, $5, $6) RETURNING *',
			[nombre, rfc, fecha_nac, fecha_asc, telefono, tipo]
		);
		res.status(201).json(result.rows[0]);
	} catch (err) {
		console.error(err);
		res.status(500).json({ error: 'Error al crear el usuario' });
	}
});

// PUT - Actualizar un dulce existente
app.put('/api/dulces/:id', async (req, res) => {
	const { id } = req.params;
	const { nombre, precio_uni, stock, lote, fecha_cad, reorden, tipo } = req.body;

	try {
		// Verificar si el dulce existe
		const checkResult = await pool.query('SELECT * FROM dulces WHERE id_dulce = $1', [id]);
		if (checkResult.rows.length === 0) {
			return res.status(404).json({ error: 'Dulce no encontrado' });
		}

		const result = await pool.query(
			'UPDATE dulces SET nombre = $1, precio_uni = $2, stock = $3, lote = $4, fecha_cad = $5, reorden = $6, tipo = $7 WHERE id_dulce = $8 RETURNING *',
			[nombre, precio_uni, stock, lote, fecha_cad, reorden, tipo, id]
		);
		res.json(result.rows[0]);
	} catch (err) {
		console.error(err);
		res.status(500).json({ error: 'Error al actualizar el dulce' });
	}
});

// DELETE - Eliminar un dulce
app.delete('/api/dulces/:id', async (req, res) => {
	const { id } = req.params;
	try {
		// Verificar si el dulce existe
		const checkResult = await pool.query('SELECT * FROM dulces WHERE id_dulce = $1', [id]);
		if (checkResult.rows.length === 0) {
			return res.status(404).json({ error: 'Dulce no encontrado' });
		}

		await pool.query('DELETE FROM dulces WHERE id_dulce = $1', [id]);
		res.status(200).json({ message: 'Dulce eliminado correctamente' });
	} catch (err) {
		console.error(err);
		res.status(500).json({ error: 'Error al eliminar el dulce' });
	}
});

// Ruta para filtrar dulces por tipo
app.get('/api/dulces/filtro/tipo/:tipo', async (req, res) => {
	const { tipo } = req.params;
	try {
		const result = await pool.query('SELECT * FROM dulces WHERE tipo = $1', [tipo]);
		res.json(result.rows);
	} catch (err) {
		console.error(err);
		res.status(500).json({ error: 'Error al filtrar los dulces por tipo' });
	}
});

// Iniciar el servidor
app.listen(port, () => {
	console.log(`Servidor backend corriendo en http://localhost:${port}`);
});