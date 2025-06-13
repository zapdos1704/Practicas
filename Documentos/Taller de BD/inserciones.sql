-- inserciones 
INSERT INTO proveedores (id_proveedor, nombre, rfc, direccion, telefono)
VALUES
(1, 'Az�cares del Valle', 'AVR123456789', 'Calle Caramelo 123, Ciudad Dulce', '555-123-4567'),
(2, 'Esencias y Sabores S.A.', 'ESS234567890', 'Av. Vainilla 234, Colonia Sabrosa', '555-234-5678'),
(3, 'Chocolates Finos', 'CHF345678901', 'Calle Cacao 345, Ciudad Chocolate', '555-345-6789'),
(4, 'Colorantes Naturales', 'CNL456789012', 'Calle Arco�ris 456, Colonia Brillante', '555-456-7890'),
(5, 'Packaging Dulce', 'PKD567890123', 'Av. Empaque 567, Ciudad Envuelta', '555-567-8901'),
(6, 'Maquinaria Azucarera', 'MAZ678901234', 'Calle Miel 678, Zona Industrial', '555-678-9012'),
(7, 'Distribuciones Frutales', 'DFR789012345', 'Av. Manzana 789, Colonia Tropical', '555-789-0123');

INSERT INTO materias_primas (id_mp, nombre, tipo, lote, existencia, fecha_cad, precio)
VALUES
(1, 'Az�car Refinada', 'Edulcorante', 'L1234', '1000 kg', '2025-12-31', 20),
(2, 'Cacao en Polvo', 'Saborizante', 'L5678', '500 kg', '2024-11-15', 45),
(3, 'Glucosa L�quida', 'Edulcorante', 'L2345', '800 L', '2025-06-01', 30),
(4, 'Gelatina sin Sabor', 'Texturizante', 'L3456', '600 kg', '2024-09-10', 25),
(5, 'Colorante Rojo', 'Colorante', 'L4567', '200 L', '2026-03-22', 15),
(6, 'Esencia de Vainilla', 'Saborizante', 'L6789', '100 L', '2024-12-05', 50),
(7, 'Sorbitol', 'Edulcorante', 'L7890', '300 kg', '2025-02-14', 35);

INSERT INTO compras (id_proveedor, id_mp, fecha_compra, lote, cantidad, precio, descuento)
VALUES
(1, 1, '2024-01-15', 'L1234', '500 kg', 10000, 5.0),  
(2, 2, '2024-02-01', 'L5678', '200 kg', 9000, 0.0),   
(3, 3, '2024-02-10', 'L2345', '300 L', 7500, 2.5),    
(4, 4, '2024-03-05', 'L3456', '400 kg', 10000, 3.0),  
(5, 5, '2024-03-20', 'L4567', '100 L', 3000, 1.5),    
(6, 6, '2024-04-12', 'L6789', '50 L', 2500, 0.0),    
(7, 7, '2024-04-25', 'L7890', '150 kg', 5250, 4.0),   -- Compra de Sorbitol a Emulsificantes y Conservantes S.A.
(1, 1, '2024-05-10', 'L1235', '700 kg', 14000, 6.0),  -- Otra compra de Az�car Refinada a Az�car Fina S.A.
(2, 2, '2024-05-20', 'L5679', '250 kg', 11250, 1.0),  -- Otra compra de Cacao en Polvo a Cacao Natural S.R.L.
(3, 3, '2024-06-01', 'L2346', '400 L', 10000, 2.0),   -- Otra compra de Glucosa L�quida a Frutas Tropicales S.A.
(4, 4, '2024-06-15', 'L3457', '300 kg', 7500, 3.5),   -- Otra compra de Gelatina sin Sabor a Colorantes y Sabores S.A.
(5, 5, '2024-07-05', 'L4568', '120 L', 3600, 2.0),    -- Otra compra de Colorante Rojo a Gelatina y Ma�z S.R.L.
(6, 6, '2024-07-20', 'L6790', '60 L', 3000, 0.0),     -- Otra compra de Esencia de Vainilla a Esencias y Aromas Ltda.
(7, 7, '2024-08-10', 'L7891', '200 kg', 7000, 5.0),   -- Otra compra de Sorbitol a Emulsificantes y Conservantes S.A.
(1, 1, '2024-09-01', 'L1236', '800 kg', 16000, 7.0),  -- M�s Az�car Refinada a Az�car Fina S.A.
(2, 2, '2024-09-15', 'L5680', '300 kg', 13500, 2.0),  -- M�s Cacao en Polvo a Cacao Natural S.R.L.
(3, 3, '2024-10-01', 'L2347', '450 L', 11250, 2.5),   -- M�s Glucosa L�quida a Frutas Tropicales S.A.
(4, 4, '2024-10-20', 'L3458', '350 kg', 8750, 4.0),   -- M�s Gelatina sin Sabor a Colorantes y Sabores S.A.
(5, 5, '2024-11-05', 'L4569', '150 L', 4500, 3.0),    -- M�s Colorante Rojo a Gelatina y Ma�z S.R.L.
(6, 6, '2024-11-20', 'L6791', '80 L', 4000, 0.0);     -- M�s Esencia de Vainilla a Esencias y Aromas Ltda.

