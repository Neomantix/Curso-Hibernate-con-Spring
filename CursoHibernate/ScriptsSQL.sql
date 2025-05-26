# Creación de la base de datos
CREATE DATABASE curso_hibernate

# Creación de tablas
CREATE TABLE clientes (
	Id INT,
	Nombre VARCHAR(100),
	Apellidos VARCHAR(100),
	Direccion VARCHAR(100)
)

# Hacer autoincremental el campo Id, y clave primaria de la tabla
ALTER TABLE clientes 
MODIFY COLUMN Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY;
