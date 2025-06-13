CREATE TABLE DemoDatos (
    Id SERIAL PRIMARY KEY,
    Nombre VARCHAR(100),
    Edad INT,
    Ciudad VARCHAR(100),
    Correo VARCHAR(150),
    RFC VARCHAR(13),
    FechaRegistro TIMESTAMP,
    Descripcion TEXT
);

---------------------------------------------------------------------------------
-- INCERSION DE 1,000,000 DE DATOS
---------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION insertar_registros_demo_datos(total INT DEFAULT 1000000) 
RETURNS VOID AS $$ 
DECLARE 
    i INT := 0;
    nuevo_nombre VARCHAR(20);
    nueva_edad INT;
    nueva_ciudad VARCHAR(20);
    nuevo_correo VARCHAR(150);
    nuevo_rfc VARCHAR(13);
    nueva_fecha TIMESTAMP;
    nueva_descripcion TEXT;
BEGIN 
    WHILE i < total LOOP 
        -- Generar datos aleatorios
        nuevo_nombre := substring(md5(random()::text) from 1 for (random() * 15 + 5)::int);
        nueva_edad := (random() * 42 + 18)::int;
        nueva_ciudad := substring(md5(random()::text) from 1 for (random() * 10 + 5)::int);
        nuevo_correo := substring(md5(random()::text) from 1 for 10) || '@jiquilpan.tecnm.mx';
        nuevo_rfc := substring(md5(random()::text) from 1 for 13);
        nueva_fecha := NOW() - (random() * 3650 * INTERVAL '1 day');
        
        -- Generar descripción aleatoria para algunos registros
        IF random() < 0.3 THEN
            nueva_descripcion := CASE (random() * 3)::INT
                WHEN 0 THEN 'Estudiante de ingeniería en sistemas computacionales del Tecnológico Nacional de México'
                WHEN 1 THEN 'Profesionista en el área de tecnologías de la información y comunicaciones'
                ELSE 'Especialista en desarrollo de software y bases de datos relacionales'
            END;
        ELSE
            nueva_descripcion := NULL;
        END IF;

        -- Insertar registro
        INSERT INTO DemoDatos (Nombre, Edad, Ciudad, Correo, RFC, FechaRegistro, Descripcion) 
        VALUES (nuevo_nombre, nueva_edad, nueva_ciudad, nuevo_correo, nuevo_rfc, nueva_fecha, nueva_descripcion);
        
        i := i + 1;
    END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT insertar_registros_demo_datos();

-------------------------------------------------------------
-- CREACIÓN DE 5 TIPOS DIFERENTES DE ÍNDICES
-------------------------------------------------------------

-- 1. ÍNDICE SIMPLE (B-Tree) - Por defecto en PostgreSQL
-- Ideal para búsquedas de igualdad y rangos
CREATE INDEX IDX_DemoDatos_Edad ON DemoDatos(Edad);

-- Verificar uso del índice
EXPLAIN (ANALYZE, BUFFERS) 
SELECT * FROM DemoDatos WHERE Edad = 25;

-- 2. ÍNDICE COMPUESTO (Multi-columna)
-- Útil para consultas que filtran por múltiples columnas
CREATE INDEX IDX_DemoDatos_Ciudad_Edad ON DemoDatos(Ciudad, Edad);

-- Verificar uso del índice compuesto
EXPLAIN (ANALYZE, BUFFERS) 
SELECT * FROM DemoDatos WHERE Ciudad = 'abc123' AND Edad BETWEEN 20 AND 30;

-- 3. ÍNDICE PARCIAL (Condicional)
-- Solo indexa filas que cumplen una condición específica
CREATE INDEX IDX_DemoDatos_Edad_Jovenes ON DemoDatos(Edad) 
WHERE Edad < 30;

-- Verificar uso del índice parcial
EXPLAIN (ANALYZE, BUFFERS) 
SELECT * FROM DemoDatos WHERE Edad < 25;

-- 4. ÍNDICE HASH
-- Eficiente para búsquedas de igualdad exacta (no para rangos)
CREATE INDEX IDX_DemoDatos_RFC_Hash ON DemoDatos USING HASH(RFC);

-- Verificar uso del índice hash
EXPLAIN (ANALYZE, BUFFERS) 
SELECT * FROM DemoDatos WHERE RFC = 'abc123def4567';

