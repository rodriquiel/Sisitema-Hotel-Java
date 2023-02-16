CREATE DATABASE hotellatam;

USE hotellatam;

CREATE table huespedes (
	id int auto_increment not null,
    nombre varchar(40) not null,
    apellido varchar(40) not null,
    fecha_nac date,
    nacionalidad varchar(50) not null,
    telefono varchar(20),
    id_reserva int,
    primary key(id)
    );
    
create table reservas (
	id int auto_increment not null,
    fecha_entrada date null,
    fecha_salida date null,
    valor double null,
    forma_pago varchar(45) null,
    primary key(id)
);

create table usuarios (
	id int auto_increment not null,
    usuario varchar(50) null,
    pass varchar(50) null,
    nombre varchar(60) null,
    primary key(id)
);    

select * from reservas;

select * from huespedes;


UPDATE RESERVAS SET 
	 valor = "2000", 
     forma_pago = "DEBITO" WHERE id = 1;