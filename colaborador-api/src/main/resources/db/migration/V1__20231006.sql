CREATE TABLE `REVINFO`
(
    `REV`      int NOT NULL AUTO_INCREMENT,
    `REVTSTMP` bigint DEFAULT NULL,
    PRIMARY KEY (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `usuario`
(
    `id`             bigint       NOT NULL AUTO_INCREMENT,
    `login`          varchar(255) NOT NULL,
    `nome`           varchar(255) NOT NULL,
    `hierarquia`     varchar(255) ,
    `score`          int    DEFAULT NULL,
    `senha`          varchar(255) NOT NULL,
    `usuario_pai_id` bigint DEFAULT NULL,

    PRIMARY KEY (`id`),
    KEY              `FKqlo0ldasa8cv21dyv0y5coyvx` (`usuario_pai_id`),
    UNIQUE KEY `id_UNIQUE` (`login`),
    CONSTRAINT `FKqlo0ldasa8cv21dyv0y5coyvx` FOREIGN KEY (`usuario_pai_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `usuario_AUD`
(
    `id`             bigint       NOT NULL,
    `REV`            int          NOT NULL,
    `REVTYPE`        tinyint      DEFAULT NULL,
    `login`          varchar(255) DEFAULT NULL,
    `nome`           varchar(255) DEFAULT NULL,
    `hierarquia`     varchar(255) ,
    `score`          int          DEFAULT NULL,
    `senha`          varchar(255) DEFAULT NULL,
    `usuario_pai_id` bigint       DEFAULT NULL,
    PRIMARY KEY (`id`, `REV`),
    KEY              `FKccqpbcawc1yublnm3f1c0q8ie` (`REV`),
    CONSTRAINT `FKccqpbcawc1yublnm3f1c0q8ie` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



INSERT INTO `usuario` (id, nome, senha, score, usuario_pai_id, login, hierarquia)
VALUES ('1', 'root', '$2a$10$eRfNsB9IPslohoxhvpr4QOq219PLHB9CXjYjZLjm4rT9kqLId95Ui', 0, null, 'admin', 1);