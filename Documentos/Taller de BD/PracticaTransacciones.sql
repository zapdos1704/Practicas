-- ----------------------------------------------
--  Autor: Edgar Cortes Resendiz
--  Tema: Transacciones, Bloqueos y Niveles de Aislamiento
--  Fecha: 12 de Noviembre del 2024
-- ----------------------------------------------


-- ----------------------------------------------
--       Base de Datos BancoJIquilpan
-- ----------------------------------------------




-- Crearemos una estructura sencilla de Base de Datos BancoJiquilpan
CREATE DATABASE BancoJiquilpan;

-- Usar la Base de Datos BancoJiquilpan
use BancoJiquilpan;


-- Tabla Cuentas
CREATE TABLE Cuentas (
    CuentaID INT PRIMARY KEY IDENTITY(1000,1000),  -- Identificador �nico para cada cuenta
    Titular NVARCHAR(100) NOT NULL,          -- Nombre del titular de la cuenta
    Saldo DECIMAL(18, 2) NOT NULL DEFAULT 0, -- Saldo actual de la cuenta
    FechaCreacion DATETIME NOT NULL DEFAULT GETDATE() -- Fecha de creaci�n de la cuenta
);

-- Datos para insertar en Cuentas
-- Insertar 10 registros de ejemplo en la tabla Cuentas

INSERT INTO Cuentas (Titular, Saldo, FechaCreacion) VALUES ('Juan P�rez', 1000.00, GETDATE());
INSERT INTO Cuentas (Titular, Saldo, FechaCreacion) VALUES ('Mar�a L�pez', 1500.00, GETDATE());
INSERT INTO Cuentas (Titular, Saldo, FechaCreacion) VALUES ('Carlos S�nchez', 800.00, GETDATE());
INSERT INTO Cuentas (Titular, Saldo, FechaCreacion) VALUES ('Ana Torres', 2000.00, GETDATE());
INSERT INTO Cuentas (Titular, Saldo, FechaCreacion) VALUES ('Luis Fern�ndez', 500.00, GETDATE());
INSERT INTO Cuentas (Titular, Saldo, FechaCreacion) VALUES ('Laura Garc�a', 300.00, GETDATE());
INSERT INTO Cuentas (Titular, Saldo, FechaCreacion) VALUES ('Miguel Ram�rez', 2500.00, GETDATE());
INSERT INTO Cuentas (Titular, Saldo, FechaCreacion) VALUES ('Paula Castillo', 1200.00, GETDATE());
INSERT INTO Cuentas (Titular, Saldo, FechaCreacion) VALUES ('Sof�a Moreno', 1800.00, GETDATE());
INSERT INTO Cuentas (Titular, Saldo, FechaCreacion) VALUES ('Andr�s G�mez', 400.00, GETDATE());



-- Tabla Transacciones
CREATE  TABLE Transacciones (
    TransaccionID INT PRIMARY KEY IDENTITY(5,5),   -- Identificador �nico para cada transacci�n
    CuentaOrigenID INT,                            -- ID de la cuenta de origen (puede ser NULL para dep�sitos)
    CuentaDestinoID INT,                           -- ID de la cuenta de destino (puede ser NULL para retiros)
    Monto DECIMAL(18, 2) NOT NULL,                 -- Monto de la transacci�n
    Tipo NVARCHAR(50) NOT NULL,                    -- Tipo de transacci�n: 'Dep�sito', 'Retiro', 'Transferencia'
    Fecha DATETIME NOT NULL DEFAULT GETDATE(),     -- Fecha de la transacci�n
    CONSTRAINT FK_CuentaOrigen FOREIGN KEY (CuentaOrigenID) REFERENCES Cuentas(CuentaID),
    CONSTRAINT FK_CuentaDestino FOREIGN KEY (CuentaDestinoID) REFERENCES Cuentas(CuentaID)
);

-- Datos de los movimientos de apertura de cuenta
-- Insertar registros de dep�sitos iniciales en la tabla Transacciones

INSERT INTO Transacciones (CuentaOrigenID, CuentaDestinoID, Monto, Tipo, Fecha) 
VALUES (NULL, 1000, 1000.00, 'Dep�sito', GETDATE());

