-- Creación de la base de datos
CREATE DATABASE relacionesHibernate;
-- Creación de la tabla detallesCliente
CREATE TABLE `detalles_cliente` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`web` varchar(128) DEFAULT NULL,
	`telefono` varchar(128) DEFAULT NULL,
	`comentarios` varchar(128) DEFAULT NULL,
	PRIMARY KEY(`id`)
) ENGINE=INNODB

-- Creación de la tabla cliente
CREATE TABLE `cliente` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`nombre` varchar(128) DEFAULT NULL,
	`apellido` varchar(128) DEFAULT NULL,
	`direccion` varchar(128) DEFAULT NULL,
	PRIMARY KEY(`id`)
) ENGINE=INNODB

-- Creación de la clave foránea que relaciona las tablas cliente y detalles_cliente
ALTER TABLE `cliente` ADD CONSTRAINT `FK_DETALLES` FOREIGN KEY (`id`) REFERENCES `detalles_cliente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION

-- Creación de la tabla pedido
CREATE TABLE `pedido` (
	`id` int(3) NOT NULL AUTO_INCREMENT,
	`fecha` date DEFAULT NULL,
	`forma_pago` varchar(15),
	`cliente_id` int(3) DEFAULT NULL,
	PRIMARY KEY (`id`), 
	-- Crea un índice llamado idx_cliente_id sobre la columna cliente_id para acelerar las búsquedas y facilitar la relación foránea.
	KEY idx_cliente_id(`cliente_id`),
	-- Crea una clave foránea llamada fk_cliente_id sobre la columna cliente_id que apunta a la columna id de la tabla cliente
	CONSTRAINT `fk_cliente_id` FOREIGN KEY (`cliente_id`) REFERENCES cliente(`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1
