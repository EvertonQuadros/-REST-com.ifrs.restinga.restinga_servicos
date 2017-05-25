DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(150) NOT NULL,
  `email` varchar(255) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `pessoa_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`id`)) ENGINE=InnoDB;
							   
INSERT INTO `usuario` (`id`,`login`,`email`,`senha`,`pessoa_id`) VALUES 
	 (null,'Rodrigo Sebben','hyugasan@gmail.com','123456',1),
	 (null,'Natalia Schnechel','nsschnechel@restinga.ifrs.edu.br','123456',2),
	 (null,'teste','22222','123123123213',3),
	 (null,'Paulo','Infosebben@gmail.com','654321',4),
	 (null,'Natalia','nsschnechel@ifrs.edu.br','123456',5),
	 (null,'Regio','Regio@restinga.com','123456',6),
	 (null,'Regio','Regio@ifrs.com','123456',7),
	 (null,'Jsjehsh','Hshshs@jdjdjd.com','098765',8),
	 (null,'Ana Maria','anamaria@gmail.com','crisis013',9),
	 (null,'Ana Maria','asdasd@asasdas.com','crisis013',15),
	 (null,'Adriel domagalski','adriel.soares3@gmail.com','123456',11),
	 (null,'Mathias gheno azzolini','Mathiasgheno@gmail.com','mathias',12);