INSERT INTO Transacciones (CuentaOrigenID, CuentaDestinoID, Monto, Tipo, Fecha) 
VALUES (NULL, 2000, 1500.00, 'Dep�sito', GETDATE());

INSERT INTO Transacciones (CuentaOrigenID, CuentaDestinoID, Monto, Tipo, Fecha) 
VALUES (NULL, 3000, 800.00, 'Dep�sito', GETDATE());

INSERT INTO Transacciones (CuentaOrigenID, CuentaDestinoID, Monto, Tipo, Fecha) 
VALUES (NULL, 4000, 2000.00, 'Dep�sito', GETDATE());

INSERT INTO Transacciones (CuentaOrigenID, CuentaDestinoID, Monto, Tipo, Fecha) 
VALUES (NULL, 5000, 500.00, 'Dep�sito', GETDATE());

INSERT INTO Transacciones (CuentaOrigenID, CuentaDestinoID, Monto, Tipo, Fecha) 
VALUES (NULL, 6000, 300.00, 'Dep�sito', GETDATE());

INSERT INTO Transacciones (CuentaOrigenID, CuentaDestinoID, Monto, Tipo, Fecha) 
VALUES (NULL, 7000, 2500.00, 'Dep�sito', GETDATE());

INSERT INTO Transacciones (CuentaOrigenID, CuentaDestinoID, Monto, Tipo, Fecha) 
VALUES (NULL, 8000, 1200.00, 'Dep�sito', GETDATE());

INSERT INTO Transacciones (CuentaOrigenID, CuentaDestinoID, Monto, Tipo, Fecha) 
VALUES (NULL, 9000, 1800.00, 'Dep�sito', GETDATE());

INSERT INTO Transacciones (CuentaOrigenID, CuentaDestinoID, Monto, Tipo, Fecha) 
VALUES (NULL, 10000, 400.00, 'Dep�sito', GETDATE());


-- Consultas de transacciones
select * from Transacciones;
select * from Cuentas;

-- Actualiza todas las cuentas, actualiza el valor del campo Tipo por "apertura"
update Transacciones set Tipo='Apertura'

-- Consulta de Titular, saldo y fecha de apertura de la cuenta
select Titular, saldo,FechaCreacion from cuentas;


/*

Para empezar:

BEGIN TRANSACTION: Inicia una nueva transacci�n.
COMMIT: Confirma todos los cambios realizados en la transacci�n, haci�ndolos permanentes.
ROLLBACK: Revierte todos los cambios realizados en la transacci�n.

SELECT @@TRANCOUNT: Para saber el n�mero de transacciones que se estan ejecutando

Execute sp_lock: para ver los bloqueos

NOTA: Los datos a afectar se cargan a la cache, se modifican en la cache y se escriben en el LOG de
transacciones, en el LOG esperan confirmaci�n y una vez se confirman se escriben a los archivos
de Base de Datos


1. Actualizar la cuenta 1000, sumar 1500 pesos
Paso 1. Actualizar el saldo

*/
BEGIN TRANSACTION
   update Cuentas set Saldo=Saldo+1500 where CuentaID=1000
COMMIT
-- Saber cuantos bloqueos existen
EXECUTE sp_lock 

--Conocer el numero de transacciones

select @@TRANCOUNT


BEGIN TRANSACTION
   select * from Cuentas where CuentaID=1000
   
COMMIT

-- Realizar una consulta de las cuentas
Begin Transaction
	select * from Cuentas
Commit Transaction

-- Saber el numero de transacciones que se estan ejecutando
select @@TRANCOUNT

-- Cambiar el nivel de aislamiento
SET TRANSACTION ISOLATION LEVEL
READ UNCOMMITTED


-- Queremos actualizar el saldo de una cuenta
-- 1. Ver si la cuenta 1 tiene saldo suficiente
-- 2. Verificar si la cuenta 2 esta disponible
-- 3. Iniciar transaccion (Critico)
-- 4. Actualizar saldo de la cuenta 1
-- 5. Actualizar saldo de la cuenta 2
-- 6. Registrar el movimiento

