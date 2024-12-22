DROP TABLE IF EXISTS sala_especializada;
CREATE TABLE sala_especializada(
	id_sala_especializada INT AUTO_INCREMENT,
    nombre VARCHAR(100),
    espacio_en_m2 DECIMAL(10,2),
    torre CHAR,
    piso INT,
    posee_equipamiento_imagenologia TINYINT,
    activa TINYINT,
    PRIMARY KEY(id_sala_especializada)
)ENGINE=InnoDB;
INSERT INTO sala_especializada(nombre,espacio_en_m2,torre,piso,posee_equipamiento_imagenologia,activa) values('CAYETANO HEREDIA',39.4,'A',3,true,1);