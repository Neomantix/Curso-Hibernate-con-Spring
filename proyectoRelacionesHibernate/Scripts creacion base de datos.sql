# Creación de la base de datos
CREATE DATABASE relacionesHibernate;

# Creación de la tabla detallesCliente
CREATE TABLE `detalles_cliente` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`web` varchar(128) DEFAULT NULL,
	`telefono` varchar(128) DEFAULT NULL,
	`comentarios` varchar(128) DEFAULT NULL,
	PRIMARY KEY(`id`)
) ENGINE=INNODB

# Creación de la tabla cliente
CREATE TABLE `cliente` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`nombre` varchar(128) DEFAULT NULL,
	`apellido` varchar(128) DEFAULT NULL,
	`direccion` varchar(128) DEFAULT NULL,
	PRIMARY KEY(`id`)
) ENGINE=INNODB

# Creación de la clave foránea en la tabla cliente
ALTER TABLE `cliente` ADD CONSTRAINT `FK_DETALLES` FOREIGN KEY (`id`) REFERENCES `detalles_cliente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
