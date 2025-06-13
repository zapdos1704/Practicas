--Edgar Cortes Resendiz--
--03/12/2024--
--UNIDAD 6 FUNCIONES, PROCEDIMIENTOS Y TRIGERS
--HACIENDO UN CARRITO--
DROP FUNCTION afectar_inventario(numeric,numeric)

--1. AFECTAR INVENTARIO
CREATE OR REPLACE FUNCTION AFECTAR_INVENTARIO(_PRODUCT_ID NUMERIC, _QUANTITY NUMERIC)
RETURNS BOOLEAN
AS
$$
--DECLARACIONES
	DECLARE
		_UNITS_IN_STOCK SMALLINT;
		
	--CUERPO 
	BEGIN
		RAISE NOTICE 'BIENVENIDO AL SISTEMA TRANSACCIONAL DEL CARRITO NORTHWIND';
		--ASIGNARLE VALOR A LAS VARIABLES
		_UNITS_IN_STOCK:=NULL;

		--VERIFICAR SI EXISTE EL PRODUCTO
		IF EXISTS(SELECT 1 FROM PRODUCTS WHERE PRODUCT_ID=_PRODUCT_ID)THEN 
			--RAISE NOTICE 'EL PRODUCTO EXISTE';
			SELECT UNITS_IN_STOCK INTO _UNITS_IN_STOCK FROM PRODUCTS WHERE PRODUCT_ID=_PRODUCT_ID;
			RAISE NOTICE 'EXISTENCIA % ' , _UNITS_IN_STOCK;
			--VERIFICAR QUE HAYA SUFICIENCIA EN INVENTARIO
			IF _UNITS_IN_STOCK<_QUANTITY THEN 
				RAISE EXCEPTION 'NO HAY SUFICIENTE EN EXISTENCIA';
			END IF;
			--ACTUALIZAR EL INVENTARIO
				UPDATE PRODUCTS SET UNITS_IN_STOCK = UNITS_IN_STOCK - _QUANTITY WHERE PRODUCT_ID=_PRODUCT_ID;
				RAISE NOTICE 'TRUE';
				RETURN TRUE;
		ELSE
			RAISE NOTICE 'EL PRODUCTO % NO EXISTE' , _PRODUCT_ID;
			RETURN FALSE;
		END IF;
	END;
$$
LANGUAGE PLPGSQL; 

SELECT * FROM PRODUCTS
SELECT * FROM ORDERS
SELECT * FROM CUSTOMERS
SELECT AFECTAR_INVENTARIO(3,1)

DROP FUNCTION crear_orden(character varying,numeric,numeric,numeric)
--2. CREAR LA ORDEN
CREATE OR REPLACE FUNCTION CREAR_ORDEN (_CUSTOMER_ID CHARACTER VARYING(5),_EMPLOYEE_ID NUMERIC,_SHIP_VIA NUMERIC, _FREIGHT NUMERIC)
RETURNS INT
AS
$$
	DECLARE
	_ORDER_ID INT;
	CUSTOMER RECORD:= NULL;
	/*_COMPANY_NAME CHARACTER VARYING(40);
	_ADDRESS;CITY;
	_REGION;
	_POSTAL_CODE;
	_COUNTRY;*/
	BEGIN

		--VERIFICAR QUE EXISTA EL CLIENTE, EMPLEADO, LA SHIP_VIA Y INSERTAR
		IF EXISTS(SELECT 1 FROM CUSTOMERS WHERE CUSTOMER_ID=_CUSTOMER_ID)THEN 
			IF EXISTS(SELECT 1 FROM EMPLOYEES WHERE EMPLOYEE_ID=_EMPLOYEE_ID)THEN 
				IF EXISTS(SELECT 1 FROM SHIPPERS WHERE SHIPPER_ID=_SHIP_VIA)THEN 
				--OBTENER EL NUMERO DE ORDEN
					SELECT MAX (ORDER_ID)+1 INTO _ORDER_ID FROM ORDERS;
					--OBTENER LOS DATOS DE ENVIO DEL CLIENTE
					SELECT COMPANY_NAME,ADDRESS,CITY,REGION,POSTAL_CODE,COUNTRY
					INTO CUSTOMER--_COMPANY_NAME,_ADDRESS,CITY,_REGION,_POSTAL_CODE,_COUNTRY
					FROM CUSTOMERS WHERE CUSTOMER_ID=_CUSTOMER_ID;
					INSERT INTO ORDERS  VALUES
					(_ORDER_ID,_CUSTOMER_ID,_EMPLOYEE_ID,CURRENT_DATE,CURRENT_DATE,CURRENT_DATE,_SHIP_VIA,_FREIGHT,
					CUSTOMER.COMPANY_NAME,CUSTOMER.ADDRESS,CUSTOMER.CITY,CUSTOMER.REGION,CUSTOMER.POSTAL_CODE,CUSTOMER.COUNTRY);
					--RAISE NOTICE 'COMPANY NAME %', CUSTOMER.COMPANY_NAME;
					RETURN _ORDER_ID;
				ELSE
					RAISE NOTICE 'NO EXISTE LA SHIP VIA %', _SHIP_VIA;
				END IF;
			ELSE
				RAISE NOTICE 'NO EXISTE EL EMPLEADO %', _EMPLOYEE_ID;
			END IF;
		ELSE
			RAISE NOTICE 'NO EXISTE EL CLIENTE %', _CUSTOMER_ID;
			RETURN -1;
		END IF;
	END
