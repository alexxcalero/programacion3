DROP SCHEMA IF EXISTS examen_final_20242;
CREATE SCHEMA examen_final_20242;
USE examen_final_20242;
DROP TABLE IF EXISTS postulante;
DROP TABLE IF EXISTS universidad;
CREATE TABLE universidad(
	id_universidad INT AUTO_INCREMENT,
    nombre VARCHAR(150),
    siglas VARCHAR(10),
    activa TINYINT,
    PRIMARY KEY(id_universidad)
)ENGINE=InnoDB;
CREATE TABLE postulante(
	id_postulante INT AUTO_INCREMENT,
    fid_universidad_origen INT,
    DNI VARCHAR(8),
	nombre VARCHAR(100),
    apellido_paterno VARCHAR(100),
    promedio_acumulado_pregrado DECIMAL(10,2),
    fue_tercio_superior TINYINT,
    fue_miembro_grupo_investigacion TINYINT,
    fue_deportista_calificado TINYINT,
    modalidad_preferida ENUM('PRESENCIAL','VIRTUAL','HIBRIDA'),
    activo TINYINT,
    PRIMARY KEY(id_postulante),
    FOREIGN KEY(fid_universidad_origen) REFERENCES universidad(id_universidad)
)ENGINE=InnoDB;
-- Insertando registros
INSERT INTO universidad(nombre,siglas,activa) VALUES('PONTIFICIA UNIVERSIDAD CATOLICA DEL PERU','PUCP',1);
INSERT INTO universidad(nombre,siglas,activa) VALUES('UNIVERSIDAD NACIONAL MAYOR DE SAN MARCOS','UNMSM',1);
INSERT INTO universidad(nombre,siglas,activa) VALUES('UNIVERSIDAD PERUANA CAYETANO HEREDIA','UPCH',1);
INSERT INTO universidad(nombre,siglas,activa) VALUES('UNIVERSIDAD NACIONAL AGRARIA LA MOLINA','UNALM',1);
INSERT INTO universidad(nombre,siglas,activa) VALUES('UNIVERSIDAD NACIONAL DE INGENIERIA','UNI',1);
INSERT INTO universidad(nombre,siglas,activa) VALUES('UNIVERSIDAD PERUANA DE CIENCIAS APLICADAS','UPC',1);
INSERT INTO universidad(nombre,siglas,activa) VALUES('UNIVERSIDAD DEL PACIFICO','UP',1);
INSERT INTO universidad(nombre,siglas,activa) VALUES('UNIVERSIDAD SAN IGNACIO DE LOYOLA','USIL',1);
-- Creación de Procedimientos Almacenados
DROP PROCEDURE IF EXISTS LISTAR_UNIVERSIDADES_TODAS;
DROP PROCEDURE IF EXISTS INSERTAR_POSTULANTE;
DELIMITER $
CREATE PROCEDURE LISTAR_UNIVERSIDADES_TODAS()
BEGIN
	SELECT id_universidad, nombre, siglas FROM universidad WHERE activa = 1;
END$
CREATE PROCEDURE INSERTAR_POSTULANTE(
	OUT _id_postulante INT,
    IN _fid_universidad_origen INT,
    IN _DNI VARCHAR(8),
    IN _nombre VARCHAR(100),
    IN _apellido_paterno VARCHAR(100),
    IN _promedio_acumulado_pregrado DECIMAL(10,2),
    IN _fue_tercio_superior TINYINT,
    IN _fue_miembro_grupo_investigacion TINYINT,
    IN _fue_deportista_calificado TINYINT,
    IN _modalidad_preferida ENUM('PRESENCIAL','VIRTUAL','HIBRIDA')
)
BEGIN
	INSERT postulante(fid_universidad_origen,DNI,nombre,apellido_paterno,promedio_acumulado_pregrado,fue_tercio_superior,fue_miembro_grupo_investigacion,fue_deportista_calificado,modalidad_preferida,activo) VALUES(_fid_universidad_origen,_DNI,_nombre,_apellido_paterno,_promedio_acumulado_pregrado,_fue_tercio_superior,_fue_miembro_grupo_investigacion,_fue_deportista_calificado,_modalidad_preferida,1);
    SET _id_postulante = @@last_insert_id;
END$