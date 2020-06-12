CREATE DATABASE IF NOT EXISTS selesnyadb;
USE selesnyadb;

DROP TABLE IF EXISTS grupo_materia_profesor;
DROP TABLE IF EXISTS uso_aula_grupo;
DROP TABLE IF EXISTS aula_equipo;
DROP TABLE IF EXISTS login;
DROP TABLE IF EXISTS disponibilidad;
DROP TABLE IF EXISTS materia_usuario;
DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS materias;
DROP TABLE IF EXISTS equipo;
DROP TABLE IF EXISTS plan_estudios;
DROP TABLE IF EXISTS aulas;
DROP TABLE IF EXISTS grupos;
DROP TABLE IF EXISTS carrera;
DROP TABLE IF EXISTS categorias_equipo;

CREATE TABLE IF NOT EXISTS aulas(id_aula VARCHAR(10) NOT NULL PRIMARY KEY, nombre VARCHAR(100) NOT NULL, tipo VARCHAR(20) NOT NULL, capacidad INT(11) NOT NULL )ENGINE=InnoDB;
INSERT INTO aulas(id_aula, nombre, tipo, capacidad) VALUES('232','Aula 232','Laboratorio','35');
INSERT INTO aulas(id_aula, nombre, tipo, capacidad) VALUES('233','Aula 233','Salon','30');

CREATE TABLE IF NOT EXISTS carrera (idcarrera TINYINT NOT NULL PRIMARY KEY, nombre_carrera VARCHAR(100) NOT NULL )ENGINE=InnoDB;
INSERT INTO carrera (idcarrera, nombre_carrera)  VALUES (1,'Ingenieria en Tecnologias de la Informacion');
INSERT INTO carrera (idcarrera, nombre_carrera)  VALUES (2,'Ingenieria en Mecatronica');
INSERT INTO carrera (idcarrera, nombre_carrera)  VALUES (3,'Ingenieria en Sistemas Automotrices');
INSERT INTO carrera (idcarrera, nombre_carrera)  VALUES (4,'Ingenieria en Manufactura');
INSERT INTO carrera (idcarrera, nombre_carrera)  VALUES (5,'Licenciatura en Administracion y Gestion de PyMEs');

CREATE TABLE IF NOT EXISTS grupos( clv_grupo VARCHAR(10) NOT NULL PRIMARY KEY, turno boolean )ENGINE=InnoDB;
INSERT INTO grupos(clv_grupo, turno) VALUES('ITI6-1',1);
INSERT INTO grupos(clv_grupo, turno) VALUES('ITI6-2',1);

CREATE TABLE IF NOT EXISTS categorias_equipo( id_categoria INT(11) NOT NULL PRIMARY KEY, nombre_categoria VARCHAR(100) NOT NULL, descripcion_categoria VARCHAR(300) NOT NULL )ENGINE=InnoDB;
INSERT INTO categorias_equipo(id_categoria, nombre_categoria, descripcion_categoria) VALUES(1,'CPUs','Esta es la descripcion de la categoria CPUs');
INSERT INTO categorias_equipo(id_categoria, nombre_categoria, descripcion_categoria) VALUES(2,'Monitores','Esta es la descripcion de la categoria monitores');

CREATE TABLE IF NOT EXISTS equipo( id_equipo INT(11) NOT NULL PRIMARY KEY, nombre VARCHAR(100) NOT NULL, descripcion VARCHAR(100) NOT NULL, id_categoria INT(11), FOREIGN KEY(id_categoria) REFERENCES categorias_equipo(id_categoria) ON DELETE CASCADE ON UPDATE CASCADE )ENGINE=InnoDB;
INSERT INTO equipo(id_equipo, nombre, descripcion, id_categoria) VALUES(1,'Equipo1','Equipo del laboratorio 232',1);
INSERT INTO equipo(id_equipo, nombre, descripcion, id_categoria) VALUES(2,'Equipo2','Equipo del laboratorio 232',2);

