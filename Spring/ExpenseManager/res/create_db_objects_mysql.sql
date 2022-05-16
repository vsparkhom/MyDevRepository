/*
CREATE USER 'bestuser'@'localhost' IDENTIFIED BY 'bestuser';
GRANT ALL PRIVILEGES ON * . * TO 'bestuser'@'localhost';

CREATE DATABASE  exp_man_spring_db;
USE exp_man_spring_db;
*/

use exp_man_spring_db;

create table categories (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL, -- TODO: make unique
    category_limit FLOAT DEFAULT 0 -- TODO: rename to LIMIT
);


----- categories

INSERT INTO 
	categories(name, category_limit)
VALUES
	('Other', 500),
	('Monthly', 2500),
    ('Unknown', 500),
	('Test category 1', 1000), -- 4
	('Test category 2',  800), -- 5
	('Test category 3',  600), -- 6
	('Test category 4',  400), -- 7
	('Test category 5',  200), -- 8
	('Test category 6',  100); -- 9 (empty category)
