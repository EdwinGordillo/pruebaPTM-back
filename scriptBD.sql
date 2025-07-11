CREATE DATABASE IF NOT EXISTS crud_app;
USE crud_app;

CREATE TABLE producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    cantidad_en_stock INT NOT NULL
);