create database dulceria;

use dulceria;

create table proveedores(
	id_proveedor integer not null primary key,
	nombre varchar(30) not null,
	rfc varchar(20) not null,
	direccion varchar(100) not null,
	telefono varchar (15)
)
go

create table materias_primas(
	id_mp integer not null primary key,
	nombre varchar(30) not null,
	tipo varchar (30),
	lote varchar (30) not null,
	existencia varchar (15),
	fecha_cad date not null,
	precio integer not null
)
go

create table compras(
	id_compra integer identity(1,1) primary key,
	id_proveedor integer not null,
	id_mp integer not null,
	fecha_compra date,
	lote varchar (30) not null,
	cantidad varchar (15) not null,
	precio integer not null,
	descuento numeric
	foreign key (id_proveedor) references proveedores(id_proveedor),
	foreign key (id_mp) references materias_primas(id_mp)
)
go

create table fabricas(
	id_fabrica integer not null primary key,
	telefono varchar (15),
	nom_responsable varchar (30),
	estado varchar (50) not null,
	municipio varchar (50) not null,
	colonia varchar (30) not null,
	domicilio varchar (50) not null,
	cp integer 
)
go

create table elaboracion(
	id_elaboracion integer identity (1,1),
	id_fabrica integer not null,
	id_trabajador integer not null,
	id_mp integer not null,
	id_dulce integer not null,
	cantidad integer not null,
	foreign key (id_fabrica) references fabricas(id_fabrica),
	foreign key (id_mp) references materias_primas(id_mp),
	foreign key (id_trabajador) references trabajadores (id_trabajador),
	foreign key (id_dulce) references dulces (id_dulce)
)
go

create table sucursales(
	id_sucursal integer not null primary key,
	nom_responsable varchar (30),
	telefono varchar (15) not null,
	estado varchar (30) not null,
	municipio varchar (50) not null,
	colonia varchar (30) not null,
	domicilio varchar (80) not null,
	cp integer
)
go

create table trabajadores (
	id_trabajador integer not null primary key,
	id_sucursal integer not null,
	apellido_paterno varchar(30) not null,
	apellido_materno varchar(30) not null,
	nombres varchar (30) not null,
	fecha_nacimiento date not null,
	fecha_ingreso date not null,
	rfc varchar (20) not null,
	tipo varchar (20),
	telefono varchar(15)
	foreign key (id_sucursal) references sucursales (id_sucursal)
)
go

create table repartos(
	id_repartos integer identity (1,1)primary key,
	id_fabrica integer not null,
	id_sucursal integer not null,
	id_trabajador integer not null,
	id_dulce integer not null,
	cantidad integer,
	fecha_reparto date
	foreign key (id_fabrica) references fabricas (id_fabrica),
	foreign key (id_sucursal) references sucursales (id_sucursal),
	foreign key (id_trabajador) references trabajadores (id_trabajador),
	foreign key (id_dulce) references dulces(id_dulce)
)
go

create table usuarios(
	id_usuario integer not null primary key,
	apellido_paterno varchar(30) not null,
	apellido_materno varchar(30) not null,
	nombres varchar (30) not null,
	email varchar (80) not null unique,
	telefono varchar (15) not null
)
go

create table pedidos(
	id_pedido integer identity(1,1) primary key,
	id_usuario integer not null,
	id_sucursal integer not null,
	id_dulce integer not null,
	cantidad integer,
	fecha_pedido date
	foreign key (id_sucursal) references sucursales (id_sucursal),
	foreign key (id_usuario) references usuarios (id_usuario),
	foreign key (id_dulce) references dulces (id_dulce)
)
go 

create table dulces(
	id_dulce integer not null primary key,
	nombre varchar (30) not null,
	precio_uni money not null,
	stock integer ,
	lote varchar (30) not null,
	fecha_cad date not null,
	reorden integer check(reorden>=0),
	tipo varchar (20) check(tipo='CON AZUCAR' or tipo='SIN AZUCAR') 
)
go

create table ventas(
	id_venta integer identity(1,1) primary key,
	id_usuario integer not null,
	id_sucursal integer not null,
	id_dulce integer not null,
	cantidad integer,
	fecha date,
	precio_uni money not null,
	descuento integer 
	foreign key (id_sucursal) references sucursales (id_sucursal),
	foreign key (id_usuario) references usuarios (id_usuario),
	foreign key (id_dulce) references dulces (id_dulce)
)
go


create table registros(
	id_registro integer identity(1,1) primary key,
	id_sucursal integer not null,
	id_dulce integer not null,
	cantidad integer,
	fecha_recepcion date not null,
	precio_uni money not null,
	foreign key (id_sucursal) references sucursales (id_sucursal),
	foreign key (id_dulce) references dulces (id_dulce)
)


