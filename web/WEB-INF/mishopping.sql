-- we don't know how to generate schema mishopping (class Schema) :(
create table address
(
	aid int auto_increment
		primary key,
	uid int not null,
	addr varchar(200) not null,
	aphone varchar(11) not null
)
;

create table admin
(
	adminid int auto_increment
		primary key,
	adminname varchar(20) not null,
	adminpassword varchar(100) not null,
	constraint admin_adminname_uindex
		unique (adminname)
)
;

create table category
(
	categoryid int auto_increment
		primary key,
	categoryname varchar(50) not null,
	constraint category_categoryname_uindex
		unique (categoryname)
)
comment '类别表'
;

create table collection
(
	clid int auto_increment comment '收藏id'
		primary key,
	uid int not null,
	pid int not null
)
comment '收藏表'
;

create table `order`
(
	oid int auto_increment
		primary key,
	onum varchar(100) not null,
	ostate int default '0' null comment '0:未发货
1:已发货
2:已签收',
	ocreatetime varchar(20) not null,
	oupdatetime varchar(20) not null,
	constraint order_onum_uindex
		unique (onum)
)
comment '订单详情表'
;

create table products
(
	pid int auto_increment
		primary key,
	pname varchar(50) not null,
	pprice double not null,
	pstock int default '0' null,
	pdes text null,
	pimg varchar(200) null,
	pstate int default '1' not null comment '0:下架
1:上架',
	categoryid int null,
	ppricediscount double not null
)
comment '商品表'
;

create table productsorder
(
	poid int auto_increment comment '商品订单id'
		primary key,
	pid int not null comment '商品编号',
	oid int not null comment '订单id',
	pnum int not null comment '商品数量'
)
comment '商品订单表'
;

create table users
(
	uid int not null comment '用户id'
		primary key,
	uname varchar(20) not null comment '用户名',
	upassword varchar(100) not null comment '用户密码',
	nickname varchar(20) not null comment '用户昵称',
	uphone varchar(11) not null comment '用户手机号',
	ustate int default '1' null comment '0:锁定（不可登录）
1:正常',
	constraint users_username_uindex
		unique (uname),
	constraint users_phone_uindex
		unique (uphone)
)
comment '用户表'
;

