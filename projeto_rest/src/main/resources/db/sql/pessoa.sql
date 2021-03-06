DROP TABLE IF EXISTS `pessoa`;
CREATE TABLE `pessoa` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomePessoa` varchar(250) NOT NULL,
  `logradouro_id` int(10) unsigned NOT NULL,
  `numero` varchar(45) NOT NULL,
  `complemento` varchar(100) DEFAULT NULL,
  `descricao` text,
  `cpf_cnpj` varchar(16) DEFAULT NULL,
  `inscricaoEstadual` varchar(45) DEFAULT NULL,
  `razaoSocial` varchar(255) DEFAULT NULL,
  `pessoa_tipo_id` int(10) unsigned NOT NULL,
  `foto` blob,
  `nota_id` int(10) unsigned,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`logradouro_id`) REFERENCES `logradouro` (`id`),
  CONSTRAINT `FK_pessoa_tipo` FOREIGN KEY (`pessoa_tipo_id`) REFERENCES `pessoa_tipo` (`id`),
  CONSTRAINT `FK_nota` FOREIGN KEY (`nota_id`) REFERENCES `nota`(`id`)) ENGINE=InnoDB;

INSERT INTO `pessoa` (`id`,`nomePessoa`,`logradouro_id`,`numero`,`complemento`,`descricao`,`cpf_cnpj`,`inscricaoEstadual`,`razaoSocial`,`pessoa_tipo_id`,`foto`,`nota_id`) VALUES 
(null,'Rodrigo Sebben',1,'340','Bloco H ap 102',NULL,'97899488087',NULL,NULL,1,null,null),
(null,'Natalia Schnechel',52,'150',NULL,NULL,NULL,NULL,NULL,1,null,null),
(null,'Viviane Loff',6626,'340','Bloco H ap 102',NULL,NULL,NULL,NULL,2,null,null),
(null,'Leonardo Simon',33772,'345',NULL,NULL,NULL,NULL,NULL,2,null,null),
(null,'Adriana Schmitt',335,'123',NULL,NULL,NULL,NULL,NULL,2,null,null),
(null,'Fulano de tal',8383,'123',NULL,'Trabalho a 14 anos com pinturas prediais e domiciliares',NULL,NULL,NULL,2,null,null),
(null,'Warlei Schnechel',5333,'123',NULL,NULL,NULL,NULL,NULL,2,null,null),
(null,'Suzana Magalhaes',246,'70',NULL,NULL,NULL,NULL,NULL,2,null,null),
(null,'Beltrano de tal',136,'625',NULL,NULL,NULL,NULL,NULL,2,null,null),
(null,'Maria Aparecida da Silva',722,'894','Centro admin ielb',NULL,'92990092000112',NULL,NULL,2,null,null),
(null,'Ciclano de tal',4722,'456',NULL,'Instalo impressoras, configuro windows, e dou aulas de informativa',NULL,NULL,NULL,2,null,null),
(null,'Joao Kleber',772,'670',NULL,NULL,NULL,NULL,NULL,2,null,null),
(null,'Funer�ria v� com deus',833,'1234',NULL,'Trabalhando com seguran�a e compromisso a mais de 20 anos',NULL,NULL,NULL,3,null,null),
(null,'Madereira Kastelo',883,'1150',NULL,NULL,NULL,NULL,NULL,3,null,null),
(null,'Ferragem s�o pedro',233494,'1150',NULL,NULL,NULL,NULL,NULL,3,null,null),
(null,'Daenerys Targaryen',3555,'123',NULL,'Rainha dos �ndalos dos Rhoinares e dos Primeiros Homens\r\nSenhora dos Sete Reinos\r\nKhaleesi\r\nDestruidora de Correntes\r\nRainha de Meereen\r\nPrincesa de Pedra do Drag�o',NULL,NULL,NULL,2,null,null),
(null,'jason voorhees',22335,'123',NULL,NULL,NULL,NULL,NULL,2,null,null);
