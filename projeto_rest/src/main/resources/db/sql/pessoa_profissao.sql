DROP TABLE IF EXISTS `pessoa_profissao`;
CREATE TABLE `pessoa_profissao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pessoa_id` int(10) unsigned NOT NULL,
  `profissao_id` int(11) unsigned NOT NULL,
  `flg_principal` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`pessoa_id`,`profissao_id`),
  FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`id`),
  FOREIGN KEY (`profissao_id`) REFERENCES `profissao` (`id`)
) ENGINE=InnoDB;

INSERT INTO `pessoa_profissao` (`id`,`pessoa_id`,`profissao_id`,`flg_principal`) VALUES (null,1,317,1),
																				   (null,2,267,1),
																				   (null,3,319,1),
																				   (null,4,267,1),
																				   (null,5,214,1),
																				   (null,6,317,1),
																				   (null,7,144,1),
																				   (null,4,318,0),
																				   (null,4,319,0),
																				   (null,8,311,1),
																				   (null,9,311,1),
																				   (null,10,319,1),
																				   (null,11,26,1),
																				   (null,3,256,0),
																				   (null,12,320,1),
																				   (null,13,321,1),
																				   (null,1,256,0),
																				   (null,14,26,1),
																				   (null,1,278,0),
																				   (null,15,322,1),
																				   (null,16,323,1),
																				   (null,17,26,1),
																				   (null,2,256,0);