INSERT INTO fabricas (id_fabrica, telefono, nom_responsable, estado, municipio, colonia, domicilio, cp)
VALUES
(1, '5551234567', 'Juan P�rez', 'Ciudad de M�xico', 'Iztapalapa', 'Col. Dulce', 'Calle Caramelo 123', 09876),
(2, '5557654321', 'Mar�a G�mez', 'Jalisco', 'Guadalajara', 'Col. Golosina', 'Avenida Az�car 456', 44100),
(3, '5558765432', 'Carlos Ram�rez', 'Estado de M�xico', 'Toluca', 'Col. Caramelo', 'Boulevard Chocolate 789', 50000),
(4, '5551357913', 'Ana Torres', 'Nuevo Le�n', 'Monterrey', 'Col. Galleta', 'Calle Bomb�n 321', 64000),
(5, '5552468135', 'Laura Mart�nez', 'Puebla', 'Puebla', 'Col. Menta', 'Plaza Dulzura 654', 72000),
(6, '5553692587', 'Luis S�nchez', 'Veracruz', 'Veracruz', 'Col. Nube', 'Calle Goma 987', 91700),
(7, '5551478523', 'Jos� Hern�ndez', 'Yucat�n', 'M�rida', 'Col. Regaliz', 'Parque Confite 852', 97000);

INSERT INTO elaboracion (id_fabrica, id_trabajador, id_mp, id_dulce, cantidad)
VALUES
(1, 1, 1, 1, 200),  -- F�brica 1, Trabajador 1, Materia Prima 1, Dulce 1
(1, 2, 2, 2, 150),  -- F�brica 1, Trabajador 2, Materia Prima 2, Dulce 2
(2, 3, 3, 3, 300),  -- F�brica 2, Trabajador 3, Materia Prima 3, Dulce 3
(2, 1, 1, 4, 250),  -- F�brica 2, Trabajador 1, Materia Prima 1, Dulce 4
(3, 2, 2, 5, 400),  -- F�brica 3, Trabajador 2, Materia Prima 2, Dulce 5
(3, 3, 4, 6, 350),  -- F�brica 3, Trabajador 3, Materia Prima 4, Dulce 6
(4, 1, 5, 7, 500),  -- F�brica 4, Trabajador 1, Materia Prima 5, Dulce 7
(4, 2, 6, 6, 450),  -- F�brica 4, Trabajador 2, Materia Prima 6, Dulce 8
(5, 3, 1, 1, 600),  -- F�brica 5, Trabajador 3, Materia Prima 1, Dulce 1
(5, 1, 2, 2, 550),  -- F�brica 5, Trabajador 1, Materia Prima 2, Dulce 2
(6, 2, 3, 3, 700),  -- F�brica 6, Trabajador 2, Materia Prima 3, Dulce 3
(6, 3, 4, 4, 650),  -- F�brica 6, Trabajador 3, Materia Prima 4, Dulce 4
(7, 1, 5, 5, 800),  -- F�brica 7, Trabajador 1, Materia Prima 5, Dulce 5
(7, 2, 6, 6, 750),  -- F�brica 7, Trabajador 2, Materia Prima 6, Dulce 6
(1, 3, 7, 7, 900),  -- F�brica 1, Trabajador 3, Materia Prima 7, Dulce 7
(2, 1, 1, 4, 850),  -- F�brica 2, Trabajador 1, Materia Prima 1, Dulce 8
(3, 2, 2, 1, 950),  -- F�brica 3, Trabajador 2, Materia Prima 2, Dulce 1
(4, 3, 3, 2, 1000), -- F�brica 4, Trabajador 3, Materia Prima 3, Dulce 2
(5, 1, 4, 3, 1100), -- F�brica 5, Trabajador 1, Materia Prima 4, Dulce 3
(6, 2, 5, 4, 1200); -- F�brica 6, Trabajador 2, Materia Prima 5, Dulce 4

