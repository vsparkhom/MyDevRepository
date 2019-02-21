--- clear tables 

delete from expenses;
delete from expense_patterns;
delete from categories;

----- categories

/* 1 */  insert into categories(name, 'limit') values ('Unknown', 1000);
/* 2 */  insert into categories(name, 'limit') values ('Food', 800);
/* 3 */  insert into categories(name, 'limit') values ('Home', 200);
/* 4 */  insert into categories(name, 'limit') values ('Alcohol', 150);
/* 5 */  insert into categories(name, 'limit') values ('Clothes', 150);
/* 6 */  insert into categories(name, 'limit') values ('Lina', 200);
/* 7 */  insert into categories(name, 'limit') values ('Vova', 100);
/* 8 */  insert into categories(name, 'limit') values ('Nicole', 200);
/* 9 */  insert into categories(name, 'limit') values ('Dog', 200);
/* 10 */ insert into categories(name, 'limit') values ('Entertainment', 200);
/* 11 */ insert into categories(name, 'limit') values ('Other', 500);
/* 12 */ insert into categories(name, 'limit') values ('Permanent', 2500);

----- expenses (example)

-- insert into expenses(date, merchant, amount, category_id) values (date('now','-4 months'), 'Unknown expense 1', 10, 1);

----- expense_patterns

-- 2 - Food
insert into expense_patterns(pattern, category_id, type_id) values ('%SAVE%ON%FOODS%', 2, 1);
insert into expense_patterns(pattern, category_id, type_id) values ('%REAL%CANADIAN%SUPERSTO%', 2, 1);
insert into expense_patterns(pattern, category_id, type_id) values ('%SOBEYS%MAHOGANY%', 2, 1);
insert into expense_patterns(pattern, category_id, type_id) values ('%EUROPEAN%DELI%', 2, 1);
insert into expense_patterns(pattern, category_id, type_id) values ('%COSTCO%WHOLESAL%', 2, 1);

-- 3 - Home
insert into expense_patterns(pattern, category_id, type_id) values ('%THE%HOME%DEPOT%', 3, 1);
insert into expense_patterns(pattern, category_id, type_id) values ('%WAL-MART%', 3, 1);
insert into expense_patterns(pattern, category_id, type_id) values ('%SHOPPERS%DRUG%', 3, 1);
insert into expense_patterns(pattern, category_id, type_id) values ('%IKEA%CALGARY%', 3, 1);

-- 4 - Alcohol
insert into expense_patterns(pattern, category_id, type_id) values ('%HIGHLANDER%WINE%', 4, 1);
insert into expense_patterns(pattern, category_id, type_id) values ('%SOBEYS%LIQUOR%', 4, 1);
insert into expense_patterns(pattern, category_id, type_id) values ('%REAL%CANADIAN%LIQUOR%', 4, 1);

-- 5 - Clothes
insert into expense_patterns(pattern, category_id, type_id) values ('%MARSHALLS%', 5, 1);
insert into expense_patterns(pattern, category_id, type_id) values ('%WINNERS%', 5, 1);
insert into expense_patterns(pattern, category_id, type_id) values ('%SPORT%CHEK%', 5, 1);

-- 7 - Vova
insert into expense_patterns(pattern, category_id, type_id) values ('%SOCCERGENIUS%', 7, 1);

-- 8 - Nicole
insert into expense_patterns(pattern, category_id, type_id) values ('%TOYS%R%US%', 8, 1);
insert into expense_patterns(pattern, category_id, type_id) values ('%TREEHOUSE%', 8, 1);

-- 9 - Dog
insert into expense_patterns(pattern, category_id, type_id) values ('%PET%PLANET%', 9, 1);

-- 10 - Entertainment
insert into expense_patterns(pattern, category_id, type_id) values ('%THE%CALGARY%ZOO%', 10, 1);
insert into expense_patterns(pattern, category_id, type_id) values ('%GOOD%EARTH%COFFEEHOUSE%', 10, 1);
insert into expense_patterns(pattern, category_id, type_id) values ('%EDO%JAPAN%', 10, 1);
insert into expense_patterns(pattern, category_id, type_id) values ('%PIZZA%73%', 10, 1);

-- 11 - Other
insert into expense_patterns(pattern, category_id, type_id) values ('%SEND%E-TFR%', 11, 1);
insert into expense_patterns(pattern, category_id, type_id) values ('%VMT_%', 11, 1);

-- 12 - Permanent
insert into expense_patterns(pattern, category_id, type_id) values ('%KOODO MOBILE%', 12, 3);
insert into expense_patterns(pattern, category_id, type_id) values ('%1786%GB%CALGARY%SETON%', 12, 3);
insert into expense_patterns(pattern, category_id, type_id) values ('%SHAW%CABLESYSTE%', 12, 3);
insert into expense_patterns(pattern, category_id, type_id) values ('%INTACT%INS%CO%', 12, 3);
insert into expense_patterns(pattern, category_id, type_id) values ('%VW%CREDIT%CAN%%%LOAN%', 12, 3);
insert into expense_patterns(pattern, category_id, type_id) values ('%MONTHLY%ACCOUNT%FEE%', 12, 3);
insert into expense_patterns(pattern, category_id, type_id, amount) values ('%SEND%E-TFR%', 12, 3, 1500.00);

-- SKIP PATTERNS
insert into expense_patterns(pattern, category_id, type_id) values ('%TD%VISA%', 1, 2);
insert into expense_patterns(pattern, category_id, type_id) values ('%PC%MASTRCRD%', 1, 2);


