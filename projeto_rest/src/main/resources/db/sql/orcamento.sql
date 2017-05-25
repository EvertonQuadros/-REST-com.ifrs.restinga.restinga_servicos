DROP TABLE IF EXISTS `orcamento`;
CREATE TABLE `orcamento` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `contratante_id` int(10) unsigned NOT NULL,
  `contratado_id` int(10) unsigned NOT NULL,
  `data_orcamento` datetime NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `descricao` text NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_Chat_cliente_profissional_Contratante` FOREIGN KEY (`contratante_id`) REFERENCES `pessoa` (`id`),
  CONSTRAINT `FK_Chat_cliente_profissional_Contratado` FOREIGN KEY (`contratado_id`) REFERENCES `pessoa` (`id`)
  )ENGINE=InnoDB;
  
  INSERT INTO `orcamento` (`id`,`contratante_id`,`contratado_id`,`data_orcamento`,`status`,`descricao`) VALUES 
 (1,1,2,'2017-05-17 22:08:28',0,'Gostaria de trocar meu chuveiro e a torneira da pia da cozinha\r\n'),
 (2,1,2,'2017-05-17 22:13:39',0,'preciso desenvolver um sistema de caixa para minha empresa'),
 (3,1,3,'2017-05-17 22:17:59',0,'preciso carnear um boi'),
 (4,1,9,'2017-05-17 23:47:01',0,'oi teste'),
 (5,1,4,'2017-05-17 23:59:23',0,'Tejdjjs hsjjshsh shshhshhshs hshshhsuwhhwhs shshhshhshs hshshhsuwhhwhs'),
 (6,1,3,'2017-05-18 00:06:06',0,'Gdgjr DF hh'),
 (7,1,9,'2017-05-18 00:15:31',0,'Gostaria de fazer uma reforma na minha cozinha completa. \r\n- Trocar os azulejos, e o piso;\r\n- Colocar armarios;\r\n- Colocar encanamento'),
 (8,1,9,'2017-05-18 00:27:50',0,'asdads'),
 (9,1,3,'2017-05-18 00:38:55',0,'Jshshs'),
 (10,1,12,'2017-05-18 00:43:52',0,'Oi tudo bem?'),
 (11,1,11,'2017-05-18 09:05:10',0,'Teste novo hoje'),
 (12,1,11,'2017-05-18 09:09:51',0,'Jdjdjdhsj'),
 (13,1,11,'2017-05-18 16:42:17',0,'oi tudo bem:'),
 (14,1,4,'2017-05-19 09:02:25',0,'Rgfdgg fy tb gg ');