INSERT INTO sucursales (id_sucursal, nom_responsable, telefono, estado, municipio, colonia, domicilio, cp)
VALUES
(1, 'Pedro S�nchez', '5551234567', 'Ciudad de M�xico', 'Coyoac�n', 'Col. Alegre', 'Avenida Azucarera 101', 04360),
(2, 'Mar�a L�pez', '5552345678', 'Jalisco', 'Guadalajara', 'Col. Dulce', 'Calle de la Chocolater�a 202', 44100),
(3, 'Jos� Fern�ndez', '5553456789', 'Nuevo Le�n', 'Monterrey', 'Col. Sabrosa', 'Boulevard de los Caramelos 303', 64000),
(4, 'Laura Torres', '5554567890', 'Puebla', 'Puebla', 'Col. Fresa', 'Calle de los Bombones 404', 72000),
(5, 'Carlos Ram�rez', '5555678901', 'Veracruz', 'Veracruz', 'Col. Miel', 'Calle de la Galleta 505', 91700),
(6, 'Ana Mart�nez', '5556789012', 'Yucat�n', 'M�rida', 'Col. Regaliz', 'Avenida Dulzura 606', 97000),
(7, 'Luis Garc�a', '5557890123', 'Quintana Roo', 'Canc�n', 'Col. Mango', 'Calle de los Chicles 707', 77500);

INSERT INTO trabajadores (id_trabajador, id_sucursal, apellido_paterno, apellido_materno, nombres, fecha_nacimiento, fecha_ingreso, rfc, tipo, telefono)
VALUES
(1, 1, 'S�nchez', 'G�mez', 'Pedro', '1990-05-15', '2022-01-10', 'SAGP900515HDFMPR01', 'Permanente', '5551234567'),
(2, 1, 'L�pez', 'Mart�nez', 'Mar�a', '1985-08-22', '2021-03-15', 'LOMM850822HDFMTR02', 'Permanente', '5552345678'),
(3, 2, 'Fern�ndez', 'Hern�ndez', 'Jos�', '1992-11-30', '2023-02-20', 'FEHJ920113HDFMNR03', 'Temporal', '5553456789'),
(4, 2, 'Ram�rez', 'Torres', 'Ana', '1988-03-12', '2020-06-25', 'RATA880312HDFMRA04', 'Permanente', '5554567890'),
(5, 3, 'Mart�nez', 'S�nchez', 'Carlos', '1995-07-05', '2021-09-01', 'MASC950705HDFMCR05', 'Temporal', '5555678901'),
(6, 4, 'Garc�a', 'Mendoza', 'Laura', '1990-12-18', '2022-11-15', 'GAML901218HDFMDA06', 'Permanente', '5556789012'),
(7, 5, 'Hern�ndez', 'Luna', 'Luis', '1987-04-25', '2020-05-30', 'HELU870425HDFMLU07', 'Temporal', '5557890123');

