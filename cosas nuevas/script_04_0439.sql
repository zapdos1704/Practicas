-- Edgar Cortes Resendiz

-- Creacion de roles y usuarios
CREATE ROLE Almacenista;

CREATE USER cargador WITH PASSWORD 'cargador@02';

grant Almacenista to cargador;

grant select on products,orders, order_details to Almacenista;
grant update (units_in_stock,reorder_level) on products to Almacenista;
grant update (shipped_date) on orders to Almacenista;
grant select (employee_id,first_name,last_name,title) on employees to Almacenista;


-- Consultas de prueba
--1.-
select * from products;

--2.-
update products
set units_in_stock = 50, reorder_level = 20
where product_id = 5; 

--3.-
select * from orders where order_id=10248;

--4.-
update orders
set shipped_date = '1992-08-10'
where order_id = 10248; 

--5.-
select * from order_details where order_id=10248;

--6.-
select * from employees;

select employee_id, first_name, last_name, title from employees;


