CREATE TABLE  IF NOT EXISTS `revinfo` (
                                          `rev` int NOT NULL AUTO_INCREMENT,
                                          `revtstmp` bigint DEFAULT NULL,
                                          PRIMARY KEY (`rev`)
    ) ENGINE=InnoDB ;



CREATE TABLE IF NOT EXISTS `usuario` (
    `id` int NOT NULL,
    `nome`                      varchar(150) DEFAULT NULL,
    `senha`                     varchar(255) DEFAULT NULL,
    `score`                     decimal(10,2) DEFAULT NULL,
    `usuario_pai_id`            INT,
    `login`                     varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS `usuario_aud` (
    `id` int NOT NULL,
    `rev` int NOT NULL,
    `revtype` tinyint DEFAULT NULL,
    `nome` varchar(150) DEFAULT NULL,
    `senha`                     varchar(20) DEFAULT NULL,
    `score`                     decimal(10,2) DEFAULT NULL,
    `usuario_pai_id`            INT,
    `login`                     varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`,`rev`),
    KEY `fkUserAudRev` (`rev`),
    CONSTRAINT `fkUserAudRev` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS `colaborador`
(
    `id`                        int    NOT NULL AUTO_INCREMENT,
    `nome`                      varchar(255) DEFAULT NULL,
    `senha`                     varchar(255) DEFAULT NULL,
    `score`                     decimal(10,2) DEFAULT NULL,
    `colaborador_pai_id`        INT,
    PRIMARY KEY (`id`),
    FOREIGN KEY (colaborador_pai_id) REFERENCES colaborador(id)
    ) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `colaborador_aud`
(
    `id`                      int NOT NULL,
    `rev`                     int NOT NULL,
    `revtype`                 tinyint      DEFAULT NULL,
    `nome`                      varchar(255) DEFAULT NULL,
    `senha`                     varchar(255) DEFAULT NULL,
    `score`                     decimal(10,2) DEFAULT NULL,
    `colaborador_pai_id`        INT,
    PRIMARY KEY (`id`, `rev`),
    KEY `fkUserAudRev` (`rev`),
    CONSTRAINT `fkColaboradorAudRev` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
    ) ENGINE = InnoDB;

INSERT INTO `usuario` (id, nome, senha, score, usuario_pai_id, login )
VALUES ('1', 'root', '$2a$10$yUyq/GhQEs9gWbm/U90Fku17TNmdwfYjQ/iyAA00dI9qAAqoXUPR2',0, null, 'admin');