$$
LANGUAGE PLPGSQL;

_CUSTOMER_ID:='ANATR';
		_EMPLOYEE_ID:=1;
		_SHIP_VIA:=3;
		_FREIGHT:=50.5;
SELECT CREAR_ORDEN ('ANATR',1,3,50.5)


--3. INSERTAR EN EL CARRITO

--A) CREAR TABLA TEMPORAL CARRITO
CREATE TABLE CARRITO(
	CARRITO_ID SERIAL,
	CUSTOMER_ID CHARACTER VARYING (5) NOT NULL,
	PRODUCT_ID SMALLINT NOT NULL,
	QUANTITY SMALLINT NOT NULL,
	UNIT_PRICE REAL NOT NULL,
	DISCOUNT REAL NULL,
	primary key (carrito_id)
);

-- Insertar algunos registros
Insert into carrito(CUSTOMER_ID,PRODUCT_ID,QUANTITY,UNIT_PRICE,DISCOUNT)
values ('TORTU',1,1,18,0.0);

Insert into carrito(CUSTOMER_ID,PRODUCT_ID,QUANTITY,UNIT_PRICE,DISCOUNT)
values ('TORTU',3,1,10,0.0);

Insert into carrito(CUSTOMER_ID,PRODUCT_ID,QUANTITY,UNIT_PRICE,DISCOUNT)
values ('CENTC',5,2,21.35,0.0);
Insert into carrito(CUSTOMER_ID,PRODUCT_ID,QUANTITY,UNIT_PRICE,DISCOUNT)
values ('CENTC',7,2,18,0.0);

-- Crear el procedimiento almacenado
Create or replace procedure sp_Insertar_Detalles(
	_order_id numeric,
	_customer_id character varying(5)
)
AS $$

BEGIN
	-- Buscar los datos del cliente
	if exists(select 1 from customers where customer_id=_customer_id) then
		-- Obtener los productos de la tabla carrito que pertenecen al cliente
		insert into order_details 
		select _order_id, product_id,unit_price,quantity,discount from carrito where customer_id= _customer_id;
		-- Eliminar los productos del cliente
		delete from carrito where customer_id=_customer_id;
	else
		raise exception 'No existe el cliente %',_customer_id; 
	end if;
END;
$$
LANGUAGE plpgsql;

select * from order_details where order_id=11078;
-- Primero genero numero de orden
select CREAR_ORDEN('CENTC',2,6,50.5);
-- Segundo muevo los productos de carrito a order_details
call sp_Insertar_Detalles(11078,'CENTC');


-- 4. Generar-Orden. Llamar las funciones o procedimientos anteriores para crear la orden
create or replace procedure sp_generar_orden(
	_customer_id character varying(5),
	_employee_id numeric,
	_ship_via numeric,
	_freight numeric
)
AS $$
DECLARE
	_order_id int;
	Producto RECORD := NULL;
BEGIN
	-- Generar la orden del cliente
	_order_id := crear_orden(_customer_id,_employee_id,_ship_via,_freight);
	for Producto in select * from carrito where customer_id=_customer_id
	loop
		--raise notice 'Producto: %', Producto.unit_price;
		-- Afectar el inventario
		PERFORM AFECTAR_INVENTARIO(Producto.product_id, Producto.quantity);
	end loop;

	-- Insertar en la tabla  order_details
	call sp_Insertar_Detalles(_order_id,_customer_id);

	-- Verificar si existe algun error
	exception when others then
		raise notice 'Ocurrio un error: %', SQLERRM;
		RAISE;
END;
$$
LANGUAGE plpgsql;

call sp_generar_orden('TORTU',2,6,50.5);
select * from carrito;
select * from orders order by order_id desc;
select * from order_details where order_id= 11082;
select * from order_details;

