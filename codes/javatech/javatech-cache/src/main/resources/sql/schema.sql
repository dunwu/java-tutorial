-- -------------------------------------------
-- 运行本项目的 DDL 脚本
-- -------------------------------------------

-- 创建数据表 user
CREATE TABLE IF NOT EXISTS user (
    id      BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Id',
    name    VARCHAR(10)         NOT NULL DEFAULT '' COMMENT '用户名',
    age     TINYINT(3)          NOT NULL DEFAULT 0 COMMENT '年龄',
    address VARCHAR(32)         NOT NULL DEFAULT '' COMMENT '地址',
    email   VARCHAR(32)         NOT NULL DEFAULT '' COMMENT '邮件',
    PRIMARY KEY (id)
) COMMENT = '用户表';