-- Realizar las transacciones anteriores a traves de un procedimiento almacendo
create alter procedure sp_Movimiento(
	--Parametros de entrada
	@_CuentaID1 int,
	@_CuentaID2 int,
	@_Monto decimal(18,2),
	@_Tipo char(1) 
)
as
begin
	--Cuerpo del procedimiento
	-- Iniciar transaccion
	begin try
		begin transaction uno
			--1. Verificar el saldo de la cuenta 1
			if ((select Saldo from Cuentas where CuentaID=@_CuentaID1)>=@_Monto)
				--verificar si la cuenta 2 existe
				if exists(select 1 from Cuentas where CuentaID=@_CuentaID1)
				begin
					--iniciamos la transaccion
					begin try
						begin transaction dos
							if @_Tipo='D'
							begin
								--actualizamos saldo de la cuenta 1
								update Cuentas set Saldo=Saldo-@_Monto where CuentaID=@_CuentaID1
								--actualizamos saldo de la cuenta 2
								update Cuentas set Saldo=Saldo-@_Monto where CuentaID=@_CuentaID2
								--registrar el movimiento de deposito
								insert into Transacciones(CuentaOrigenID,CuentaDestinoID,Monto,Tipo,Fecha)
								values(@_CuentaID1,@_CuentaID2,@_Monto,'D',GETDATE())
							end
							if @_Tipo='R'
							begin
								-- Reducir el dinero de la cuenta que se va a retirar
								update Cuentas set Saldo=Saldo-@_Monto where CuentaID=@_CuentaID1
								--registrar el movimiento de retiro
								insert into Transacciones(CuentaOrigenID,CuentaDestinoID,Monto,Tipo,Fecha)
								values(@_CuentaID1,null,@_Monto,'R',GETDATE())
							end
							waitfor delay '00:00:30'
						commit
					end try
					begin catch
						rollback -- transaccion dos
					end catch
				end
				else 
					print'No existe la cuenta destino'
			else
				print'No hay fondos ...'
		commit 
	end try
	begin catch
		rollback -- transaccion uno
	end catch
end

-- Mandar a llamar el procedimiento almacenado
select * from Transacciones
select * from Cuentas
execute sp_Movimiento 3000,4000,200,'D';



/*
Bloqueo: Un bloqueo es cualquier conexi�n que se establece con un objeto de base de datos
Type: DB---> Base de Datos, KEY--> Registro, PAG-->P�gina, TAB-->Tabla
Mode: S--> Standar,   Sch-S--> Standar Compartido, IS--> Standar de Intenci�n,
       X--> Exclusivo, IX--> Exclusivos de intenci�n

Para probar los bloqueos crearemos otro Usuario, para generar un nuevo usuario en Sql Server:
1. Genere el LOGIN. Conecta al servidor
2. Genere un USER. Conecta a la Base de Datos con los Permisos establecidos
*/

-- Lecturas Sucias. Ocurre cuando se le permite a otra Transacci�n la lectura de una FILA que ha sido
--                  modificada por otra transacci�n sim�ltanea pero todav�a no ha sido confirmada.


