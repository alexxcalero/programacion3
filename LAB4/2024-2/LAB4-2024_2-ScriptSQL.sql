DROP TABLE IF EXISTS catering;
DROP TABLE IF EXISTS productora;
DROP TABLE IF EXISTS empresa;
CREATE TABLE empresa(
	id_empresa INT AUTO_INCREMENT,
    nombre VARCHAR(100),
    fecha_fundacion DATE,
    activa TINYINT,
    PRIMARY KEY(id_empresa)
)ENGINE=InnoDB;
CREATE TABLE catering(
	id_catering INT,
    capacidad_servicio INT,
    ofrece_decoracion_ambientacion TINYINT,
    ofrece_alimentacion_especial TINYINT,
    PRIMARY KEY(id_catering),
    FOREIGN KEY(id_catering) REFERENCES empresa(id_empresa)
)ENGINE=InnoDB;
CREATE TABLE productora(
	id_productora INT,
    numero_filiales_en_peru INT,
    presupuesto_anual DECIMAL(14,2),
    ofrece_alquiler_equipos TINYINT,
    tipo_especializacion ENUM('AUDIOVISUAL','LOGISTICA','PUBLICIDAD'),
    PRIMARY KEY(id_productora),
    FOREIGN KEY(id_productora) REFERENCES empresa(id_empresa)
)ENGINE=InnoDB;
DROP PROCEDURE IF EXISTS INSERTAR_PRODUCTORA;
DROP PROCEDURE IF EXISTS MODIFICAR_PRODUCTORA;
DROP PROCEDURE IF EXISTS ELIMINAR_PRODUCTORA;
DROP PROCEDURE IF EXISTS LISTAR_PRODUCTORA_TODAS;
DROP PROCEDURE IF EXISTS OBTENER_PRODUCTORA_X_ID;
DELIMITER $
CREATE PROCEDURE INSERTAR_PRODUCTORA(
	OUT _id_productora INT,
    IN _nombre VARCHAR(100),
    IN _fecha_fundacion DATE,
    IN _numero_filiales_en_peru INT,
    IN _presupuesto_anual DECIMAL(14,2),
    IN _ofrece_alquiler_equipos TINYINT,
    IN _tipo_especializacion ENUM('AUDIOVISUAL','LOGISTICA','PUBLICIDAD')
)
BEGIN
	INSERT INTO empresa(nombre,fecha_fundacion,activa) VALUES(_nombre,_fecha_fundacion,1);
    SET _id_productora = @@last_insert_id;
    INSERT INTO productora(id_productora,numero_filiales_en_peru,presupuesto_anual,ofrece_alquiler_equipos,tipo_especializacion) VALUES(_id_productora,_numero_filiales_en_peru,_presupuesto_anual,_ofrece_alquiler_equipos,_tipo_especializacion);
END$
CREATE PROCEDURE MODIFICAR_PRODUCTORA(
	IN _id_productora INT,
    IN _nombre VARCHAR(100),
    IN _fecha_fundacion DATE,
    IN _numero_filiales_en_peru INT,
    IN _presupuesto_anual DECIMAL(14,2),
    IN _ofrece_alquiler_equipos TINYINT,
    IN _tipo_especializacion ENUM('AUDIOVISUAL','LOGISTICA','PUBLICIDAD')
)
BEGIN
	UPDATE empresa SET nombre = _nombre, fecha_fundacion = _fecha_fundacion WHERE id_empresa = _id_productora;
    UPDATE productora SET numero_filiales_en_peru = _numero_filiales_en_peru, presupuesto_anual = _presupuesto_anual, ofrece_alquiler_equipos = _ofrece_alquiler_equipos WHERE id_productora = _id_productora;
END$
CREATE PROCEDURE ELIMINAR_PRODUCTORA(
	IN _id_productora INT
)
BEGIN
	UPDATE empresa SET activa = 0 WHERE id_empresa = _id_productora;
END$
CREATE PROCEDURE LISTAR_PRODUCTORA_TODAS()
BEGIN
	SELECT p.id_productora, e.nombre, e.fecha_fundacion, p.numero_filiales_en_peru, p.presupuesto_anual, p.ofrece_alquiler_equipos, p.tipo_especializacion FROM empresa e INNER JOIN productora p ON e.id_empresa = p.id_productora WHERE e.activa = 1;
END$
CREATE PROCEDURE OBTENER_PRODUCTORA_X_ID(
	IN _id_productora INT
)
BEGIN
	SELECT p.id_productora, e.nombre, e.fecha_fundacion, p.numero_filiales_en_peru, p.presupuesto_anual, p.ofrece_alquiler_equipos, p.tipo_especializacion FROM empresa e INNER JOIN productora p ON e.id_empresa = p.id_productora WHERE p.id_productora = _id_productora;
END$
DELIMITER ;
CALL INSERTAR_PRODUCTORA(@id_productora1,'TONDERO PRODUCCIONES','1992-08-03',2,330010.43,1,'LOGISTICA');
CALL INSERTAR_PRODUCTORA(@id_productora2,'GEOTV','1989-11-17',6,110300.00,4,'AUDIOVISUAL');