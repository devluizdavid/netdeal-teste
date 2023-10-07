CREATE TABLE  IF NOT EXISTS `revinfo` (
                                          `rev` int NOT NULL AUTO_INCREMENT,
                                          `revtstmp` bigint DEFAULT NULL,
                                          PRIMARY KEY (`rev`)
    ) ENGINE=InnoDB ;


CREATE TABLE IF NOT EXISTS `role` (
    `name_role` varchar(255) NOT NULL,
    PRIMARY KEY (`name_role`)
    ) ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS `role_aud` (
    `name_role` varchar(255) NOT NULL,
    `rev` int NOT NULL,
    `revtype` tinyint DEFAULT NULL,
    PRIMARY KEY (`name_role`,`rev`),
    KEY `fkRoleRev` (`rev`),
    CONSTRAINT `fkRoleRev` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
    ) ENGINE=InnoDB ;



CREATE TABLE IF NOT EXISTS `user` (
                                      `id` int NOT NULL,
                                      `account_non_expired` bit(1) NOT NULL,
    `account_non_locked` bit(1) NOT NULL,
    `credentials_non_expired` bit(1) NOT NULL,
    `enabled` bit(1) NOT NULL,
    `password` varchar(255) DEFAULT NULL,
    `user_name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS `user_aud` (
                                          `id` int NOT NULL,
                                          `rev` int NOT NULL,
                                          `revtype` tinyint DEFAULT NULL,
                                          `account_non_expired` bit(1) DEFAULT NULL,
    `account_non_locked` bit(1) DEFAULT NULL,
    `credentials_non_expired` bit(1) DEFAULT NULL,
    `enabled` bit(1) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `user_name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`,`rev`),
    KEY `fkUserAudRev` (`rev`),
    CONSTRAINT `fkUserAudRev` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
    ) ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS `user_role` (
                                           `user_id` int NOT NULL,
                                           `role_id` varchar(255) NOT NULL,
    KEY `fkUserRoleRoleId` (`role_id`),
    KEY `fkUserRoleUserId` (`user_id`),
    CONSTRAINT `fkUserRoleUserId` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    CONSTRAINT `fkUserRoleRoleId` FOREIGN KEY (`role_id`) REFERENCES `role` (`name_role`)
    ) ENGINE=InnoDB ;



CREATE TABLE IF NOT EXISTS `user_role_aud` (
                                               `rev` int NOT NULL,
                                               `user_id` int NOT NULL,
                                               `role_id` varchar(255) NOT NULL,
    `revtype` tinyint DEFAULT NULL,
    PRIMARY KEY (`rev`,`user_id`,`role_id`),
    CONSTRAINT `fkUserRoleRev` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
    ) ENGINE=InnoDB ;

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

INSERT INTO `user` (id, account_non_expired, account_non_locked, credentials_non_expired, enabled, password, user_name)
VALUES ('1', false, false, false, false,  '$2a$10$yUyq/GhQEs9gWbm/U90Fku17TNmdwfYjQ/iyAA00dI9qAAqoXUPR2','teste');