/*
Niveles de aislamiento (Son los estadares para Gestores Relacionales)
1. Read Commited (Lecturas Confirmadas) (Predeterminado)
   Uso: Es el nivel predeterminado en SQL Server y generalmente adecuado para operaciones normales.

   Con este nivel de aislamiento, una peraci�n de lectura (SELECT) establecer� bloqueos compartidos (Shared Locks)
   sobre los datos que est� leyendo. Sin embargo, dichos bloqueos compartidos finalizar�n junto con la propia
   operaci�n de lectura, de tal modo que entre dos lecturas cabe la posibilidad de que otra transacci�n realice
   una operaci�n de escritura, como un UPDATE, en cuyo caso, la segunda lectura obtendr� datos distintos a la primera
   lectur (lecturas no repetibles)
   En este nivel, no se permite leer datos sin confirmar. Esto asegura que las lecturas de saldo se hagan
   sobre datos consistentes, aunque no bloquea la fila en caso de que otras transacciones necesiten leerla
   simult�neamente.
  
2. Read Uncommited.
   Uso: No recomendado para operaciones financieras, ya que puede permitir lecturas sucias.
   
   Una operaci�n de lectura (SELECT) no establecer� bloqueos compartidos (Shared Locks), sobre los datos que est� leyendo,
   por lo que no ser� bloqueada por otra transacci�n que tenga establecido un bloqueo exclusivo por motivo de
   una operaci�n de escritura.
   Este nivel ofrece grandes beneficios de rendimiento, pero solo debemos utilizarlo en aquellos casos en que la
   ocurrencia de lecturas sucias, lecturas no repetibles y lecturas fantasmas no sea un problema.

   En este nivel, los usuarios concurrentes pueden ver cambios que a�n no se han confirmado,
   lo que podr�a resultar en lecturas sucias de los saldos.


3. Repeteable Read.
   Uso: �til para garantizar que los saldos de las cuentas no cambien entre las lecturas y escrituras
        de una transacci�n.
   Este nivel bloquea las filas seleccionadas hasta el final de la transacci�n, evitando que otros procesos
   modifiquen los saldos de las cuentas involucradas. Esto ayuda a garantizar que los saldos no cambien
   entre la verificaci�n y la actualizaci�n.

4. Serializable
   Uso: El nivel m�s seguro, adecuado para asegurar transacciones completamente aisladas, evitando lecturas fantasma.

   En este nivel, el rango completo de filas est� bloqueado durante la transacci�n, lo cual evita cualquier
   tipo de interferencia de otros procesos, incluyendo inserciones de nuevas filas (lecturas fantasma).
   Este nivel es ideal para transacciones financieras cr�ticas, ya que asegura la m�xima consistencia de datos.


Para sistemas de transacciones bancarias:

READ COMMITTED es el m�nimo recomendado.
REPEATABLE READ garantiza que los saldos de las cuentas no cambien mientras se realiza la transacci�n.
SERIALIZABLE proporciona el mayor grado de aislamiento y es el m�s seguro para aplicaciones cr�ticas.


-------------------  Microsoft tiene 2 m�s
5. Snapshot
6. Read Commited Snapshot


Para cambiar el nivel de aislamiento
SET TRANSACTION ISOLATION LEVEL
READ UNCOMMITTED

*/

SET TRANSACTION ISOLATION LEVEL
read committed

-- Nivel de aislamiento Read Commited
begin transaction
	select * from Cuentas
	where CuentaID=2000
commit

select @@TRANCOUNT

-- Crear un procedimiento almacenado, transacciones y bloques try-catch
-- para eliminar una cuenta

create procedure sp_EliminarCuenta(
	--Parametros de entrada
	@_CuentaID int 
)
as
begin
	--Cuerpo del procedimiento
	-- Iniciar transaccion
	begin try
		begin transaction uno
			--1. Verificar el saldo de la cuenta 1
			if exists(select 1 from Cuentas where CuentaID=@_CuentaID)
				begin
					delete from Transacciones where CuentaDestinoID=@_CuentaID or CuentaOrigenID=@_CuentaID
					delete from Cuentas where CuentaID=@_CuentaID
				end
				else 
					print'No existe la cuenta...'
		commit 
	end try
	begin catch
		rollback -- transaccion uno
	end catch
end

create alter procedure sp_EliminarCuenta(
	--Parametros de entrada
	@_CuentaID int 
)
as
begin
	--Cuerpo del procedimiento
	-- Iniciar transaccion
		begin transaction uno
			--1. Verificar el saldo de la cuenta 1
			if exists(select 1 from Cuentas where CuentaID=@_CuentaID)
				begin
					delete from Transacciones where CuentaDestinoID=@_CuentaID or CuentaOrigenID=@_CuentaID
					delete from Cuentas where CuentaID=@_CuentaID
				end
			else 
				print'No existe la cuenta...'
	if @@ERROR  <> 0
	begin
		rollback -- transaccion uno
		print 'Ocurrio un error'
	end 
	else
		commit
end

select * from Cuentas
select * from Transacciones
execute sp_EliminarCuenta 2000;



-- --------------------------------------------------
-- Niveles de aislamiento
-- --------------------------------------------------

-- Establecer Nivel de aislamiento
SET TRANSACTION ISOLATION LEVEL
read Uncommitted

-- Nivel de aislamiento Read Commited
begin transaction
	--select * from Cuentas
	--where CuentaID=2000
	update Cuentas set saldo =1 where CuentaID=3000
commit

SET TRANSACTION ISOLATION LEVEL
Serializable

begin transaction
	select * from Cuentas
	where CuentaID=3000
commit

begin transaction
	update Cuentas set saldo =1000 where CuentaID=4000
commit

select @@TRANCOUNT