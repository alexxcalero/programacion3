DROP SCHEMA IF EXISTS examen_parcial20242;
CREATE SCHEMA examen_parcial20242;
USE examen_parcial20242;
DROP TABLE IF EXISTS evento;
DROP TABLE IF EXISTS productora;
DROP TABLE IF EXISTS clasificacion;
-- Creación de tablas
CREATE TABLE productora(
	id_productora INT AUTO_INCREMENT,
    nombre VARCHAR(100),
    activa TINYINT,
    PRIMARY KEY(id_productora)
)ENGINE=InnoDB;
CREATE TABLE clasificacion(
	id_clasificacion CHAR,
    descripcion VARCHAR(100),
    activa TINYINT,
    PRIMARY KEY(id_clasificacion)
)ENGINE=InnoDB;
CREATE TABLE evento(
	id_evento INT AUTO_INCREMENT,
    fid_productora INT,
    fid_clasificacion CHAR,
    nombre VARCHAR(100),
    costo_realizacion DECIMAL(14,2),
    tipo_evento ENUM('OBRA_TEATRAL','CONCIERTO'),
    fecha_realizacion DATE,
    descripcion VARCHAR(3000),
    permite_reingreso TINYINT,
    permite_grabacion TINYINT,
    banner_promocional LONGBLOB,
    activo TINYINT,
    PRIMARY KEY(id_evento),
    FOREIGN KEY(fid_productora) REFERENCES productora(id_productora),
    FOREIGN KEY(fid_clasificacion) REFERENCES clasificacion(id_clasificacion)
)ENGINE=InnoDB;
-- Inserción de registros
INSERT INTO productora(nombre,activa) VALUES('TARKUS PRODUCCIONES',1);
INSERT INTO productora(nombre,activa) VALUES('STUDIO 3',1);
INSERT INTO productora(nombre,activa) VALUES('MGK PRODUCCIONES',1);
INSERT INTO productora(nombre,activa) VALUES('ONE ENTERTAINMENT',1);
INSERT INTO productora(nombre,activa) VALUES('DEA PROMOTORA',1);
INSERT INTO productora(nombre,activa) VALUES('MASTERLIVE PERU',1);
INSERT INTO productora(nombre,activa) VALUES('MOVE CONCERTS',1);
INSERT INTO productora(nombre,activa) VALUES('LOS PRODUCTORES',1);
INSERT INTO productora(nombre,activa) VALUES('TIERRA FILMS',1);
INSERT INTO clasificacion(id_clasificacion,descripcion,activa) VALUES('A','PARA ADULTOS',1);
INSERT INTO clasificacion(id_clasificacion,descripcion,activa) VALUES('J','PARA JOVENES',1);
INSERT INTO clasificacion(id_clasificacion,descripcion,activa) VALUES('N','PARA NIÑOS',1);
INSERT INTO clasificacion(id_clasificacion,descripcion,activa) VALUES('T','PARA TODO PUBLICO',1);
-- Creación de Procedimientos Almacenados
DROP PROCEDURE IF EXISTS LISTAR_PRODUCTORAS_TODAS;
DROP PROCEDURE IF EXISTS INSERTAR_EVENTO;
DROP PROCEDURE IF EXISTS OBTENER_EVENTO_X_ID;
DROP PROCEDURE IF EXISTS LISTAR_EVENTOS_X_NOMBRE;
DELIMITER $
CREATE PROCEDURE LISTAR_PRODUCTORAS_TODAS()
BEGIN
	SELECT id_productora, nombre, activa FROM productora WHERE activa = 1;
END$
CREATE PROCEDURE INSERTAR_EVENTO(
	OUT _id_evento INT,
    IN _fid_productora INT,
    IN _fid_clasificacion CHAR,
    IN _nombre VARCHAR(100),
    IN _costo_realizacion DECIMAL(14,2),
    IN _tipo_evento ENUM('OBRA_TEATRAL','CONCIERTO'),
    IN _fecha_realizacion DATE,
    IN _descripcion VARCHAR(3000),
    IN _permite_reingreso TINYINT,
    IN _permite_grabacion TINYINT,
    IN _banner_promocional LONGBLOB
)
BEGIN
	INSERT INTO evento(fid_productora,fid_clasificacion,nombre,costo_realizacion,tipo_evento,fecha_realizacion,descripcion,permite_reingreso,permite_grabacion,banner_promocional,activo) VALUES(_fid_productora,_fid_clasificacion,_nombre,_costo_realizacion,_tipo_evento,_fecha_realizacion,_descripcion,_permite_reingreso,_permite_grabacion,_banner_promocional,1);
    SET _id_evento = @@last_insert_id;
END$
CREATE PROCEDURE LISTAR_EVENTOS_X_NOMBRE(
	IN _nombre VARCHAR(100)
)
BEGIN
	SELECT e.id_evento, p.id_productora, p.nombre as nombre_productora, e.nombre as nombre_evento, e.fecha_realizacion FROM evento e INNER JOIN productora p ON e.fid_productora = p.id_productora INNER JOIN clasificacion c ON c.id_clasificacion = e.fid_clasificacion WHERE e.activo = 1 AND e.nombre LIKE CONCAT('%',_nombre,'%');
END$
CREATE PROCEDURE OBTENER_EVENTO_X_ID(
	IN _id_evento INT
)
BEGIN
	SELECT e.id_evento, p.id_productora, p.nombre as nombre_productora, c.id_clasificacion, e.nombre as nombre_evento, e.costo_realizacion, e.tipo_evento, e.fecha_realizacion, e.descripcion, e.permite_reingreso, e.permite_grabacion, e.banner_promocional, e.activo FROM evento e INNER JOIN productora p ON e.fid_productora = p.id_productora INNER JOIN clasificacion c ON c.id_clasificacion = e.fid_clasificacion WHERE e.activo = 1 AND e.id_evento = _id_evento;
END$