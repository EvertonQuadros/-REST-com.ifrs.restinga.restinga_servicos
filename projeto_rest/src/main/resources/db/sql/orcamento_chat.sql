DROP TABLE IF EXISTS `orcamento_chat`;
CREATE TABLE `orcamento_chat` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dataEnvio` datetime NOT NULL,
  `texto` text NOT NULL,
  `imagem` blob,
  `orcamento_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_Chat_orcamento` FOREIGN KEY (`orcamento_id`) REFERENCES `orcamento` (`id`)
  )ENGINE=InnoDB;