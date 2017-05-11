DROP TABLE IF EXISTS `chat_cliente_profissional`;
CREATE TABLE `chat_cliente_profissional` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cliente_id` int(10) unsigned NOT NULL,
  `profissional_id` int(10) unsigned NOT NULL,
  `dataEnvio` datetime NOT NULL,
  `texto` text NOT NULL,
  `imagem` clob,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_Chat_cliente_profissional_Cliente` FOREIGN KEY (`cliente_id`) REFERENCES `pessoa` (`id`),
  CONSTRAINT `FK_Chat_cliente_profissional_Profissional` FOREIGN KEY (`profissional_id`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB;

INSERT INTO `chat_cliente_profissional` (`id`,`cliente_id`,`profissional_id`,`dataEnvio`,`texto`,`imagem`) VALUES
(null,5,4,NOW(),'teste 1',0xABC),
(null,4,5,NOW(),'teste 2',0xDEF); 