-- 5. ÍNDICE GIN (Generalized Inverted Index)
-- Útil para búsquedas de texto y tipos de datos complejos
-- Primero agregar una columna de texto para demostrar
ALTER TABLE DemoDatos ADD COLUMN Descripcion TEXT;

-- Actualizar algunos registros con texto para prueba
UPDATE DemoDatos 
SET Descripcion = 'Estudiante de ingeniería en sistemas computacionales del Tecnológico Nacional de México'
WHERE Id % 1000 = 0;

UPDATE DemoDatos 
SET Descripcion = 'Profesionista en el área de tecnologías de la información y comunicaciones'
WHERE Id % 1500 = 0;

-- Crear índice GIN para búsquedas de texto completo
CREATE INDEX IDX_DemoDatos_Descripcion_GIN ON DemoDatos 
USING GIN(to_tsvector('spanish', Descripcion));

-- Verificar uso del índice GIN
EXPLAIN (ANALYZE, BUFFERS) 
SELECT * FROM DemoDatos 
WHERE to_tsvector('spanish', Descripcion) @@ to_tsquery('spanish', 'tecnología');

--------------------------------------------------------
-- MANTENIMIENTO DE ÍNDICES EN POSTGRESQL
--------------------------------------------------------

-- 1. ACTUALIZAR ESTADÍSTICAS (Importante para el optimizador)
-- Se debe ejecutar después de cambios significativos en los datos
ANALYZE DemoDatos;

-- Actualizar estadísticas de toda la base de datos
ANALYZE;

-- 2. REINDEXAR ÍNDICES (Cuando hay fragmentación o corrupción)
-- Reindexar un índice específico
REINDEX INDEX IDX_DemoDatos_Edad;

-- Reindexar todos los índices de una tabla
REINDEX TABLE DemoDatos;

-- Reindexar toda la base de datos (usar con precaución en producción)
REINDEX DATABASE db_indices;

-- 3. VACUUM (Limpieza de espacio no utilizado)
-- VACUUM básico
VACUUM DemoDatos;

-- VACUUM FULL (reorganiza completamente la tabla - bloquea la tabla)
-- VACUUM FULL DemoDatos;

-- VACUUM ANALYZE (combina limpieza y actualización de estadísticas)
VACUUM ANALYZE DemoDatos;

-- 4. MONITOREO DE RENDIMIENTO DE ÍNDICES
-- Verificar si los índices se están usando
SELECT 
    pg_stat_user_indexes.indexrelname as "Nombre del Índice",
    idx_scan as "Veces Usado",
    idx_tup_read as "Tuplas Leídas",
    idx_tup_fetch as "Tuplas Obtenidas",
    CASE 
        WHEN idx_scan = 0 THEN 'NUNCA USADO'
        WHEN idx_scan < 5 THEN 'POCO USADO' 
        WHEN idx_scan < 10 THEN 'USO NORMAL'
        ELSE 'MUY USADO'
    END as "Estado de Uso"
FROM pg_stat_user_indexes 
WHERE pg_stat_user_indexes.relname = 'demodatos'
ORDER BY idx_scan DESC;

-- 5. IDENTIFICAR ÍNDICES NO UTILIZADOS (candidatos para eliminación)
SELECT 
    schemaname,
    tablename,
    indexname,
    idx_scan,
    pg_size_pretty(pg_relation_size(indexname::regclass)) as tamaño
FROM pg_stat_user_indexes 
WHERE idx_scan = 0 
AND tablename = 'demodatos'
AND indexname NOT LIKE '%_pkey';

-- 6. AUTO VACUUM (PostgreSQL lo hace automáticamente)
-- Ver configuración actual de autovacuum
SELECT name, setting, unit, context 
FROM pg_settings 
WHERE name LIKE '%autovacuum%' 
AND name IN ('autovacuum', 'autovacuum_vacuum_threshold', 'autovacuum_analyze_threshold');

-- Ver última ejecución de autovacuum en la tabla
SELECT 
    schemaname,
    tablename,
    last_vacuum,
    last_autovacuum,
    last_analyze,
    last_autoanalyze,
    vacuum_count,
    autovacuum_count,
    analyze_count,
    autoanalyze_count
FROM pg_stat_user_tables 
WHERE tablename = 'demodatos';

