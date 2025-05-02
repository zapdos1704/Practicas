

-- 1.1 Conectarse como Director Comercial
-- Usuario: commercial_director 
-- Contraseña: com_dir2025

-- Verificar que puede insertar clientes
INSERT INTO customers (
    customer_id, company_name, contact_name, contact_title, 
    address, city, region, postal_code, country, phone
) VALUES (
    'COMDR', 'Test Company Director', 'John Director', 'Director',
    'Main St 123', 'New York', 'North', '10001', 'USA', '(212) 555-1234'
);

-- Verificar que puede eliminar registros (permiso especial para director)
DELETE FROM customers WHERE customer_id = 'COMDR';

-- 1.2 Conectarse como Gerente Comercial
-- Usuario: commercial_manager
-- Contraseña: com_mgr2025

-- Verificar que puede insertar y actualizar clientes
INSERT INTO customers (
    customer_id, company_name, contact_name, contact_title, 
    address, city, region, postal_code, country, phone
) VALUES (
    'COMMG', 'Test Company Manager', 'Jane Manager', 'Manager',
    'Main St 456', 'Boston', 'North', '02108', 'USA', '(617) 555-4321'
);

UPDATE customers
SET contact_name = 'Jane Updated Manager'
WHERE customer_id = 'COMMG';

-- Verificar que NO puede eliminar registros
DELETE FROM customers WHERE customer_id = 'COMMG';
-- Esto debería FALLAR porque commercial_manager no tiene permiso DELETE

-- 1.3 Conectarse como Representante de Ventas
-- Usuario: sales_rep1 
-- Contraseña: sales_rep1_2025

-- Verificar que puede insertar pedidos
INSERT INTO orders (
    customer_id, employee_id, order_date, required_date
) VALUES (
    'COMMG', 1, CURRENT_DATE, CURRENT_DATE + INTERVAL '5 days'
) RETURNING order_id;
-- Guardar el order_id devuelto para usarlo después

-- Insertar detalles del pedido
INSERT INTO order_details (
    order_id, product_id, unit_price, quantity, discount
) VALUES
    (@order_id, 1, 18.00, 5, 0);
-- Reemplazar @order_id con el ID real devuelto

-- Verificar que no puede eliminar pedidos
DELETE FROM orders WHERE order_id = @order_id;
-- Esto debería FALLAR porque sales_rep1 no tiene permiso DELETE

-- 2.1 Conectarse como Director de Inventario
-- Usuario: inventory_director
-- Contraseña: inv_dir2025

-- Verificar que puede hacer todo en productos
INSERT INTO products (
    product_name, supplier_id, category_id, 
    quantity_per_unit, unit_price, units_in_stock, units_on_order,
    reorder_level, discontinued
) VALUES (
    'Test Product Director', 1, 1,
    '10 boxes x 20 bags', 18.00, 39, 0,
    10, 0
) RETURNING product_id;

-- Guardar el product_id para usarlo después
-- Ejemplo: Asignar a variable @product_id

-- Actualizar todos los campos de productos
UPDATE products
SET 
    product_name = 'Updated Test Product',
    unit_price = 20.00,
    units_in_stock = 45
WHERE product_id = @product_id;

-- Eliminar el producto (solo directores pueden hacer esto)
DELETE FROM products WHERE product_id = @product_id;

-- 2.2 Conectarse como Supervisor de Inventario
-- Usuario: inventory_supervisor
-- Contraseña: inv_sup2025

-- Insertar un nuevo producto
INSERT INTO products (
    product_name, supplier_id, category_id, 
    quantity_per_unit, unit_price, units_in_stock, units_on_order,
    reorder_level, discontinued
) VALUES (
    'Test Product Supervisor', 1, 1,
    '10 boxes x 20 bags', 18.00, 39, 0,
    10, 0
) RETURNING product_id;

-- Guardar el product_id para usarlo después
-- Ejemplo: Asignar a variable @product_id_2

-- Intentar eliminar un producto
DELETE FROM products WHERE product_id = @product_id_2;
-- Esto debería FALLAR (el supervisor no tiene permiso DELETE)

-- 2.3 Conectarse como Operario de Inventario
-- Usuario: stock_clerk1
-- Contraseña: stock1_2025

-- Verificar que puede actualizar solo campos específicos de inventario
UPDATE products
SET units_in_stock = 50,
    units_on_order = 20,
    reorder_level = 15
WHERE product_id = @product_id_2;

-- Verificar que NO puede actualizar precio u otros campos
UPDATE products
SET unit_price = 25.00,
    discontinued = 1
WHERE product_id = @product_id_2;
-- Esto debería FALLAR para unit_price pero funcionar para campos permitidos

-- 3.1 Conectarse como Director de Personal
-- Usuario: hr_director 
-- Contraseña: hr_dir2025

-- Verificar que puede ver todos los empleados (política RLS para manager_level)
SELECT * FROM employees;
-- Debería mostrar TODOS los empleados sin restricciones

-- Crear un nuevo empleado
INSERT INTO employees (
    last_name, first_name, title, title_of_courtesy,
    birth_date, hire_date, address, city, 
    region, postal_code, country, home_phone, extension,
    notes, reports_to
) VALUES (
    'TestLast', 'TestFirst', 'Test Title', 'Mr.',
    '1980-01-01', CURRENT_DATE, '123 Test St', 'Test City',
    'Test Region', '12345', 'Test Country', '(123) 456-7890', '123',
    'Test employee created for verification', 2
) RETURNING employee_id;

-- Guardar el employee_id para usarlo después
-- Ejemplo: Asignar a variable @employee_id

-- 3.2 Conectarse como Gerente de Personal
-- Usuario: hr_manager 
-- Contraseña: hr_mgr2025

-- Verificar políticas RLS para supervisor_level
SELECT * FROM employees;
-- Debería mostrar empleados según la política territory_employees_for_supervisors

-- Actualizar un empleado (verificar que funciona)
UPDATE employees
SET title = 'Updated Test Title',
    notes = 'Updated by HR Manager'
WHERE employee_id = @employee_id;

-- 3.3 Conectarse como Asistente de Personal
-- Usuario: hr_assistant
-- Contraseña: hr_asst2025

-- Verificar restricciones de columnas para operational_level
SELECT * FROM employees;
-- Debería mostrar solo columnas permitidas para operational_level

-- Intentar ver columnas restringidas
SELECT employee_id, birth_date, address, home_phone FROM employees;
-- Esto debería mostrar solo employee_id (las otras columnas están restringidas)

-- 4.1 Preparar datos de prueba (como director de personal)
-- Usuario: hr_director 
-- Contraseña: hr_dir2025

-- Asignar un territorio con el nombre de un usuario para probar la política RLS
INSERT INTO territories (territory_id, territory_description, region_id)
VALUES ('98765', 'hr_manager', 1)
ON CONFLICT (territory_id) DO UPDATE 
SET territory_description = 'hr_manager';

-- Asignar el empleado de prueba a este territorio
INSERT INTO employee_territories (employee_id, territory_id)
VALUES (@employee_id, '98765')
ON CONFLICT DO NOTHING;

-- 4.2 Probar como supervisor (gerente de RRHH)
-- Usuario: hr_manager
-- Contraseña: hr_mgr2025

-- Verificar que ahora puede ver el empleado asignado a su territorio
SELECT * FROM employees WHERE employee_id = @employee_id;
-- Debería mostrar el empleado ya que está en el territorio 'hr_manager'

