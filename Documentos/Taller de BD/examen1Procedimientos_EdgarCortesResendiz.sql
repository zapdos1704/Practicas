ALTER TABLE carrito ADD subtotal INTEGER;



call sp_agregar_producto_carrito('BONAP',4,3,23.7);

create or replace procedure sp_agregar_producto_carrito(
	_customer_id CHARACTER VARYING(5),
	_product_id NUMERIC,
	_quantity NUMERIC,
	_unit_price NUMERIC
)
AS $$
DECLARE
	subtotalActu INTEGER;
	_carrito_id INTEGER;
	price REAL;
	cantidadActu INTEGER;
	descu REAL;
BEGIN

	SELECT subtotal INTO subtotalActu
	FROM carrito
	WHERE customer_id = _customer_id;
	
	SELECT unit_price INTO price
	FROM carrito
	WHERE customer_id = _customer_id;

	SELECT quantity INTO cantidadActu
	FROM carrito
	WHERE customer_id = _customer_id;

	SELECT discount INTO descu
	FROM carrito
	WHERE customer_id = _customer_id;
	
	IF subtotalActu IS NULL THEN
		SELECT MAX (carrito_id)+1 INTO _carrito_id FROM carrito;
		subtotalActu := _quantity*_unit_price;
	ELSE
		subtotalActu := (cantidadActu+_quantity)* price - (cantidadActu+_quantity)* price * descu;
	END IF;

	-- Verificar que la existencia de la orden exista
	IF EXISTS (SELECT 1 FROM carrito where customer_id=_customer_id) THEN
		IF EXISTS (SELECT 1 FROM carrito where product_id=_product_id) THEN
			RAISE NOTICE 'SI FUNCIONA UPDETEAR';
			-- Se actualiza la cantidad del producto
			UPDATE carrito SET quantity = quantity + _quantity WHERE PRODUCT_ID=_product_id;

			-- Se actualiza el subtotal
			UPDATE carrito SET subtotal = subtotalActu WHERE PRODUCT_ID=_product_id;
			
		ELSE 
			RAISE NOTICE 'NO HAY PRODUCTO';
			INSERT INTO carrito(carrito_id,customer_id,product_id,quantity,unit_price,discount,subtotal)
			values (_carrito_id,_customer_id,_product_id,_quantity,_unit_price,0.0,subtotalActu);
		END IF;
	ELSE 
		RAISE NOTICE 'NO HAY CUSTOMER';
		
	 	INSERT INTO carrito(carrito_id,customer_id,product_id,quantity,unit_price,discount,subtotal)
		values (_carrito_id,_customer_id,_product_id,_quantity,_unit_price,0.0,subtotalActu);
	END IF;
END;
$$ LANGUAGE plpgsql;




create or replace procedure sp_incremento_preccio(
	_category_name CHARACTER VARYING(15),
	_incremento NUMERIC
)
AS $$
DECLARE
	_category_id INTEGER;
BEGIN
	SELECT category_id INTO _category_id
	FROM categories
	WHERE category_name = _category_name;
	
	IF EXISTS (SELECT 1 FROM categories where category_name=_category_name) THEN
		UPDATE products SET unit_price = unit_price*(_incremento/100)+unit_price WHERE category_id=_category_id;
	else
		raise notice 'no existe la categoria';
	END IF;
END;
$$ LANGUAGE plpgsql;


select * from customers;
select * from carrito;
select * from products;
call sp_incremento_preccio('Beverages',100);