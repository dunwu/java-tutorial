-- -------------------------------------------------------------------
-- 运行本项目的初始化 DDL 脚本
-- Mysql 知识点可以参考：
-- https://dunwu.github.io/db-tutorial/#/sql/mysql/README
-- -------------------------------------------------------------------

-- 强制新建用户表
DROP TABLE IF EXISTS `testuser`;
CREATE TABLE `testuser` (
    `id`   BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;
