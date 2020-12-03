drop table if exists user;
CREATE TABLE `user`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `name`        varchar(64)         NOT NULL COMMENT 'user name',
    `password`    varchar(64)         NOT NULL,
    `create_time` datetime            NOT NULL,
    `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY `pk_id` (`id`),
    UNIQUE KEY `uk_username` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;