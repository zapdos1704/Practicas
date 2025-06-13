-- ----------------------------------------------
--  Autor: Edgar Cortes Resendiz
--  Tema: Transacciones, Bloqueos y Niveles de Aislamiento
--  Fecha: 26 de Noviembre del 2024
-- ----------------------------------------------

-- ----------------------------------------------
-- Recopilación de Sintaxis Relevante para PostgreSQL
-- ----------------------------------------------
-- 1. **Niveles de Aislamiento**:
--    Cambiar nivel de aislamiento:
--    SET TRANSACTION ISOLATION LEVEL [READ UNCOMMITTED | READ COMMITTED | REPEATABLE READ | SERIALIZABLE];

-- 2. **Iniciar una Transacción**:
--    BEGIN;   -- Inicia una transacción
--    COMMIT;  -- Confirma los cambios
--    ROLLBACK;-- Revierte los cambios si es necesario

-- 3. **TRY y CATCH en PostgreSQL**:
--    No existe TRY-CATCH explícito. Se usan bloques DO con BEGIN-END y EXCEPTION para manejar errores:
--    DO $$
--    BEGIN
--       -- Código
--    EXCEPTION WHEN OTHERS THEN
--       -- Manejo de errores
--    END $$;

-- 4. **Uso de SERIAL**:
--    PostgreSQL usa SERIAL para autoincrementar columnas en lugar de IDENTITY.

-- 5. **Funciones almacenadas**:
--    CREATE OR REPLACE FUNCTION <nombre>() RETURNS void AS $$
--    BEGIN
--       -- Código
--    END $$ LANGUAGE plpgsql;

-- 6. **CURRENT_TIMESTAMP**:
--    Se utiliza en lugar de GETDATE() para obtener la fecha y hora actual.

-- ----------------------------------------------
-- Creación y uso de la Base de Datos BancoJiquilpan
-- ----------------------------------------------

-- Crear la base de datos
CREATE DATABASE BancoJiquilpan;

-- Cambiar a la base de datos BancoJiquilpan
\c BancoJiquilpan;

