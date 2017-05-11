DROP TABLE IF EXISTS `pessoa_contato`;
CREATE TABLE `pessoa_contato` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pessoa_id` int(10) unsigned NOT NULL,
  `contato_tipo_id` int(10) unsigned NOT NULL,
  `contato` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_pessoa_contato_contatotipo` FOREIGN KEY (`contato_tipo_id`) REFERENCES `contato_tipo` (`id`),
  CONSTRAINT `FK_Pessoa_contato_pessoa` FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB;

INSERT INTO `pessoa_contato` (`id`,`pessoa_id`,`contato_tipo_id`,`contato`) VALUES (1,1,1,'(51) 30114-2114'),
																				   (2,1,2,'(51) 99196-4473'),
																				   (3,1,3,'(51) 99196-4473'),
																				   (4,1,4,'https://www.facebook.com/rodrigo.sebben?ref=bookmarks'),
																				   (5,1,5,'rsebben@restinga.ifrs.edu.br'),
																				   (6,1,6,'www.rssistemas.com.br'),
																				   (7,2,1,'(51)985318224');