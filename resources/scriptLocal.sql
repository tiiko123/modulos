create database horarios;
use horarios;
CREATE TABLE `horarios`.`profes` ( `id` INT NOT NULL , `nombre_profesor` VARCHAR(50) NOT NULL , PRIMARY KEY (`id`)) ENGINE = MyISAM;
INSERT INTO `profes` (`id`, `nombre_profesor`) VALUES ('1', 'Gerardo Loperena');
INSERT INTO `profes` (`id`, `nombre_profesor`) VALUES ('2', 'Pablo Alberto Ramirez Ramirez');
INSERT INTO `profes` (`id`, `nombre_profesor`) VALUES ('3', 'Francisco Javier Villanueva');
INSERT INTO `profes` (`id`, `nombre_profesor`) VALUES ('4', 'Alan Zapata Gracia');