INSERT INTO repartos (id_fabrica, id_sucursal, id_trabajador, id_dulce, cantidad, fecha_reparto)
VALUES
(1, 1, 1, 1, 100, '2024-10-01'),  -- F�brica 1, Sucursal 1, Trabajador 1, Dulce 1
(1, 2, 2, 2, 150, '2024-10-02'),  -- F�brica 1, Sucursal 2, Trabajador 2, Dulce 2
(2, 1, 3, 3, 200, '2024-10-03'),  -- F�brica 2, Sucursal 1, Trabajador 3, Dulce 3
(2, 2, 1, 4, 250, '2024-10-04'),  -- F�brica 2, Sucursal 2, Trabajador 1, Dulce 4
(3, 3, 2, 5, 300, '2024-10-05'),  -- F�brica 3, Sucursal 3, Trabajador 2, Dulce 5
(3, 1, 3, 6, 350, '2024-10-06'),  -- F�brica 3, Sucursal 1, Trabajador 3, Dulce 6
(4, 4, 1, 7, 400, '2024-10-07'),  -- F�brica 4, Sucursal 4, Trabajador 1, Dulce 7
(4, 5, 2, 4, 450, '2024-10-08'),  -- F�brica 4, Sucursal 5, Trabajador 2, Dulce 8
(5, 6, 3, 1, 500, '2024-10-09'),  -- F�brica 5, Sucursal 6, Trabajador 3, Dulce 1
(5, 7, 1, 2, 550, '2024-10-10'),  -- F�brica 5, Sucursal 7, Trabajador 1, Dulce 2
(1, 3, 2, 3, 600, '2024-10-11'),  -- F�brica 1, Sucursal 3, Trabajador 2, Dulce 3
(2, 4, 3, 4, 650, '2024-10-12'),  -- F�brica 2, Sucursal 4, Trabajador 3, Dulce 4
(3, 5, 1, 5, 700, '2024-10-13'),  -- F�brica 3, Sucursal 5, Trabajador 1, Dulce 5
(4, 6, 2, 6, 750, '2024-10-14'),  -- F�brica 4, Sucursal 6, Trabajador 2, Dulce 6
(5, 7, 3, 7, 800, '2024-10-15'),  -- F�brica 5, Sucursal 7, Trabajador 3, Dulce 7
(1, 2, 1, 2, 850, '2024-10-16'),  -- F�brica 1, Sucursal 2, Trabajador 1, Dulce 8
(2, 3, 2, 1, 900, '2024-10-17'),  -- F�brica 2, Sucursal 3, Trabajador 2, Dulce 1
(3, 4, 3, 2, 950, '2024-10-18'),  -- F�brica 3, Sucursal 4, Trabajador 3, Dulce 2
(4, 5, 1, 3, 1000, '2024-10-19'), -- F�brica 4, Sucursal 5, Trabajador 1, Dulce 3
(5, 6, 2, 4, 1050, '2024-10-20'); -- F�brica 5, Sucursal 6, Trabajador 2, Dulce 4

INSERT INTO usuarios (id_usuario, apellido_paterno, apellido_materno, nombres, email, telefono)
VALUES
(1, 'S�nchez', 'G�mez', 'Pedro', 'pedro.sanchez@example.com', '5551234567'),
(2, 'L�pez', 'Mart�nez', 'Mar�a', 'maria.lopez@example.com', '5552345678'),
(3, 'Fern�ndez', 'Hern�ndez', 'Jos�', 'jose.fernandez@example.com', '5553456789'),
(4, 'Ram�rez', 'Torres', 'Ana', 'ana.ramirez@example.com', '5554567890'),
(5, 'Mart�nez', 'S�nchez', 'Carlos', 'carlos.martinez@example.com', '5555678901'),
(6, 'Garc�a', 'Mendoza', 'Laura', 'laura.garcia@example.com', '5556789012'),
(7, 'Hern�ndez', 'Luna', 'Luis', 'luis.hernandez@example.com', '5557890123');

