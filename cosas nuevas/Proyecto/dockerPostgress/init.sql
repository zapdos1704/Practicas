-- Crear la tabla dulces
CREATE TABLE IF NOT EXISTS dulces (
    id_dulce SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio_uni DECIMAL(10,2) NOT NULL,
    stock INTEGER NOT NULL DEFAULT 0,
    lote VARCHAR(50),
    fecha_cad DATE,
    reorden INTEGER DEFAULT 0,
    tipo VARCHAR(50)
);

-- Insertar algunos datos de ejemplo
INSERT INTO dulces (nombre, precio_uni, stock, lote, fecha_cad, reorden, tipo) VALUES
('Chocolate Milka', 2.50, 100, 'LOT001', '2025-12-31', 10, 'Chocolate'),
('Gomitas Haribo', 1.80, 75, 'LOT002', '2025-08-15', 15, 'Gomitas'),
('Caramelos Halls', 0.90, 200, 'LOT003', '2025-10-20', 25, 'Caramelos'),
('Chicles Trident', 1.20, 150, 'LOT004', '2025-09-30', 20, 'Chicles'),
('Paletas Chupa Chups', 0.75, 80, 'LOT005', '2025-11-15', 12, 'Paletas');