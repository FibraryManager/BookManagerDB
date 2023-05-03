insert into user(password,nickname,phone_number,type)
VALUES ('admin','admin','11111111111','3');
insert into user (password, nickname, phone_number, type)
values ('123456','ly','17878282','1');
insert into user (password, nickname, phone_number, type)
values ('pai','pai','18778569923','2');

insert into book ( id,isbn, book_name, status,classification_id, book_author, publisher, pb_time, image_url)
values (1,'978-7-5192-4411-8','考研词汇闪过',1,1,'陈康宁','世界图书出版公司','2018-03-01', 'https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c94f4f5c114a4bf4b5f40ae5a8d6b805~tplv-k3u1fbpfcp-zoom-1.png');
insert into book ( id,isbn, book_name,status, classification_id, book_author, publisher, pb_time, image_url)
values (2,'978-7-5192-4411-8','考研词汇闪过',0,1,'陈康宁','世界图书出版公司','2018-03-01', 'https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c94f4f5c114a4bf4b5f40ae5a8d6b805~tplv-k3u1fbpfcp-zoom-1.png');
insert into book ( id,isbn, book_name,status, classification_id, book_author, publisher, pb_time, image_url)
values (3,'978-7-5192-4411-8','考研词汇闪过',0,1,'陈康宁','世界图书出版公司','2018-03-01', 'https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c94f4f5c114a4bf4b5f40ae5a8d6b805~tplv-k3u1fbpfcp-zoom-1.png');
insert into book (id, isbn, book_name,status, classification_id, book_author, publisher, pb_time, image_url)
values (4,'978-7-5192-4411-8','考研词汇闪过',0,1,'陈康宁','世界图书出版公司','2018-03-01', 'https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c94f4f5c114a4bf4b5f40ae5a8d6b805~tplv-k3u1fbpfcp-zoom-1.png');
insert into book (id, isbn, book_name,status, classification_id, book_author, publisher, pb_time, image_url)
values (5,'222-2222-2222-2','测试',0,4,'派大星','测试测试','2018-03-01', 'https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c94f4f5c114a4bf4b5f40ae5a8d6b805~tplv-k3u1fbpfcp-zoom-1.png');
insert into book (id, isbn, book_name,status, classification_id, book_author, publisher, pb_time, image_url)
values (6,'222-2222-2222-1','测试2',0,4,'派大星','测试测试','2018-03-01', 'https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c94f4f5c114a4bf4b5f40ae5a8d6b805~tplv-k3u1fbpfcp-zoom-1.png');

insert into borrow (book_id, book_name, return_time, user_id, nickname, classification_id)
values (1,'考研词汇闪过','2023-05-08',1 ,'yunduo' ,1);
insert into borrow (book_id, book_name, return_time, user_id, nickname, classification_id)
values (2,'考研词汇闪过','2023-05-08',1 ,'yunduo' ,1);

insert into classify (id, classificaton)
values (1,'科学与技术');
insert into classify (id, classificaton)
values (2,'人文科学');
insert into classify (id, classificaton)
values (3,'电子信息');
insert into classify (id, classificaton)
values (4,'儿童读物');
insert into classify (id, classificaton)
values (5,'社会科学');
insert into classify (id, classificaton)
values (6,'摄影绘画集');
insert into classify (id, classificaton)
values (7,'数学类');
insert into classify (id, classificaton)
values (8,'历史类');