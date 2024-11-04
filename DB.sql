USE prueba;

CREATE TABLE IF NOT EXISTS CATEGORIAS (
    ID_CATEGORIA INT NOT NULL AUTO_INCREMENT,
    NOMBRE VARCHAR(50) NOT NULL,
    PRIMARY KEY (ID_CATEGORIA)
);

CREATE TABLE IF NOT EXISTS UBICACIONES (
    ID_UBICACION INT NOT NULL AUTO_INCREMENT,
    NOMBRE VARCHAR(50) NOT NULL,
    PRIMARY KEY (ID_UBICACION)
);

CREATE TABLE IF NOT EXISTS PRODUCTOS (
    ID_PRODUCTO INT NOT NULL AUTO_INCREMENT,
    NOMBRE VARCHAR(50) NOT NULL,
    TIPO VARCHAR(2) NOT NULL,
    CANTIDAD FLOAT NOT NULL,
    DIMENSIONES VARCHAR(20) NOT NULL,
    DESCONTINUADO BOOLEAN NOT NULL,
    ID_UBICACION INT NOT NULL,
    ID_CATEGORIA INT NOT NULL,
    PRIMARY KEY (ID_PRODUCTO),
    FOREIGN KEY (ID_CATEGORIA) REFERENCES CATEGORIAS(ID_CATEGORIA),
    FOREIGN KEY (ID_UBICACION) REFERENCES UBICACIONES(ID_UBICACION)
);

CREATE TABLE IF NOT EXISTS EMPLEADOS (
    ID_EMPLEADO INT NOT NULL AUTO_INCREMENT,
    NOMBRE VARCHAR(50) NOT NULL,
    TIPO VARCHAR(15) NOT NULL,
    CONTRASENIA VARCHAR(50) NOT NULL,
    DESPEDIDO BOOLEAN NOT NULL,
    PRIMARY KEY (ID_EMPLEADO)
);

CREATE TABLE IF NOT EXISTS CLIENTES_PROVEEDORES (
    ID_C_P INT NOT NULL AUTO_INCREMENT,
    NOMBRE VARCHAR(50) NOT NULL,
    TIPO VARCHAR(15) NOT NULL,
    PRIMARY KEY (ID_C_P)
);

CREATE TABLE IF NOT EXISTS GESTIONES (
    ID_GESTION INT NOT NULL AUTO_INCREMENT,
    FECHA DATETIME NOT NULL,
    TIPO VARCHAR(8) NOT NULL,
    ID_EMPLEADO INT NOT NULL,
    ID_C_P INT NOT NULL,
    DESCRIPCION VARCHAR(50),
    PRIMARY KEY (ID_GESTION),
    FOREIGN KEY (ID_EMPLEADO) REFERENCES EMPLEADOS(ID_EMPLEADO),
    FOREIGN KEY (ID_C_P) REFERENCES CLIENTES_PROVEEDORES(ID_C_P)
);

CREATE TABLE IF NOT EXISTS TRANSACCIONES (
    ID_GESTION INT NOT NULL,
    ID_PRODUCTO INT NOT NULL,
    CANTIDAD FLOAT NOT NULL,
    FOREIGN KEY (ID_GESTION) REFERENCES GESTIONES(ID_GESTION),
    FOREIGN KEY (ID_PRODUCTO) REFERENCES PRODUCTOS(ID_PRODUCTO)
);

INSERT INTO CLIENTES_PROVEEDORES (NOMBRE, TIPO) 
SELECT 'Empresa', 'Empresa'
WHERE NOT EXISTS (SELECT 1 FROM CLIENTES_PROVEEDORES
WHERE NOMBRE = 'Empresa' AND TIPO = 'Empresa');

INSERT INTO EMPLEADOS (NOMBRE, TIPO, CONTRASENIA, DESPEDIDO) 
SELECT 'Gerente', 'Administrador', '12345678', false
WHERE NOT EXISTS (SELECT 1 FROM EMPLEADOS
WHERE NOMBRE = 'Gerente' AND TIPO = 'Administrador' AND CONTRASENIA = '12345678');

-- DROP TABLE TRANSACCIONES;
-- DROP TABLE GESTIONES;
-- DROP TABLE PRODUCTOS;
-- DROP TABLE CATEGORIAS;
-- DROP TABLE UBICACIONES;
-- DROP TABLE CLIENTES_PROVEEDORES;
-- DROP TABLE EMPLEADOS;

SELECT * FROM UBICACIONES;
SELECT * FROM CATEGORIAS;
SELECT * FROM PRODUCTOS;
SELECT * FROM EMPLEADOS;
SELECT * FROM CLIENTES_PROVEEDORES;
SELECT * FROM GESTIONES;
SELECT * FROM TRANSACCIONES;