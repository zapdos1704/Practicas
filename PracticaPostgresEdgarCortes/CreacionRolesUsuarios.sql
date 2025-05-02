

-- Rol para el departamento comercial
CREATE ROLE commercial_dept;
GRANT northwind_base TO commercial_dept;

-- Permisos específicos para comercial
GRANT SELECT ON ALL TABLES IN SCHEMA public TO commercial_dept;
GRANT INSERT, UPDATE ON customers, orders, order_details TO commercial_dept;
REVOKE DELETE ON ALL TABLES IN SCHEMA public FROM commercial_dept;

-- Rol para el departamento de inventario
CREATE ROLE inventory_dept;
GRANT northwind_base TO inventory_dept;

-- Permisos específicos para inventario
GRANT SELECT ON ALL TABLES IN SCHEMA public TO inventory_dept;
GRANT INSERT, UPDATE ON products, categories, suppliers TO inventory_dept;
GRANT UPDATE (units_in_stock, units_on_order, reorder_level) ON products TO inventory_dept;

-- Rol para el departamento de personal
CREATE ROLE personnel_dept;
GRANT northwind_base TO personnel_dept;

-- Permisos específicos para personal
GRANT SELECT, INSERT, UPDATE, DELETE ON employees, employee_territories, territories, region TO personnel_dept;
GRANT SELECT ON orders TO personnel_dept;



-- Nivel gerencial (acceso amplio dentro de su departamento)
CREATE ROLE manager_level;

-- Nivel supervisor (acceso restringido)
CREATE ROLE supervisor_level;

-- Nivel operativo (acceso muy limitado)
CREATE ROLE operational_level;



-- Usuarios del departamento comercial
CREATE USER commercial_director WITH PASSWORD 'com_dir2025';
CREATE USER commercial_manager WITH PASSWORD 'com_mgr2025';
CREATE USER sales_rep1 WITH PASSWORD 'sales_rep1_2025';
CREATE USER sales_rep2 WITH PASSWORD 'sales_rep2_2025';

-- Asignación de roles a usuarios comerciales
GRANT commercial_dept TO commercial_director, commercial_manager, sales_rep1, sales_rep2;
GRANT manager_level TO commercial_director;
GRANT supervisor_level TO commercial_manager;
GRANT operational_level TO sales_rep1, sales_rep2;

-- Usuarios del departamento de inventario
CREATE USER inventory_director WITH PASSWORD 'inv_dir2025';
CREATE USER inventory_supervisor WITH PASSWORD 'inv_sup2025';
CREATE USER stock_clerk1 WITH PASSWORD 'stock1_2025';
CREATE USER stock_clerk2 WITH PASSWORD 'stock2_2025';

-- Asignación de roles a usuarios de inventario
GRANT inventory_dept TO inventory_director, inventory_supervisor, stock_clerk1, stock_clerk2;
GRANT manager_level TO inventory_director;
GRANT supervisor_level TO inventory_supervisor;
GRANT operational_level TO stock_clerk1, stock_clerk2;

-- Usuarios del departamento de personal
CREATE USER hr_director WITH PASSWORD 'hr_dir2025';
CREATE USER hr_manager WITH PASSWORD 'hr_mgr2025';
CREATE USER hr_assistant WITH PASSWORD 'hr_asst2025';

-- Asignación de roles a usuarios de personal
GRANT personnel_dept TO hr_director, hr_manager, hr_assistant;
GRANT manager_level TO hr_director;
GRANT supervisor_level TO hr_manager;
GRANT operational_level TO hr_assistant;

-- Usuario administrador del sistema
CREATE USER system_admin WITH PASSWORD 'sys_adm2025';
GRANT northwind_admin TO system_admin;


-- Permisos adicionales para el nivel gerencial
-- Los directores pueden eliminar registros en sus áreas
GRANT DELETE ON customers, orders, order_details TO commercial_director;
GRANT DELETE ON products, categories, suppliers TO inventory_director;

-- Los gerentes pueden ver información sensible
ALTER TABLE employees ENABLE ROW LEVEL SECURITY;

-- RLS para los diferentes niveles
CREATE POLICY all_employees_for_managers ON employees
FOR ALL
TO manager_level
USING (true);

-- Supervisores solo pueden ver empleados en sus territorios
CREATE POLICY territory_employees_for_supervisors ON employees
FOR SELECT
TO supervisor_level
USING (employee_id IN (
    SELECT e.employee_id FROM employees e
    JOIN employee_territories et ON e.employee_id = et.employee_id
    JOIN territories t ON et.territory_id = t.territory_id
    WHERE t.territory_description = CURRENT_USER::text
));

-- Nivel operativo solo puede ver información básica
CREATE POLICY basic_employee_info_for_operational ON employees
FOR SELECT
TO operational_level
USING (true);

-- Restringir columnas sensibles para nivel operativo
REVOKE SELECT ON employees FROM operational_level;
GRANT SELECT (employee_id, last_name, first_name, title, extension, reports_to) 
ON employees TO operational_level;

