

-- -----------------------------------------
-- 			Gestionar un carrito de compras
-- -----------------------------------------

--1. Generar la orden
-- Afectar-inventario, producto y la cantidad. Regresa TRUE y FALSE
-- Crear-Orden, Generar una orden: Cliente, Empleado, Ship_Via, Peso (Freight). Regresar el Numero de orden generado
-- Insertar-Detalles. Tomar de la tabla temporal Carrito y producto los datos. No regresa nada
-- Finalizar-Orden. 

-- Afectar-inventario, producto y la cantidad. Regresa TRUE y FALSE

DO
$$
	-- Declaraciones
	declare
		_product_id INT;
		_quantity INT;
		_units_in_stock SMALLINT;
	-- cuerpo
	BEGIN
		-- Asignar valores a la variables
		_product_id := 1;
		_quantity := 2;

		-- Verificar si existe el producto
		if exists( select 1 from products where product_id = _product_id) then 
			-- Obtener la existencia del producto
			select units_in_stock into _units_in_stock from products where product_id = _product_id;
			RAISE NOTICE 'Existencia: % ', _units_in_stock;
			-- Verificar que haya suficiencia en inventario
			if _units_in_stock < _quantity then
				RAISE EXCEPTION 'No hay suficiente en existencia ';
			end if;
			-- Actualizar el inventario
			BEGIN TRANSACTION;
				UPDATE products set units_in_stock = units_in_stock - _quantity
				where product_id=_product_id;
			COMMIT;
			RAISE NOTICE 'TRUE';
		else
			RAISE NOTICE 'El producto % no existe', _product_id;
		end if;
		
	
	END;

$$


-- Crear-Orden, Generar una orden: Cliente, Empleado, Ship_Via, Peso (Freight). Regresar el Numero de orden generado

DO
$$
	-- Declaraciones
	declare
		_customer_id character varying(5);
		_employee_id SMALLINT;
		_ship_via SMALLINT;
		_freight real;
	-- cuerpo
	BEGIN
		-- Asignar valores a la variables
		_customer_id := 'ANATR';
		_employee_id := 1;
		_ship_via := 6;
		_freight := 50.5;
		
		-- Verificar si existe el cliente
		if exists( select 1 from products where product_id = _product_id) then 
			-- Obtener la existencia del producto
			select units_in_stock into _units_in_stock from products where product_id = _product_id;
			RAISE NOTICE 'Existencia: % ', _units_in_stock;
			-- Verificar que haya suficiencia en inventario
			if _units_in_stock < _quantity then
				RAISE EXCEPTION 'No hay suficiente en existencia ';
			end if;
			-- Actualizar el inventario
			BEGIN TRANSACTION;
				UPDATE products set units_in_stock = units_in_stock - _quantity
				where product_id=_product_id;
			COMMIT;
			RAISE NOTICE 'TRUE';
		else
			RAISE NOTICE 'El producto % no existe', _product_id;
		end if;
		
	
	END;
$$