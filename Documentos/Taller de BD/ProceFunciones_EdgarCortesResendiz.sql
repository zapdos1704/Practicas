------------------------------------
-- EDGAR CORTES RESENDIZ --
------------------------------------

CREATE OR REPLACE FUNCTION modificar_inventario(
    _id_mp NUMERIC, 
    _cantidad_mp NUMERIC, 
    _id_dulce NUMERIC, 
    _cantidad_dulce NUMERIC 
)
RETURNS boolean
AS $$
DECLARE
    existencia_mp INTEGER;
    inventario_dulce NUMERIC;
BEGIN
	-- Disminucion de Inventario Materias Primas
	    -- Validar existencia suficiente de materia prima
	    SELECT existencia INTO existencia_mp
	    FROM materias_primas
	    WHERE id_mp = _id_mp;
		
	    IF existencia_mp IS NULL THEN
	        RAISE EXCEPTION 'No se encontro la Materia Prima con el ID: %', _id_mp;
			RETURN FALSE;
	    ELSIF existencia_mp < _cantidad_mp THEN
	        RAISE EXCEPTION 'No hay sufienciente Materia Prima, Existencia: %', existencia_mp;
			RETURN FALSE;
	    END IF;
	
	    -- Reducir inventario de materia prima
	    UPDATE materias_primas
	    SET existencia = existencia - _cantidad_mp
	    WHERE id_mp = _id_mp;

    -- Aumento Inventario de Dulces
	    SELECT stock INTO inventario_dulce 
	    FROM dulces
	    WHERE id_dulce = _id_dulce;
		
	    IF inventario_dulce IS NULL THEN
	        RAISE EXCEPTION 'No se encontro Dulce con el ID %.', _id_dulce;
			RETURN FALSE;
	    END IF;
		
	    UPDATE dulces
	    SET stock = stock + _cantidad_dulce 
	    WHERE id_dulce = _id_dulce;

	-- Todo Correcto
	RAISE NOTICE 'Aumento correcto';
	RETURN TRUE; 
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE PROCEDURE registrar_crear_elaboracion(
    _id_fabrica NUMERIC, 
	_id_trabajador NUMERIC, 
	_id_mp NUMERIC, 
	_id_dulce NUMERIC,
    _cantidad NUMERIC
)
AS $$
DECLARE
	_elaboracion_id INTEGER;
BEGIN
    -- Verificar si el registro de elaboración existe
    

    IF EXISTS (SELECT 1 FROM fabricas WHERE id_fabrica=_id_fabrica) THEN
		IF EXISTS (SELECT 1 FROM trabajadores WHERE id_trabajador=_id_trabajador) THEN
			IF EXISTS (SELECT 1 FROM materias_primas WHERE id_mp=_id_mp) THEN
				IF EXISTS (SELECT 1 FROM dulces WHERE id_dulce=_id_dulce) THEN
			        SELECT MAX (id_elaboracion)+1 INTO _elaboracion_id FROM elaboracion;
			        INSERT INTO elaboracion (id_elaboracion,id_fabrica, id_trabajador, id_mp, id_dulce, cantidad)
			        VALUES (_id_elaboracion,_id_fabrica, _id_trabajador, _id_mp, _id_dulce, _cantidad);
		    	END IF;
			END IF;
		END IF;
	END IF;
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE PROCEDURE proceso_completo(
	_id_fabrica NUMERIC,
	_id_trabajador NUMERIC,
    _id_mp NUMERIC, 
	_id_dulce NUMERIC,
	_cantidad NUMERIC,
    _cantidad_mp NUMERIC,  
    _cantidad_dulce NUMERIC
)
AS $$
DECLARE
    
BEGIN

	-- Llamar a la función para registrar o crear elaboración
    CALL registrar_crear_elaboracion(_id_fabrica , _id_trabajador , _id_mp , _id_dulce ,_cantidad );

    -- Llamar a la función de modificación de inventario (materia prima y dulces)
	PERFORM modificar_inventarios(_id_mp, _cantidad_mp, _id_dulce, _cantidad_dulce);

END;
$$ LANGUAGE plpgsql;

SELECT * FROM materias_primas;
SELECT * FROM dulces;
SELECT * FROM elaboracion;





CREATE OR REPLACE FUNCTION modificar_inventarios(
    _id_mp NUMERIC, 
    _cantidad_mp NUMERIC, 
    _id_dulce NUMERIC, 
    _cantidad_dulce NUMERIC 
)
RETURNS boolean
AS $$
DECLARE
    existencia_mp INTEGER;
    inventario_dulce NUMERIC;
BEGIN
	-- Disminucion de Inventario Materias Primas
	    -- Validar existencia suficiente de materia prima
	    SELECT existencia INTO existencia_mp
	    FROM materias_primas
	    WHERE id_mp = _id_mp;
		
	    IF existencia_mp IS NULL THEN
	        RAISE EXCEPTION 'No se encontro la Materia Prima con el ID: %', _id_mp;
			RETURN FALSE;
	    ELSIF existencia_mp < _cantidad_mp THEN
	        RAISE EXCEPTION 'No hay sufienciente Materia Prima, Existencia: %', existencia_mp;
			RETURN FALSE;
	    END IF;
	
	    -- Reducir inventario de materia prima
	    UPDATE materias_primas
	    SET existencia = existencia - _cantidad_mp
	    WHERE id_mp = _id_mp;

    -- Aumento Inventario de Dulces
	    SELECT stock INTO inventario_dulce 
	    FROM dulces
	    WHERE id_dulce = _id_dulce;
		
	    IF inventario_dulce IS NULL THEN
	        RAISE EXCEPTION 'No se encontro Dulce con el ID %.', _id_dulce;
			RETURN FALSE;
	    END IF;
		
	    UPDATE dulces
	    SET stock = stock + _cantidad_dulce 
	    WHERE id_dulce = _id_dulce;

	-- Todo Correcto
	RAISE NOTICE 'Aumento correcto';
	RETURN TRUE; 
END;
$$ LANGUAGE plpgsql;














