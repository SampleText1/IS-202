drop table userAccount;
drop database Slit;

create database Slit;
use Slit;

create table userAccount(
	id int auto_increment primary key,
    firstName varchar(25),
    lastName varchar(25),
    email varchar(40),
    pass varchar(30)
);


create table module(
	id int primary key,
    title varchar(75),
    description varchar(250),
    goals varchar(250),
    resources varchar(250),
    task varchar(250),
    deadline date
);

insert into useraccount(id, firstname)
values(1, 'Marius');
 select * from userAccount;
 

