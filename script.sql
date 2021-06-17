
CREATE TABLE user (
	cod_user INT(3) NOT NULL AUTO_INCREMENT ,
	dni VARCHAR(9) NOT NULL ,
	nom VARCHAR(50) NOT NULL ,
	fechNac DATE NOT NULL ,
	nomUser VARCHAR(25) NOT NULL ,
	clave VARCHAR(25) NOT NULL ,
	rol BOOLEAN NOT NULL );
	
INSERT INTO `user` (`cod_user`, `dni`, `nom`, `fechNac`, `nomUser`, `clave`, `rol`) 
VALUES (1, 22965424Q, Marcelina Gómez, 1991-06-20, admin, admin, 1),
	   (2, 97317168M, Pere Peris, 1995-02-17, user, user, 0),
	   (3, 03662813V, Rafaela Pastor, 1982-10-21, indstruc, 1xN5YQ10, 0),
	   (4, 46551727A, Humberto Almagro, 1977-07-18, rotatify, IN1issts, 0),
	   (5, 61767048L, Justa Carpio, 1984-11-23, finlazen, BRZNEt9R, 0),
	   (6, 92188554T, Jonathan Plaza, 1987-06-01, alInAC, 1MK6932g, 0),
	   (7, 35187411X, Olivia Solana, 1996-01-08, aceneyva, 1AjkmJJE, 0),
	   (8, 39541353Y, Cynthia Amat, 1974-08-09, finlazen, tFN4gwIb, 0),
	   (9, 96823056W, Angela Llopis, 1987-03-31, inglicom, inIGu9v1, 0),
	   (10, 30281513N, Yeray Cañete, 1992-02-29, trughuyu, TLitfVaH, 0);

	
CREATE TABLE med (
	cod_med INT(3) NOT NULL AUTO_INCREMENT ,
	nom VARCHAR(50) NOT NULL ,
	foto VARCHAR(50) NOT NULL ,
	dirección VARCHAR(50) NOT NULL );
	
INSERT INTO `med` (`cod_med`, `nom`, `foto`, `dirección`) 
VALUES (1, Yasmin Vicente, /data/1.jpeg, Alpedrete 7),
	   (2, Yanira Bernabe, /data/2.jpeg, 775 Moore Way),
	   (3, Luna San Miguel, /data/3.jpeg, 09675 Adelia Crescent),
	   (4, Nicoleta Medrano, /data/4.jpeg, 9495 Tina Pines),
	   (5, Humberto Burgos, /data/5.jpeg, 7757 Rosanna Lodge);
	
CREATE TABLE cita (
	cod_cita INT(3) NOT NULL AUTO_INCREMENT ,
	cod_user INT(3) NOT NULL ,
	cod_med INT(3) NOT NULL ,
	resumen VARCHAR(100) NOT NULL ,
	fecha DATE NOT NULL ,
	realizada BOOLEAN NOT NULL ,
	PRIMARY KEY (cod_user));
	
ALTER TABLE cita 
ADD CONSTRAINT cod_fr_med 
FOREIGN KEY (cod_med) 
REFERENCES med(cod_med) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE cita 
ADD CONSTRAINT cod_fr_user 
FOREIGN KEY (cod_user) 
REFERENCES user(cod_user) ON DELETE CASCADE ON UPDATE CASCADE;

INSERT INTO `cita` (`cod_cita`, `cod_user`, `cod_med`, `resumen`, `fecha`, `realizada`) 
VALUES (1, 3, 2, , 2021-06-24, 0),
	   (2, 1, 1, , 2021-06-21, 0),
	   (3, 2, 2, , 2021-06-24, 0),
	   (4, 3, 3, , 2021-06-25, 0),
	   (5, 4, 4, , 2021-06-22, 0),
	   (6, 5, 5, , 2021-06-29, 0),
	   (8, 2, 5, , 2021-06-22, 0);
	   

	   