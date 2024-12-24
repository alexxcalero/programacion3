-- Eliminacion de las tablas
DROP TABLE IF EXISTS sala_especializada;
DROP TABLE IF EXISTS consultorio;
DROP TABLE IF EXISTS ambiente_clinico;
-- Creacion de las tablas
CREATE TABLE ambiente_clinico(
	id_ambiente_clinico INT AUTO_INCREMENT,
    espacio_en_m2 DECIMAL(10,2),
    torre CHAR,
    piso INT,
    activo TINYINT,
    PRIMARY KEY(id_ambiente_clinico)
)ENGINE=InnoDB;
CREATE TABLE consultorio(
	id_consultorio INT,
    numero VARCHAR(3),
    PRIMARY KEY(id_consultorio),
    FOREIGN KEY(id_consultorio) REFERENCES ambiente_clinico(id_ambiente_clinico)
)ENGINE=InnoDB;
CREATE TABLE sala_especializada(
	id_sala_especializada INT,
    nombre VARCHAR(100),
    tipo_sala ENUM('UCI','CIRUGIA','EMERGENCIA'),
    posee_equipamiento_imagenologia TINYINT,
    PRIMARY KEY(id_sala_especializada),
    FOREIGN KEY(id_sala_especializada) REFERENCES ambiente_clinico(id_ambiente_clinico)
)ENGINE=InnoDB;
-- Eliminacion de Procedimientos Almacenados
DROP PROCEDURE IF EXISTS INSERTAR_SALA_ESPECIALIZADA;
DROP PROCEDURE IF EXISTS OBTENER_SALA_ESPECIALIZADA_X_ID;
DROP PROCEDURE IF EXISTS MODIFICAR_SALA_ESPECIALIZADA;
DROP PROCEDURE IF EXISTS ELIMINAR_SALA_ESPECIALIZADA;
DROP PROCEDURE IF EXISTS LISTAR_SALAS_ESPECIALIZADAS_TODAS;
-- Creacion de Procedimientos Almacenados
DELIMITER $
CREATE PROCEDURE INSERTAR_SALA_ESPECIALIZADA(
	OUT _id_sala_especializada INT,
    IN _espacio_en_m2 DECIMAL(10,2),
    IN _torre CHAR,
    IN _piso INT,
    IN _nombre VARCHAR(100),
    IN _tipo_sala ENUM('UCI','CIRUGIA','EMERGENCIA'),
    IN _posee_equipamiento_imagenologia TINYINT
)
BEGIN
	INSERT INTO ambiente_clinico(espacio_en_m2,torre,piso,activo) VALUES(_espacio_en_m2,_torre,_piso,1);
    SET _id_sala_especializada = @@last_insert_id;
    INSERT INTO sala_especializada(id_sala_especializada,nombre,tipo_sala,posee_equipamiento_imagenologia) VALUES(_id_sala_especializada,_nombre,_tipo_sala,_posee_equipamiento_imagenologia);
END$
CREATE PROCEDURE MODIFICAR_SALA_ESPECIALIZADA(
	IN _id_sala_especializada INT,
    IN _espacio_en_m2 DECIMAL(10,2),
    IN _torre CHAR,
    IN _piso INT,
    IN _nombre VARCHAR(100),
    IN _tipo_sala ENUM('UCI','CIRUGIA','EMERGENCIA'),
    IN _posee_equipamiento_imagenologia TINYINT
)
BEGIN
	UPDATE ambiente_clinico SET espacio_en_m2 = _espacio_en_m2, torre = _torre, piso = _piso WHERE id_ambiente_clinico = _id_sala_especializada;
	UPDATE sala_especializada SET nombre = _nombre, tipo_sala = _tipo_sala, posee_equipamiento_imagenologia = _posee_equipamiento_imagenologia WHERE id_sala_especializada = _id_sala_especializada;
END$
CREATE PROCEDURE ELIMINAR_SALA_ESPECIALIZADA(
	IN _id_sala_especializada INT
)
BEGIN
	UPDATE ambiente_clinico SET activo = 0 WHERE id_ambiente_clinico = _id_sala_especializada;
END$
CREATE PROCEDURE LISTAR_SALAS_ESPECIALIZADAS_TODAS()
BEGIN
	SELECT se.id_sala_especializada, ac.espacio_en_m2, ac.torre, ac.piso, se.nombre, se.tipo_sala, se.posee_equipamiento_imagenologia FROM ambiente_clinico ac INNER JOIN sala_especializada se ON ac.id_ambiente_clinico = se.id_sala_especializada WHERE ac.activo = 1; 
END$
CREATE PROCEDURE OBTENER_SALA_ESPECIALIZADA_X_ID(
	IN _id_sala_especializada INT
)
BEGIN
	SELECT se.id_sala_especializada, ac.espacio_en_m2, ac.torre, ac.piso, se.nombre, se.tipo_sala, se.posee_equipamiento_imagenologia FROM ambiente_clinico ac INNER JOIN sala_especializada se ON ac.id_ambiente_clinico = se.id_sala_especializada WHERE ac.activo = 1 AND se.id_sala_especializada = _id_sala_especializada;
END$
DELIMITER ;
-- Probando Procedimientos Almacenados
CALL INSERTAR_SALA_ESPECIALIZADA(@id1,35.8,'A',1,'HIPOLITO UNANUE','CIRUGIA',true);
CALL INSERTAR_SALA_ESPECIALIZADA(@id2,40.4,'B',2,'ALCIDES CARRION','UCI',true);
CALL INSERTAR_SALA_ESPECIALIZADA(@id3,58.2,'A',1,'HERMILIO VALDIZAN','EMERGENCIA',false);
CALL INSERTAR_SALA_ESPECIALIZADA(@id4,37.5,'C',1,'JULIO C TELLO','EMERGENCIA',false);
CALL ELIMINAR_SALA_ESPECIALIZADA(@id4);