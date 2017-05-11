DROP TABLE IF EXISTS `pessoa_tipo`;
CREATE TABLE `pessoa_tipo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomeTipoPessoa` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

INSERT INTO `pessoa_tipo` (`id`,`nomeTipoPessoa`) VALUES (1,'USUARIO COMUM'),
														  (2,'PROFISSIONAL'),
														  (3,'EMPRESA');

