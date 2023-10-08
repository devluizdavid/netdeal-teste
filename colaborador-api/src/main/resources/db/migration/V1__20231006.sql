CREATE TABLE  IF NOT EXISTS `revinfo` (
                                          `rev` int NOT NULL AUTO_INCREMENT,
                                          `revtstmp` bigint DEFAULT NULL,
                                          PRIMARY KEY (`rev`)
    ) ENGINE=InnoDB ;



CREATE TABLE IF NOT EXISTS `usuario` (
    `id` int NOT NULL AUTO_INCREMENT,
    `nome`                      varchar(150) DEFAULT NULL,
    `senha`                     varchar(255) DEFAULT NULL,
    `score`                     INT DEFAULT NULL,
    `usuario_pai_id`            INT,
    `login`                     varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS `usuario_aud` (
    `id` int NOT NULL,
    `rev` int NOT NULL,
    `revtype` tinyint DEFAULT NULL,
    `nome` varchar(150) DEFAULT NULL,
    `senha`                     varchar(255) DEFAULT NULL,
    `score`                     INT DEFAULT NULL,
    `usuario_pai_id`            INT,
    `login`                     varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`,`rev`),
    KEY `fkUserAudRev` (`rev`),
    CONSTRAINT `fkUserAudRev` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB;



INSERT INTO `usuario` (id, nome, senha, score, usuario_pai_id, login )
VALUES ('1', 'root', '$2a$10$eRfNsB9IPslohoxhvpr4QOq219PLHB9CXjYjZLjm4rT9kqLId95Ui',0, null, 'admin');