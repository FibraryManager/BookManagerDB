DROP DATABASE IF EXISTS `bookmanager`;
CREATE DATABASE bookmanager;
USE bookmanager;

DROP TABLE IF EXISTS `user`;

CREATE TABLE user
(
    user_id      INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    password     VARCHAR(256)       NOT NULL COMMENT '密码',
    nickname     VARCHAR(15)        NOT NULL DEFAULT '' COMMENT '名字',
    phone_number VARCHAR(11) UNIQUE NOT NULL DEFAULT '' COMMENT '手机号',
    type         CHAR(1)            NOT NULL DEFAULT '0' CHECK (type in ('1', '2', '3')) COMMENT '角色类型 1图书借阅者 2图书管理员 3系统管理员'
) comment '用户';

DROP TABLE IF EXISTS `book`;

create table book
(
    id         INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '图书号',
    book_id               VARCHAR(256) NOT NULL COMMENT '图书编号',
    book_name         VARCHAR(256) NOT NULL COMMENT '书籍名称',
    status            Int NOT NULL COMMENT '出借状态',
    classification_id INT          NOT NULL COMMENT '分类id',
    book_author       VARCHAR(256) NOT NULL COMMENT '作者姓名',
    publisher         VARCHAR(256) NOT NULL COMMENT '出版社',
    pb_time           DATETIME     NOT NULL COMMENT '出版时间',
    time              DATETIME     NOT NULL DEFAULT NOW() COMMENT '入库时间'
);

DROP TABLE IF EXISTS `classify`;

create table classify
(
    id            INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '借阅编号',
    classificaton VARCHAR(50) null comment '分类名称'
);

DROP TABLE IF EXISTS `borrow`;

create table borrow
(
    id                INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '借阅编号',
    book_id           INT UNSIGNED NOT NULL COMMENT '图书号',
    book_name         VARCHAR(256) NOT NULL COMMENT '书籍名称',
    borrow_time       DATETIME     NOT NULL DEFAULT NOW() COMMENT '借出时间',
    return_time       DATETIME     NOT NULL COMMENT '归还时间',
    user_id           INT UNSIGNED NOT NULL COMMENT '用户id',
    classification_id INT UNSIGNED NOT NULL COMMENT '分类id',
    FOREIGN KEY (book_id) REFERENCES book (id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON UPDATE CASCADE ON DELETE CASCADE
);
