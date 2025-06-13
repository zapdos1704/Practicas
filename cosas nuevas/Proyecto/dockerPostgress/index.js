const express = require('express');
const { Pool } = require('pg');
const cors = require('cors');
require('dotenv').config();

const app = express();
const port = process.env.PORT || 3000;

// Middleware
app.use(cors());
app.use(express.json());
app.use(express.static('public'));

// Configuración de la base de datos
const pool = new Pool({
	connectionString: process.env.DATABASE_URL || 'postgresql://admin:password123@localhost:5432/dulces_db'
});

// Función para manejar errores de base de datos
const handleDBError = (error, res) => {
	console.error('Error de base de datos:', error);
	res.status(500).json({ error: 'Error interno del servidor' });
};

// RUTAS DEL CRUD

// GET - Obtener todos los dulces
app.get('/api/dulces', async (req, res) => {
	try {
		const result = await pool.query('SELECT * FROM dulces ORDER BY id_dulce');
		res.json(result.rows);
	} catch (error) {
		handleDBError(error, res);
	}
});

// GET - Obtener un dulce por ID
app.get('/api/dulces/:id', async (req, res) => {
	try {
		const { id } = req.params;
		const result = await pool.query('SELECT * FROM dulces WHERE id_dulce = $1', [id]);

		if (result.rows.length === 0) {
			return res.status(404).json({ error: 'Dulce no encontrado' });
		}

		res.json(result.rows[0]);
	} catch (error) {
		handleDBError(error, res);
	}
});

// POST - Crear un nuevo dulce
app.post('/api/dulces', async (req, res) => {
	try {
		const { nombre, precio_uni, stock, lote, fecha_cad, reorden, tipo } = req.body;

		// Validaciones básicas
		if (!nombre || !precio_uni || stock === undefined) {
			return res.status(400).json({ error: 'Nombre, precio_uni y stock son campos obligatorios' });
		}

		const result = await pool.query(
			`INSERT INTO dulces (nombre, precio_uni, stock, lote, fecha_cad, reorden, tipo) 
       VALUES ($1, $2, $3, $4, $5, $6, $7) 
       RETURNING *`,
			[nombre, precio_uni, stock, lote, fecha_cad, reorden || 0, tipo]
		);

		res.status(201).json(result.rows[0]);
	} catch (error) {
		handleDBError(error, res);
	}
});

// PUT - Actualizar un dulce existente
app.put('/api/dulces/:id', async (req, res) => {
	try {
		const { id } = req.params;
		const { nombre, precio_uni, stock, lote, fecha_cad, reorden, tipo } = req.body;

		// Validaciones básicas
		if (!nombre || !precio_uni || stock === undefined) {
			return res.status(400).json({ error: 'Nombre, precio_uni y stock son campos obligatorios' });
		}

		const result = await pool.query(
			`UPDATE dulces 
       SET nombre = $1, precio_uni = $2, stock = $3, lote = $4, 
           fecha_cad = $5, reorden = $6, tipo = $7 
       WHERE id_dulce = $8 
       RETURNING *`,
			[nombre, precio_uni, stock, lote, fecha_cad, reorden || 0, tipo, id]
		);

		if (result.rows.length === 0) {
			return res.status(404).json({ error: 'Dulce no encontrado' });
		}

		res.json(result.rows[0]);
	} catch (error) {
		handleDBError(error, res);
	}
});

// DELETE - Eliminar un dulce
app.delete('/api/dulces/:id', async (req, res) => {
	try {
		const { id } = req.params;
		const result = await pool.query('DELETE FROM dulces WHERE id_dulce = $1 RETURNING *', [id]);

		if (result.rows.length === 0) {
			return res.status(404).json({ error: 'Dulce no encontrado' });
		}

		res.json({ message: 'Dulce eliminado correctamente', dulce: result.rows[0] });
	} catch (error) {
		handleDBError(error, res);
	}
});

// RUTAS ADICIONALES

// GET - Buscar dulces por nombre
app.get('/api/dulces/buscar/:nombre', async (req, res) => {
	try {
		const { nombre } = req.params;
		const result = await pool.query(
			'SELECT * FROM dulces WHERE nombre ILIKE $1 ORDER BY id_dulce',
			[`%${nombre}%`]
		);
		res.json(result.rows);
	} catch (error) {
		handleDBError(error, res);
	}
});

// GET - Obtener dulces con stock bajo
app.get('/api/dulces/stock-bajo', async (req, res) => {
	try {
		const result = await pool.query(
			'SELECT * FROM dulces WHERE stock <= reorden ORDER BY stock ASC'
		);
		res.json(result.rows);
	} catch (error) {
		handleDBError(error, res);
	}
});

// Ruta principal para servir la interfaz web
app.get('/', (req, res) => {
	res.sendFile(__dirname + '/public/index.html');
});

// Iniciar el servidor
app.listen(port, () => {
	console.log(`Servidor corriendo en http://localhost:${port}`);
});

// Manejar cierre graceful
process.on('SIGINT', async () => {
	console.log('Cerrando conexiones...');
	await pool.end();
	process.exit(0);
});