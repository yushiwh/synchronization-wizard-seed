CREATE TABLE `test_user`
(
    `id`        BIGINT      NOT NULL DEFAULT 0 COMMENT 'id',
    `user_name` VARCHAR(50) NOT NULL COMMENT '用户名',
    `password`  VARCHAR(50) NOT NULL COMMENT '密码',
    PRIMARY KEY (`id`)
)
    COMMENT ='测试的表'
    COLLATE = 'utf8_general_ci'
    ENGINE = InnoDB
;


-- nick 20190617

CREATE TABLE `user`
(
    `id`            BIGINT(20)  NOT NULL DEFAULT '0' COMMENT '编号',
    `user_agent_id` BIGINT(20)  NOT NULL COMMENT '外部用户编号',
    `login_name`    VARCHAR(50) NOT NULL COMMENT '登录名',
    `login_pwd`     VARCHAR(50) NULL COMMENT '登录密码',
    `login_pwd_jm`  VARCHAR(50) NULL COMMENT '加密密码',
    `status`        INT(11)     NOT NULL DEFAULT '1' COMMENT '状态 0：停用 1：正常',
    `link_name`     VARCHAR(50) NULL     DEFAULT NULL COMMENT '连接名',
    `link_phone`    VARCHAR(50) NULL     DEFAULT NULL COMMENT '外部电话',
    `create_at`     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
)
    COMMENT ='用户表，对应三方药店的用户'
    COLLATE = 'utf8_general_ci'
    ENGINE = InnoDB
;



-- *****************************  shiro  开始  不用上线 ***************************************
CREATE TABLE `jwtrole`
(
    `id`         INT(11)     NOT NULL AUTO_INCREMENT,
    `role`       VARCHAR(50) NULL DEFAULT NULL COMMENT '角色',
    `permission` VARCHAR(50) NULL DEFAULT NULL COMMENT '权限',
    PRIMARY KEY (`id`)
)
    COLLATE = 'utf8_general_ci'
    ENGINE = InnoDB
    AUTO_INCREMENT = 5
;
CREATE TABLE `jwtuser`
(
    `id`         INT(11)     NOT NULL AUTO_INCREMENT,
    `username`   VARCHAR(50) NULL DEFAULT NULL COMMENT '用户名',
    `password`   VARCHAR(50) NULL DEFAULT NULL COMMENT '密码',
    `role`       VARCHAR(50) NULL DEFAULT NULL COMMENT '角色',
    `permission` VARCHAR(50) NULL DEFAULT NULL COMMENT '权限',
    `ban`        INT(11)     NULL DEFAULT '0' COMMENT '状态 0:正常  1：禁用',
    PRIMARY KEY (`id`)
)
    COLLATE = 'utf8_general_ci'
    ENGINE = InnoDB
    AUTO_INCREMENT = 7
;


INSERT INTO `jwtuser` (`username`, `password`, `role`, `permission`, `ban`)
VALUES ('howie', '1234567', 'admin', 'normal', 0);
INSERT INTO `jwtuser` (`username`, `password`, `role`, `permission`, `ban`)
VALUES ('ys', '123456789', 'user', 'normal', 0);
INSERT INTO `jwtuser` (`username`, `password`, `role`, `permission`, `ban`)
VALUES ('kobe', '112233', 'user', 'vip', 0);



INSERT INTO `jwtrole` (`role`, `permission`)
VALUES ('user', 'normal');
INSERT INTO `jwtrole` (`role`, `permission`)
VALUES ('admin', 'vip');
--  **************************** shiro完成 **************************************************
