drop database if exists git;
create database git;

use git;

create table usuario (id int primary key auto_increment, login varchar (100) unique, senha varchar (255) not null);

insert into usuario values (10,'lala@gmail.com',md5('123'));
insert into usuario values (12,'hugo@gmail.com',md5('123'));
insert into usuario values (13,'jose@gmail.com',md5('123'));
insert into usuario values (14,'maria@gmail.com',md5('123'));

select * from usuario;

create table produto (codigo int primary key auto_increment, nome varchar (100), preco double);

insert into produto values (100,'iphone',100);
insert into produto values (101,'samsung',200);
insert into produto values (102,'moto',300);

select * from produto;
