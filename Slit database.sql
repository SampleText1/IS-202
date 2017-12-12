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

CREATE TABLE uploads (
  contact_id int(11) NOT NULL AUTO_INCREMENT,
  first_name varchar(45) DEFAULT NULL,
  last_name varchar(45) DEFAULT NULL,
  photo mediumblob,
  PRIMARY KEY (contact_id)
);