CREATE TABLE IF NOT EXISTS aula_equipo( id_equipo INT(11) NOT NULL, id_aula VARCHAR(10) NOT NULL, cantidad INT(11) NOT NULL, UNIQUE(id_equipo), UNIQUE(id_aula), FOREIGN KEY(id_equipo) REFERENCES equipo(id_equipo), FOREIGN KEY(id_aula) REFERENCES aulas(id_aula) ON DELETE CASCADE ON UPDATE CASCADE )ENGINE=InnoDB;
INSERT INTO aula_equipo(id_equipo, id_aula, cantidad) VALUES(1,232,10);

CREATE TABLE IF NOT EXISTS usuarios ( clv_usuario VARCHAR(50) NOT NULL PRIMARY KEY, idcarrera TINYINT NOT NULL, nombre_usuario VARCHAR(50) NOT NULL, nivel_ads VARCHAR(5) NOT NULL DEFAULT 'Ing.' CHECK (nivel_ads IN ('Dr.', 'M.C', 'Ing.', 'Lic.')), contrato VARCHAR(3) NOT NULL DEFAULT 'PA' CHECK (contrato IN ('PTC', 'PA')), FOREIGN KEY(idcarrera) REFERENCES carrera(idcarrera) ON DELETE CASCADE ON UPDATE CASCADE )ENGINE=InnoDB;
INSERT INTO usuarios(clv_usuario, idcarrera, nombre_usuario, nivel_ads, contrato) VALUES('user1',1,'Said','Dr.','PTC');
INSERT INTO usuarios(clv_usuario, idcarrera, nombre_usuario, nivel_ads, contrato) VALUES('user2',1,'Bewt','Ing.','PA');

CREATE TABLE IF NOT EXISTS login (clv_usuario VARCHAR(50) NOT NULL PRIMARY KEY, pass_usuario VARCHAR(50) NOT NULL, tipo_usuario CHAR(4) NOT NULL CHECK(tipo_usuario IN ('DIRE','PROF')), FOREIGN KEY (clv_usuario) REFERENCES usuarios(clv_usuario) ON DELETE CASCADE ON UPDATE CASCADE)ENGINE=InnoDB;
INSERT INTO login(clv_usuario, pass_usuario, tipo_usuario) VALUES('user1','admin123','PROF');
INSERT INTO login(clv_usuario, pass_usuario, tipo_usuario) VALUES('user2','admin456','PROF');

CREATE TABLE IF NOT EXISTS plan_estudios ( clv_plan VARCHAR(10) NOT NULL PRIMARY KEY, nombre_plan VARCHAR(45) NOT NULL, nivel VARCHAR(3) NOT NULL, idcarrera TINYINT NOT NULL, FOREIGN KEY(idcarrera) REFERENCES carrera(idcarrera) ON DELETE CASCADE ON UPDATE CASCADE )ENGINE=InnoDB;
INSERT INTO plan_estudios (clv_plan, nombre_plan, nivel, idcarrera) VALUES ('ITI-2010', 'Profesional asociado a Mecatronica', '5', 3);
INSERT INTO plan_estudios (clv_plan, nombre_plan, nivel, idcarrera) VALUES ('ITI-2018', 'Profesional asociado a ITI', '6', 1);

CREATE TABLE IF NOT EXISTS materias ( nombre_materia VARCHAR(50) NOT NULL, clv_materia VARCHAR(10) NOT NULL PRIMARY KEY, creditos TINYINT NOT NULL, cuatrimestre TINYINT NOT NULL, posicion TINYINT NOT NULL, clv_plan VARCHAR(10) NOT NULL, horas_x_semana TINYINT NOT NULL, tipo_materia CHAR(3) NOT NULL, FOREIGN KEY(clv_plan) REFERENCES plan_estudios(clv_plan) ON DELETE CASCADE ON UPDATE CASCADE )ENGINE=InnoDB;
INSERT INTO materias(nombre_materia, clv_materia, creditos, cuatrimestre, posicion, clv_plan, horas_x_semana, tipo_materia) VALUES ('Algebra lineal','AL22', 5, 3, 1,'ITI-2018',20,'ITI');
INSERT INTO materias(nombre_materia, clv_materia, creditos, cuatrimestre, posicion, clv_plan, horas_x_semana, tipo_materia) VALUES ('Fundamentos de Programacion Orientada a Objetos','FPOO1', 5, 5, 1, 'ITI-2018', 15, 'ITI');

