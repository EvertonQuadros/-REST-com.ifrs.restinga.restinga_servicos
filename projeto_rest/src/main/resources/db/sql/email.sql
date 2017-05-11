DROP TABLE IF EXISTS `email`;
CREATE TABLE `email` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `token` varchar(38) NOT NULL,
  `usuario_id` int(10) unsigned NOT NULL,
  `data_expira` datetime NOT NULL,
  `confirmacao` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)) ENGINE=InnoDB;