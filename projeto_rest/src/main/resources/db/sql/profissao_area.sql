DROP TABLE IF EXISTS `profissao_area`;
CREATE TABLE `profissao_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `profissao_area` (`id`,`nome`) VALUES 
 (1,'Administra��o, neg�cios e servi�os'),
 (2,'Artes e Design'),
 (3,'Ci�ncias Biol�gicas e da Terra'),
 (4,'Ci�ncias Exatas e Inform�tica'),
 (5,'Ci�ncias Sociais e Humanas'),
 (6,'Comunica��o e Informa��o'),
 (7,'Engenharia e Produ��o'),
 (8,'Sa�de e Bem-Estar'),
 (9,'Undefined');