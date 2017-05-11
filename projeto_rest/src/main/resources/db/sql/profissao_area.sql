DROP TABLE IF EXISTS `profissao_area`;
CREATE TABLE `profissao_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `profissao_area` (`id`,`nome`) VALUES 
 (1,'Administração, negócios e serviços'),
 (2,'Artes e Design'),
 (3,'Ciências Biológicas e da Terra'),
 (4,'Ciências Exatas e Informática'),
 (5,'Ciências Sociais e Humanas'),
 (6,'Comunicação e Informação'),
 (7,'Engenharia e Produção'),
 (8,'Saúde e Bem-Estar'),
 (9,'Undefined');