DROP TABLE IF EXISTS local;
CREATE TABLE local(
	id_local INT AUTO_INCREMENT,
	nombre VARCHAR(100),
	direccion VARCHAR(400),
	capacidad INT,
	espacio_m2 DECIMAL(10,2),
	tipo_local ENUM('TEATRO','AUDITORIO','ANFITEATRO','ESTADIO'),
	activo TINYINT,
	PRIMARY KEY(id_local)
)ENGINE=InnoDB;