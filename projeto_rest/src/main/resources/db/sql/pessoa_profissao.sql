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

INSERT INTO `pessoa_profissao` (`id`,`pessoa_id`,`profissao_id`,`flg_principal`) VALUES (null,4,317,1),
																				   (null,5,267,1),
																				   (null,6,319,1),
																				   (null,9,267,1),
																				   (null,12,214,1),
																				   (null,15,317,1),
																				   (null,17,144,1),
																				   (null,4,318,0),
																				   (null,4,319,0),
																				   (null,18,311,1),
																				   (null,19,311,1),
																				   (null,11,319,1),
																				   (null,3,26,1),
																				   (null,3,256,0),
																				   (null,8,320,1),
																				   (null,7,321,1),
																				   (null,1,256,0),
																				   (null,1,26,1),
																				   (null,1,278,0),
																				   (null,20,322,1),
																				   (null,26,323,1),
																				   (null,2,26,1),
																				   (null,2,256,0);