INSERT INTO pedidos (id_usuario, id_sucursal, id_dulce, cantidad, fecha_pedido)
VALUES
(1, 1, 1, 5, '2024-10-01'),   -- Usuario 1, Sucursal 1, Dulce 1, Cantidad 5
(2, 1, 2, 10, '2024-10-02'),  -- Usuario 2, Sucursal 1, Dulce 2, Cantidad 10
(3, 2, 3, 3, '2024-10-03'),   -- Usuario 3, Sucursal 2, Dulce 3, Cantidad 3
(4, 2, 4, 20, '2024-10-04'),  -- Usuario 4, Sucursal 2, Dulce 4, Cantidad 20
(5, 3, 5, 15, '2024-10-05'),  -- Usuario 5, Sucursal 3, Dulce 5, Cantidad 15
(6, 3, 1, 12, '2024-10-06'),  -- Usuario 6, Sucursal 3, Dulce 1, Cantidad 12
(7, 4, 2, 8, '2024-10-07'),   -- Usuario 7, Sucursal 4, Dulce 2, Cantidad 8
(1, 4, 3, 9, '2024-10-08'),   -- Usuario 1, Sucursal 4, Dulce 3, Cantidad 9
(2, 5, 4, 11, '2024-10-09'),  -- Usuario 2, Sucursal 5, Dulce 4, Cantidad 11
(3, 5, 5, 14, '2024-10-10'),  -- Usuario 3, Sucursal 5, Dulce 5, Cantidad 14
(4, 6, 1, 18, '2024-10-11'),  -- Usuario 4, Sucursal 6, Dulce 1, Cantidad 18
(5, 6, 2, 6, '2024-10-12'),   -- Usuario 5, Sucursal 6, Dulce 2, Cantidad 6
(6, 7, 3, 7, '2024-10-13'),   -- Usuario 6, Sucursal 7, Dulce 3, Cantidad 7
(7, 7, 4, 25, '2024-10-14'),  -- Usuario 7, Sucursal 7, Dulce 4, Cantidad 25
(1, 1, 5, 30, '2024-10-15'),   -- Usuario 1, Sucursal 1, Dulce 5, Cantidad 30
(2, 2, 1, 4, '2024-10-16'),   -- Usuario 2, Sucursal 2, Dulce 1, Cantidad 4
(3, 3, 2, 5, '2024-10-17'),   -- Usuario 3, Sucursal 3, Dulce 2, Cantidad 5
(4, 4, 3, 16, '2024-10-18'),  -- Usuario 4, Sucursal 4, Dulce 3, Cantidad 16
(5, 5, 4, 7, '2024-10-19'),   -- Usuario 5, Sucursal 5, Dulce 4, Cantidad 7
(6, 6, 5, 9, '2024-10-20');   -- Usuario 6, Sucursal 6, Dulce 5, Cantidad 9

INSERT INTO dulces (id_dulce, nombre, precio_uni, stock, lote, fecha_cad, reorden, tipo)
VALUES
(1, 'Ate de Membrillo', 50.00, 100, 'Lote001', '2025-12-31', 20, 'CON AZUCAR'),
(2, 'Cajeta de Cabra', 60.00, 80, 'Lote002', '2025-11-30', 15, 'CON AZUCAR'),
(3, 'Dulce de Calabaza', 30.00, 150, 'Lote003', '2025-10-15', 25, 'CON AZUCAR'),
(4, 'Tamarindo', 20.00, 200, 'Lote004', '2025-09-01', 30, 'SIN AZUCAR'),
(5, 'Nueces Garapi�adas', 70.00, 90, 'Lote005', '2025-12-01', 10, 'CON AZUCAR'),
(6, 'Dulce de Leche', 40.00, 120, 'Lote006', '2025-12-15', 20, 'CON AZUCAR'),
(7, 'Jam�n de Frutas', 45.00, 110, 'Lote007', '2025-11-15', 15, 'SIN AZUCAR');

