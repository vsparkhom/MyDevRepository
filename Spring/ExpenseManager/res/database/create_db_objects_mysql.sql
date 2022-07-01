/*
CREATE USER 'vlpa_user'@'localhost' IDENTIFIED BY 'vlpa_user';
GRANT ALL PRIVILEGES ON * . * TO 'vlpa_user'@'localhost';

CREATE DATABASE  exp_man_spring_db;
USE exp_man_spring_db;
*/

USE exp_man_spring_db;

DROP TABLE expenses;
DROP TABLE categories;

CREATE TABLE categories (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL, -- TODO: make unique
    category_limit FLOAT DEFAULT 0 -- TODO: rename to LIMIT
);

CREATE TABLE expenses (
    id INT NOT NULL AUTO_INCREMENT,
    date DATETIME,
    merchant VARCHAR(255) NOT NULL,
    amount FLOAT DEFAULT 0,
    bank VARCHAR(100),
    description VARCHAR(255),
    category_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

/*
create table expense_pattern_types (
    id integer primary key not null,
    name text
);

insert into expense_pattern_types(name) values('REGULAR');
insert into expense_pattern_types(name) values('SKIP');
insert into expense_pattern_types(name) values('AMOUNT_BASED');

create table expense_patterns (
    id integer primary key not null,
    pattern text,
    category_id integer,
    type_id integer,
    priority integer default '10', -- MEDIUM
    amount float default '0',
    foreign key (category_id) references categories(id),
    foreign key (type_id) references expense_pattern_types(id)
);

create table import_history (
    date date null,
    message text null
);
*/