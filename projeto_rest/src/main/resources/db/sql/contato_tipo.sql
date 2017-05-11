DROP TABLE IF EXISTS `contato_tipo`;
CREATE TABLE `contato_tipo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomeTipoContato` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `contato_tipo` (`id`,`nomeTipoContato`) VALUES (1,'Telefone'),
														   (2,'Celular'),
														   (3,'WhatsApp'),
													       (4,'facebook'),
														   (5,'E-mail'),
														   (6,'Site');