DROP TABLE IF EXISTS `apoio`;

CREATE TABLE `apoio` (
  `cod_apoio` int(11) NOT NULL AUTO_INCREMENT,
  `cnpj` varchar(14) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `contato` varchar(50) NOT NULL,
  `email` varchar(30) NOT NULL,
  `ddd` varchar(2) NOT NULL,
  `telefone` varchar(9) NOT NULL,
  PRIMARY KEY (`cod_apoio`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `evento`;

CREATE TABLE `evento` (
  `cod_evento` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`cod_evento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `edicao`;

CREATE TABLE `edicao` (
  `cod_evento` int(11) NOT NULL,
  `edicao` int(11) NOT NULL,
  `realizacao` date NOT NULL,
  `hora` time NOT NULL,
  PRIMARY KEY (`cod_evento`,`edicao`),
  CONSTRAINT `fk_evento_edicao` FOREIGN KEY (`cod_evento`) REFERENCES `evento` (`cod_evento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `pessoas`;

CREATE TABLE `pessoas` (
  `cod_participante` int(11) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `email` varchar(30) NOT NULL,
  `ddd` varchar(2) DEFAULT NULL,
  `telefone` varchar(9) DEFAULT NULL,
  `ddd_cel` varchar(2) DEFAULT NULL,
  `celular` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`cod_participante`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `checklist`;

CREATE TABLE `checklist` (
  `cod_check` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) NOT NULL,
  PRIMARY KEY (`cod_check`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `checklist_evento`;

CREATE TABLE `checklist_evento` (
  `cod_evento` int(11) NOT NULL,
  `edicao` int(11) NOT NULL,
  `cod_check` int(11) NOT NULL,
  `prazo` date NOT NULL,
  `cod_participante` int(11) NOT NULL,
  PRIMARY KEY (`cod_evento`,`edicao`,`cod_check`),
  KEY `fk_cheklist_checklist_evento` (`cod_check`),
  CONSTRAINT `fk_cheklist_checklist_evento` FOREIGN KEY (`cod_check`) REFERENCES `checklist` (`cod_check`),
  CONSTRAINT `fk_edicao_cheklist_evento` FOREIGN KEY (`cod_evento`, `edicao`) REFERENCES `edicao` (`cod_evento`, `edicao`),
  CONSTRAINT `fk_pessoas_checklist_evento` FOREIGN KEY (`cod_participante`) REFERENCES `pessoas` (`cod_participante`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `participantes`;

CREATE TABLE `participantes` (
  `cod_participante` int(11) NOT NULL,
  `cod_evento` int(11) NOT NULL,
  `edicao` int(11) NOT NULL,
  PRIMARY KEY (`cod_evento`,`edicao`,`cod_participante`) USING BTREE,
  KEY `fk_pessoas_participantes` (`cod_participante`),
  CONSTRAINT `fk_edicao_participantes` FOREIGN KEY (`cod_evento`, `edicao`) REFERENCES `edicao` (`cod_evento`, `edicao`),
  CONSTRAINT `fk_pessoas_participantes` FOREIGN KEY (`cod_participante`) REFERENCES `pessoas` (`cod_participante`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `patrocinio`;

CREATE TABLE `patrocinio` (
  `cod_evento` int(11) NOT NULL,
  `edicao` int(11) NOT NULL,
  `cod_apoio` int(11) NOT NULL,
  KEY `fk_edicao_patrocinio` (`cod_evento`,`edicao`),
  KEY `fk_apoio_patrocinio` (`cod_apoio`),
  CONSTRAINT `fk_apoio_patrocinio` FOREIGN KEY (`cod_apoio`) REFERENCES `apoio` (`cod_apoio`),
  CONSTRAINT `fk_edicao_patrocinio` FOREIGN KEY (`cod_evento`, `edicao`) REFERENCES `edicao` (`cod_evento`, `edicao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `premiados`;

CREATE TABLE `premiados` (
  `cod_evento` int(11) NOT NULL,
  `edicao` int(11) NOT NULL,
  `cod_participante` int(11) NOT NULL,
  `colocacao` varchar(15) NOT NULL,
  PRIMARY KEY (`cod_evento`,`edicao`,`cod_participante`),
  CONSTRAINT `fk_participantes_premiados` FOREIGN KEY (`cod_evento`, `edicao`, `cod_participante`) REFERENCES `participantes` (`cod_evento`, `edicao`, `cod_participante`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;