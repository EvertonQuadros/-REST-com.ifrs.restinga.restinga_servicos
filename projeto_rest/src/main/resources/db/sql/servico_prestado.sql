DROP TABLE IF EXISTS `servico_prestado`;
CREATE TABLE `servico_prestado` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pessoa_Contratante_id` int(10) unsigned NOT NULL,
  `pessoa_Contratada_id` int(10) unsigned NOT NULL,
  `descricao` text,
  `nota_contratado` int(10) unsigned NOT NULL,
  `data_contrato` datetime NOT NULL,
  `data_termino` datetime NOT NULL,
  `orcamento` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_Servicos_Prestado_1` FOREIGN KEY (`pessoa_Contratante_id`) REFERENCES `pessoa` (`id`),
  CONSTRAINT `FK_Servicos_Prestado_2` FOREIGN KEY (`pessoa_Contratada_id`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB;

INSERT INTO `servico_prestado` (`id`,`pessoa_Contratante_id`,`pessoa_Contratada_id`,`descricao`,`nota_contratado`,`data_contrato`,`data_termino`,`orcamento`) VALUES 
 (1,1,3,'Serviços aleatorios',4,'2017-04-05 10:50:21','2017-04-05 10:50:21',0),
 (2,2,4,'Serviços aleatorios',5,'2017-04-05 10:51:17','2017-04-05 10:51:17',0),
 (3,2,4,'Serviços aleatorios',1,'2017-04-05 10:51:18','2017-04-05 10:51:18',1),
 (4,2,4,'Serviços aleatorios',5,'2017-04-05 10:54:28','2017-04-05 10:54:28',1),
 (5,2,4,'Serviços aleatorios',2,'2017-04-05 10:54:29','2017-04-05 10:54:29',0),
 (6,2,4,'Serviços aleatorios',3,'2017-04-05 10:54:30','2017-04-05 10:54:30',0),
 (7,2,4,'Serviços aleatorios',4,'2017-04-05 10:54:31','2017-04-05 10:54:31',1),
 (8,2,4,'Serviços aleatorios',4,'2017-04-05 10:54:32','2017-04-05 10:54:32',1);
