--------------------------------------------------------
-- DESDE MENÚ DE pgAdmin
--------------------------------------------------------

/*
------ CREAR RESPALDO DESDE pgAdmin --------

1. Abrir pgAdmin
2. Conectarse al servidor PostgreSQL
3. Expandir "Databases"
4. CLIC DERECHO en "Datos"
5. Seleccionar "Backup..."
6. En la ventana de Backup:
   - Filename: Escribir ruta como "C:\respaldos\Datos_backup.backup"
   - Format: Seleccionar "Custom"
   - Compression: Seleccionar nivel de compresión (ej: 6)
7. Ir a pestaña "Objects" 
   - Verificar que estén marcadas las tablas que quieres respaldar
8. CLIC en "Backup"
9. Esperar a que termine y aparezca "Backup job completed"

-------- ELIMINAR BASE DE DATOS DESDE pgAdmin --------

1. En pgAdmin, CLIC DERECHO en "Datos"
2. Seleccionar "Delete/Drop"
3. Confirmar escribiendo el nombre de la base de datos: Datos
4. CLIC en "OK"
5. La base de datos desaparecerá de la lista

-------- RESTAURAR BASE DE DATOS DESDE pgAdmin --------

1. CLIC DERECHO en "Databases" (no en una base específica)
2. Seleccionar "Create" > "Database..."
3. En "Database name" escribir: Datos
4. CLIC en "Save"
5. Ahora CLIC DERECHO en la nueva base "Datos" 
6. Seleccionar "Restore..."
7. En la ventana de Restore:
   - Filename: Seleccionar el archivo de respaldo creado antes
   - Format: Debe coincidir con el formato del backup
8. CLIC en "Restore"
9. Esperar a que termine: "Restore job completed"
*/
-------- VERIFICAR RESTAURACIÓN --------

SELECT COUNT(*) FROM DemoDatos;