INSERT INTO ventas (id_usuario, id_sucursal, id_dulce, cantidad, fecha, precio_uni, descuento)
VALUES
(1, 1, 1, 2, '2024-10-01', 50.00, 5),    -- Usuario 1, Sucursal 1, Dulce 1, Cantidad 2
(2, 1, 2, 5, '2024-10-02', 60.00, 10),   -- Usuario 2, Sucursal 1, Dulce 2, Cantidad 5
(3, 2, 3, 1, '2024-10-03', 30.00, 0),    -- Usuario 3, Sucursal 2, Dulce 3, Cantidad 1
(4, 2, 4, 3, '2024-10-04', 20.00, 2),    -- Usuario 4, Sucursal 2, Dulce 4, Cantidad 3
(5, 3, 5, 10, '2024-10-05', 40.00, 5),   -- Usuario 5, Sucursal 3, Dulce 5, Cantidad 10
(6, 3, 1, 4, '2024-10-06', 50.00, 0),    -- Usuario 6, Sucursal 3, Dulce 1, Cantidad 4
(7, 4, 2, 6, '2024-10-07', 60.00, 5),    -- Usuario 7, Sucursal 4, Dulce 2, Cantidad 6
(1, 4, 3, 7, '2024-10-08', 20.00, 0),    -- Usuario 1, Sucursal 4, Dulce 3, Cantidad 7
(2, 5, 4, 8, '2024-10-09', 20.00, 3),    -- Usuario 2, Sucursal 5, Dulce 4, Cantidad 8
(3, 5, 5, 5, '2024-10-10', 40.00, 0),    -- Usuario 3, Sucursal 5, Dulce 5, Cantidad 5
(4, 6, 1, 9, '2024-10-11', 50.00, 2),    -- Usuario 4, Sucursal 6, Dulce 1, Cantidad 9
(5, 6, 2, 1, '2024-10-12', 60.00, 0),    -- Usuario 5, Sucursal 6, Dulce 2, Cantidad 1
(6, 7, 3, 2, '2024-10-13', 20.00, 1),    -- Usuario 6, Sucursal 7, Dulce 3, Cantidad 2
(7, 7, 4, 15, '2024-10-14', 20.00, 0),   -- Usuario 7, Sucursal 7, Dulce 4, Cantidad 15
(1, 1, 5, 8, '2024-10-15', 40.00, 2),    -- Usuario 1, Sucursal 1, Dulce 5, Cantidad 8
(2, 2, 1, 3, '2024-10-16', 50.00, 0),    -- Usuario 2, Sucursal 2, Dulce 1, Cantidad 3
(3, 3, 2, 4, '2024-10-17', 60.00, 3),    -- Usuario 3, Sucursal 3, Dulce 2, Cantidad 4
(4, 4, 3, 12, '2024-10-18', 20.00, 5),   -- Usuario 4, Sucursal 4, Dulce 3, Cantidad 12
(5, 5, 4, 7, '2024-10-19', 20.00, 0),    -- Usuario 5, Sucursal 5, Dulce 4, Cantidad 7
(6, 6, 5, 3, '2024-10-20', 40.00, 0);    -- Usuario 6, Sucursal 6, Dulce 5, Cantidad 3

INSERT INTO registros (id_sucursal, id_dulce, cantidad, fecha_recepcion, precio_uni)
VALUES
(1, 1, 50, '2024-10-01', 45.00),   -- Sucursal 1, Dulce 1, Cantidad 50
(1, 2, 30, '2024-10-02', 55.00),   -- Sucursal 1, Dulce 2, Cantidad 30
(2, 3, 40, '2024-10-03', 25.00),   -- Sucursal 2, Dulce 3, Cantidad 40
(2, 4, 60, '2024-10-04', 20.00),   -- Sucursal 2, Dulce 4, Cantidad 60
(3, 5, 20, '2024-10-05', 60.00),   -- Sucursal 3, Dulce 5, Cantidad 20
(3, 1, 100, '2024-10-06', 45.00),  -- Sucursal 3, Dulce 1, Cantidad 100
(4, 2, 70, '2024-10-07', 55.00),   -- Sucursal 4, Dulce 2, Cantidad 70
(4, 3, 80, '2024-10-08', 25.00),   -- Sucursal 4, Dulce 3, Cantidad 80
(5, 4, 90, '2024-10-09', 20.00),   -- Sucursal 5, Dulce 4, Cantidad 90
(5, 5, 10, '2024-10-10', 60.00),   -- Sucursal 5, Dulce 5, Cantidad 10
(6, 1, 50, '2024-10-11', 45.00),   -- Sucursal 6, Dulce 1, Cantidad 50
(6, 2, 40, '2024-10-12', 55.00),   -- Sucursal 6, Dulce 2, Cantidad 40
(7, 3, 30, '2024-10-13', 25.00),   -- Sucursal 7, Dulce 3, Cantidad 30
(7, 4, 60, '2024-10-14', 20.00),   -- Sucursal 7, Dulce 4, Cantidad 60
(1, 5, 20, '2024-10-15', 60.00),   -- Sucursal 1, Dulce 5, Cantidad 20
(2, 1, 75, '2024-10-16', 45.00),   -- Sucursal 2, Dulce 1, Cantidad 75
(3, 2, 55, '2024-10-17', 55.00),   -- Sucursal 3, Dulce 2, Cantidad 55
(4, 3, 90, '2024-10-18', 25.00),   -- Sucursal 4, Dulce 3, Cantidad 90
(5, 4, 15, '2024-10-19', 20.00),   -- Sucursal 5, Dulce 4, Cantidad 15
(6, 5, 50, '2024-10-20', 60.00);   -- Sucursal 6, Dulce 5, Cantidad 50

