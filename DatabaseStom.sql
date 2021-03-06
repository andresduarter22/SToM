DROP DATABASE STOM;
CREATE DATABASE STOM;

USE STOM;

CREATE TABLE cliente(
    id_cliente INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(500),
    correo VARCHAR(500) UNIQUE NOT NULL,
    password VARCHAR(70),
    credito double(8,2),
    PRIMARY KEY(id_cliente)
);
CREATE TABLE administrador(
    nombre VARCHAR(500),
    password VARCHAR(500),
    id_administrador INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(id_administrador)
);
CREATE TABLE distribuidor(
    id_distribuidor INT NOT NULL AUTO_INCREMENT,
    correo VARCHAR(50),
    telefono VARCHAR(10),
    direccion VARCHAR(10000),
    nombre VARCHAR(200),
    PRIMARY KEY (id_distribuidor)
);

drop table juego;
CREATE TABLE juego(
    id_juego INT NOT NULL AUTO_INCREMENT,
    id_distribuidor INT,
    nombre VARCHAR(100),
    estado VARCHAR(10),
    descripcion text,
    linkimagen text,
    categoria VARCHAR(100),
    costo DOUBLE (8,2),
    version VARCHAR(10),
    PRIMARY KEY(id_juego),
    FOREIGN KEY (id_distribuidor) REFERENCES distribuidor(id_distribuidor)
);
CREATE TABLE resena(
    id_resena INT NOT NULL AUTO_INCREMENT,
    id_juego INT,
    id_cliente INT,
    titulo VARCHAR(200),
    parrafo text,
    calificacion INT,
    PRIMARY KEY(id_resena),
    FOREIGN KEY (id_juego) REFERENCES juego(id_juego),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

CREATE TABLE compra(
    id_compra INT NOT NULL AUTO_INCREMENT,
    id_juego INT,
    id_cliente INT,
    precio double(8,2) NOT NULL,
    PRIMARY KEY(id_compra),
    FOREIGN KEY (id_juego) REFERENCES juego(id_juego),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);
CREATE TABLE log_cliente(
  id_log INT NOT NULL AUTO_INCREMENT,
  id_cliente VARCHAR(500),
  nombre VARCHAR(500),
  descripcion VARCHAR(50),
  PRIMARY KEY (id_log)
);

drop trigger if exists insertar_Cliente;
Delimiter //
Create trigger insertar_Cliente after insert on cliente
for each row
Begin
  insert into log_cliente values(null, new.id_cliente, new.nombre, "Cliente creado");
end //
Delimiter ;

drop trigger if exists editar_Cliente;
Delimiter //
Create trigger editar_Cliente after update on cliente
for each row
Begin
  insert into log_cliente values(null, new.id_cliente, new.nombre, "Cliente editado");
end //
Delimiter ;

drop trigger if exists borrar_Cliente;
Delimiter //
Create trigger borrar_Cliente after delete on cliente
for each row
Begin
  insert into log_cliente values(null, old.id_cliente, old.nombre, "Cliente borrado");
end //
Delimiter ;


drop trigger if exists Validar_Mail;
Delimiter //
Create trigger ValidarMail before insert on cliente
for each row
Begin
if (new.correo not like '%@%_._%') then
  set new.correo=null;
end if;
end //
Delimiter ;

drop trigger if exists Validar_Mail_Editar;
Delimiter //
Create trigger Validar_Mail_Editar before update on cliente
for each row
Begin
if (new.correo not like '%_@%_._%') then
  set new.correo=null;
end if;
end //
Delimiter ;



drop trigger if exists Un_Juego;
delimiter //
Create trigger Un_Juego before insert on compra
for each row
BEGIN
if ((select count(*) from compra where id_juego = new.id_juego and id_cliente = new.id_cliente) > 0) then
  set new.precio=null;
end if;
end //
Delimiter ;



drop trigger if exists Credito_Disponible;
delimiter //
create trigger Credito_Disponible before insert on compra
for each row
BEGIN
set @credito_disponible = (select credito from cliente where id_cliente = new.id_cliente);
if ((@credito_disponible-new.precio) < 0) then
  set new.precio=null;
end if;
end //
Delimiter ;

drop trigger if exists Actualizar_Saldo_Compra;
delimiter //
create trigger Actualizar_Saldo_Compra after insert on compra
for each row
BEGIN
set @credito_disponible = (select credito from cliente where id_cliente = new.id_cliente);
set @credito = @credito_disponible - new.precio;
update cliente set credito = @credito where id_cliente = new.id_cliente;
end //
Delimiter ;

drop trigger if exists Actualizar_Saldo_Devolucion;
delimiter //
create trigger Actualizar_Saldo_Devolucion after delete on compra
for each row
BEGIN
set @credito_disponible = (select credito from cliente where id_cliente = old.id_cliente);
set @credito = @credito_disponible + old.precio*0.75;
update cliente set credito = @credito where id_cliente = old.id_cliente;
end //
Delimiter ;