-- Crear tabla Cuentas
CREATE TABLE Cuentas (
    CuentaID SERIAL PRIMARY KEY,  -- Uso de SERIAL para ID autoincremental
    Titular VARCHAR(100) NOT NULL, -- VARCHAR en lugar de NVARCHAR
    Saldo NUMERIC(18, 2) NOT NULL DEFAULT 0,
    FechaCreacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Insertar datos en la tabla Cuentas
INSERT INTO Cuentas (Titular, Saldo, FechaCreacion) VALUES
('Juan Pérez', 1000.00, CURRENT_TIMESTAMP),
('María López', 1500.00, CURRENT_TIMESTAMP),
('Carlos Sánchez', 800.00, CURRENT_TIMESTAMP),
('Ana Torres', 2000.00, CURRENT_TIMESTAMP),
('Luis Fernández', 500.00, CURRENT_TIMESTAMP),
('Laura García', 300.00, CURRENT_TIMESTAMP),
('Miguel Ramírez', 2500.00, CURRENT_TIMESTAMP),
('Paula Castillo', 1200.00, CURRENT_TIMESTAMP),
('Sofía Moreno', 1800.00, CURRENT_TIMESTAMP),
('Andrés Gómez', 400.00, CURRENT_TIMESTAMP);

-- Crear tabla Transacciones
CREATE TABLE Transacciones (
    TransaccionID SERIAL PRIMARY KEY, -- SERIAL reemplaza IDENTITY
    CuentaOrigenID INT,
    CuentaDestinoID INT,
    Monto NUMERIC(18, 2) NOT NULL,
    Tipo VARCHAR(50) NOT NULL,
    Fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT FK_CuentaOrigen FOREIGN KEY (CuentaOrigenID) REFERENCES Cuentas(CuentaID),
    CONSTRAINT FK_CuentaDestino FOREIGN KEY (CuentaDestinoID) REFERENCES Cuentas(CuentaID)
);

-- Insertar datos en la tabla Transacciones
INSERT INTO Transacciones (CuentaOrigenID, CuentaDestinoID, Monto, Tipo, Fecha) VALUES
(NULL, 1, 1000.00, 'Depósito', CURRENT_TIMESTAMP),
(NULL, 2, 1500.00, 'Depósito', CURRENT_TIMESTAMP),
(NULL, 3, 800.00, 'Depósito', CURRENT_TIMESTAMP),
(NULL, 4, 2000.00, 'Depósito', CURRENT_TIMESTAMP),
(NULL, 5, 500.00, 'Depósito', CURRENT_TIMESTAMP),
(NULL, 6, 300.00, 'Depósito', CURRENT_TIMESTAMP),
(NULL, 7, 2500.00, 'Depósito', CURRENT_TIMESTAMP),
(NULL, 8, 1200.00, 'Depósito', CURRENT_TIMESTAMP),
(NULL, 9, 1800.00, 'Depósito', CURRENT_TIMESTAMP),
(NULL, 10, 400.00, 'Depósito', CURRENT_TIMESTAMP);

-- Consultar datos
SELECT * FROM Transacciones;
SELECT * FROM Cuentas;

-- Actualizar datos
UPDATE Transacciones SET Tipo='Apertura';

-- Consulta específica
SELECT Titular, Saldo, FechaCreacion FROM Cuentas;

-- Transacciones en PostgreSQL
BEGIN;
   UPDATE Cuentas SET Saldo = Saldo + 1500 WHERE CuentaID = 1;
COMMIT;


-- Cambiar el nivel de aislamiento de una transacción
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;


-- Función almacenada para movimiento entre cuentas
CREATE OR REPLACE FUNCTION sp_Movimiento(
    _CuentaID1 INT,
    _CuentaID2 INT,
    _Monto NUMERIC,
    _Tipo CHAR
)
RETURNS VOID AS $$
BEGIN
	BEGIN
    IF (SELECT Saldo FROM Cuentas WHERE CuentaID = _CuentaID1) >= _Monto THEN
        IF EXISTS (SELECT 1 FROM Cuentas WHERE CuentaID = _CuentaID2) THEN
            BEGIN
				BEGIN
                IF _Tipo = 'D' THEN
                    UPDATE Cuentas SET Saldo = Saldo - _Monto WHERE CuentaID = _CuentaID1;
                    UPDATE Cuentas SET Saldo = Saldo + _Monto WHERE CuentaID = _CuentaID2;
                    INSERT INTO Transacciones (CuentaOrigenID, CuentaDestinoID, Monto, Tipo, Fecha)
                    VALUES (_CuentaID1, _CuentaID2, _Monto, 'D', CURRENT_TIMESTAMP);
                ELSIF _Tipo = 'R' THEN
                    UPDATE Cuentas SET Saldo = Saldo - _Monto WHERE CuentaID = _CuentaID1;
                    INSERT INTO Transacciones (CuentaOrigenID, CuentaDestinoID, Monto, Tipo, Fecha)
                    VALUES (_CuentaID1, NULL, _Monto, 'R', CURRENT_TIMESTAMP);
                END IF;
				COMMIT
            END;
        ELSE
            RAISE NOTICE 'Cuenta destino no existe.';
        END IF;
    ELSE
        RAISE NOTICE 'Saldo insuficiente.';
    END IF;
	COMMIT
END $$ LANGUAGE plpgsql;


-- Llamar al procedimiento almacenado
SELECT * FROM Transacciones;
SELECT * FROM Cuentas;
SELECT sp_Movimiento(1, 2, 200, 'D');


-- Función para eliminar una cuenta
CREATE OR REPLACE FUNCTION sp_EliminarCuenta(_CuentaID INT)
RETURNS VOID AS $$
BEGIN
    IF EXISTS (SELECT 1 FROM Cuentas WHERE CuentaID = _CuentaID) THEN
        DELETE FROM Transacciones WHERE CuentaDestinoID = _CuentaID OR CuentaOrigenID = _CuentaID;
        DELETE FROM Cuentas WHERE CuentaID = _CuentaID;
    ELSE
        RAISE NOTICE 'No existe la cuenta.';
    END IF;
END $$ LANGUAGE plpgsql;


-- Llamar al procedimiento almacenado
SELECT sp_EliminarCuenta(1);
SELECT * FROM Cuentas;
SELECT * FROM Transacciones;


-- Para ver el nivel de aislamiento
SHOW transaction_isolation:

-- Cambiar el nivel de aislamiento
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

BEGIN TRANSACTION ISOLATION LEVEL READ COMMITTED;
	select * from cuentas where cuentaid=4;
	select saldo from cuentas where cuentaId=4;
commit;
