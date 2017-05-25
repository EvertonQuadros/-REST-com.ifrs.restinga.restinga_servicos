
DROP TABLE IF EXISTS `nota`;
CREATE TABLE `nota` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `valor` int(10) unsigned NOT NULL,
  `imagem` blob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;
