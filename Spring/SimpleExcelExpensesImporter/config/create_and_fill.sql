
DROP TABLE Expenses;
DROP TABLE Cards;
DROP TABLE Categories;


CREATE TABLE Categories (
    id        INTEGER       PRIMARY KEY
                            NOT NULL
                            UNIQUE,
    name      VARCHAR (200) NOT NULL,
    parent_id               REFERENCES Categories (id) ON DELETE SET NULL,
    type      VARCHAR (50) 
);

insert into categories(id, name, parent_id, type) values (?, ?, ?, ?);

-- delete from categories;




CREATE TABLE Cards (
    id   INTEGER       PRIMARY KEY AUTOINCREMENT,
    name VARCHAR (200) NOT NULL,
    bank VARCHAR (200) NOT NULL,
    card_type VARCHAR (200) NOT NULL
);


insert into Cards(name, bank, card_type) values ('TdCredit', 'TD', 'CREDIT');
insert into Cards(name, bank, card_type) values ('TdDebit', 'TD', 'DEBIT');
insert into Cards(name, bank, card_type) values ('PcfCredit', 'PCF', 'CREDIT');
insert into Cards(name, bank, card_type) values ('CibcCredit', 'CIBC', 'CREDIT');




CREATE TABLE Expenses (
    id            INTEGER       PRIMARY KEY AUTOINCREMENT,
    merchant      VARCHAR (500),
    amount        DECIMAL,
    purchase_date DATE          NOT NULL,
    type          VARCHAR,
    category_id   INTEGER       REFERENCES Categories (id) ON DELETE SET NULL,
    card_id       INTEGER       REFERENCES Cards (id) 
);

insert into expenses(merchant, amount, purchase_date, type, category_id, card_id) values ('ENMAX ENERGY', 355.63, date('2024-12-01'), 'Unknown', 3, (select id from cards where name = 'TdCredit'));

-- delete from Expenses;