CREATE TABLE IF NOT EXISTS disponibilidad(dia TINYINT NOT NULL,espacio_tiempo TINYINT NOT NULL,clv_usuario VARCHAR(50) NOT NULL, UNIQUE(clv_usuario), FOREIGN KEY(clv_usuario) REFERENCES usuarios(clv_usuario) ON DELETE CASCADE ON UPDATE CASCADE )ENGINE=InnoDB;
INSERT INTO disponibilidad(dia, espacio_tiempo, clv_usuario) VALUES(10,0,"user1");
INSERT INTO disponibilidad(dia, espacio_tiempo, clv_usuario) VALUES(10,1,"user2");

CREATE TABLE IF NOT EXISTS materia_usuario( clv_materia VARCHAR(10) NOT NULL, clv_plan VARCHAR(10) NOT NULL, clv_usuario VARCHAR(10) NOT NULL, puntos_confianza TINYINT NULL DEFAULT 0, puntos_director TINYINT NULL DEFAULT 0, FOREIGN KEY(clv_materia) REFERENCES materias(clv_materia), FOREIGN KEY(clv_plan) REFERENCES plan_estudios(clv_plan), FOREIGN KEY(clv_usuario) REFERENCES usuarios(clv_usuario) ON DELETE CASCADE ON UPDATE CASCADE )ENGINE=InnoDB;
INSERT INTO materia_usuario(clv_materia, clv_plan, clv_usuario, puntos_confianza, puntos_director) VALUES('FPOO1','ITI-2018','user1',5,5);
INSERT INTO materia_usuario(clv_materia, clv_plan, clv_usuario, puntos_confianza, puntos_director) VALUES('AL22','ITI-2018','user2',5,5);

CREATE TABLE IF NOT EXISTS uso_aula_grupo(dia TINYINT NOT NULL, espacio_tiempo TINYINT NOT NULL, id_aula VARCHAR(10) NOT NULL, clv_grupo VARCHAR(10) NOT NULL,clv_materia VARCHAR(10) NOT NULL, FOREIGN KEY(id_aula) REFERENCES aulas(id_aula), FOREIGN KEY(clv_grupo) REFERENCES grupos(clv_grupo), FOREIGN KEY(clv_materia) REFERENCES materias(clv_materia) ON DELETE CASCADE ON UPDATE CASCADE)ENGINE=InnoDB;
INSERT INTO uso_aula_grupo(dia, espacio_tiempo, id_aula, clv_grupo, clv_materia) VALUES(20, 1, '232','ITI6-1','AL22');
INSERT INTO uso_aula_grupo(dia, espacio_tiempo, id_aula, clv_grupo, clv_materia) VALUES(21, 1, '233','ITI6-2','FPOO1');

CREATE TABLE IF NOT EXISTS grupo_materia_profesor( clv_grupo VARCHAR(10) NOT NULL, clv_materia VARCHAR(10) NOT NULL, clv_usuario VARCHAR(50) NOT NULL, PRIMARY KEY(clv_grupo, clv_materia, clv_usuario), FOREIGN KEY(clv_grupo) REFERENCES grupos(clv_grupo), FOREIGN KEY(clv_materia) REFERENCES materias(clv_materia), FOREIGN KEY(clv_usuario) REFERENCES usuarios(clv_usuario) ON DELETE CASCADE ON UPDATE CASCADE )ENGINE=InnoDB;
INSERT INTO grupo_materia_profesor(clv_grupo, clv_materia, clv_usuario) VALUES('ITI6-1','FPOO1','user1');
INSERT INTO grupo_materia_profesor(clv_grupo, clv_materia, clv_usuario) VALUES('ITI6-2','AL22','user2');
--<FIN>