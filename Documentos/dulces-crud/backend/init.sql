-- Crear tipos enumerados para los tipos de dulces
CREATE TYPE tipo_dulce AS ENUM ('con azucar', 'sin azucar');

-- Crear tabla dulces
CREATE TABLE IF NOT EXISTS dulces (
    id_dulce SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio_uni DECIMAL(10, 2) NOT NULL,
    stock INTEGER NOT NULL DEFAULT 0,
    lote VARCHAR(50),
    fecha_cad DATE,
    reorden INTEGER DEFAULT 10,
    tipo tipo_dulce NOT NULL
);

-- Insertar algunos datos de ejemplo
INSERT INTO dulces (nombre, precio_uni, stock, lote, fecha_cad, reorden, tipo) VALUES
('Caramelo de menta', 0.50, 100, 'L001', '2025-12-31', 20, 'con azucar'),
('Chocolate amargo', 1.20, 50, 'L002', '2025-10-15', 15, 'sin azucar'),
('Gominola de frutas', 0.30, 200, 'L003', '2025-11-20', 30, 'con azucar'),
('Pastilla de estevia', 0.25, 150, 'L004', '2026-01-10', 25, 'sin azucar'),
('Piruleta', 0.75, 80, 'L005', '2025-09-05', 15, 'con azucar');