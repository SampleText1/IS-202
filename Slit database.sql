create database Slit;
use Slit;

create table userAccount(
	id int auto_increment primary key,
    firstName varchar(25),
    lastName varchar(25),
    email varchar(40),
    pass varchar(30)
);


create table modules(
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

use slit;

CREATE TABLE users (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

insert into users(username, password)
values('vegard2', 'passord22');

select * from userAccount;

delete from userAccount;
where id = 1;


