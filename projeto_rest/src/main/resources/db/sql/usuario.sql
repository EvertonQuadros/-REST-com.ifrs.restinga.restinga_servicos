DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(150) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `pessoa_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`id`)) ENGINE=InnoDB;
  
  insert into `usuario` (`id`,`email`,`senha`,`pessoa_id`) values (null,'Fulaninho@gmail.com','12345',6),
							   (null,'Ciclanho@gmail.com','123',12),
							   (null,'Beltrano@gmail.com','hehe123',9);