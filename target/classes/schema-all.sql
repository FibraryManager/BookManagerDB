DROP DATABASE IF EXISTS `bookmanager`;
CREATE DATABASE bookmanager;
USE bookmanager;

CREATE TABLE user(
    user_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    password VARCHAR(256) NOT NULL COMMENT '密码',
    nickname VARCHAR(15) NOT NULL DEFAULT '' COMMENT '名字',
    phone_number VARCHAR(11) UNIQUE NOT NULL DEFAULT '' COMMENT '手机号',
    type CHAR(1) NOT NULL DEFAULT '0' CHECK(type in ('1','2','3')) COMMENT '角色类型 1图书借阅者 2图书管理员 3系统管理员'
) comment '用户';

