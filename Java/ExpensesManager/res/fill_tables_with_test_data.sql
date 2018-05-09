--- clear tables 

delete from expenses;
delete from expenses_mapping;
delete from categories;

----- categories

insert into categories(name, 'limit') values ('Unknown', 1000);
insert into categories(name, 'limit') values ('Other', 1000);
insert into categories(name, 'limit') values ('Test category 1', 1000);
insert into categories(name, 'limit') values ('Test category 2', 1000);
insert into categories(name, 'limit') values ('Test category 3', 1000);
insert into categories(name, 'limit') values ('Test category 4', 1000);
insert into categories(name, 'limit') values ('Test category 5', 1000);

----- expenses

--- January


insert into expenses(date, merchant, amount, category_id) values (datetime(1514832402, 'unixepoch'), 'Unknown expense 1', 10, 1);

insert into expenses(date, merchant, amount, category_id) values (datetime(1514832402, 'unixepoch'), 'Other expense 1', 10, 2);

insert into expenses(date, merchant, amount, category_id) values (datetime(1514832402, 'unixepoch'), 'Test Cat 1 expense 1', 10, 3);
insert into expenses(date, merchant, amount, category_id) values (datetime(1514832402, 'unixepoch'), 'Test Cat 2 expense 1', 10, 4);
insert into expenses(date, merchant, amount, category_id) values (datetime(1514832402, 'unixepoch'), 'Test Cat 3 expense 1', 10, 5);



--- February

insert into expenses(date, merchant, amount, category_id) values (datetime(1517510802, 'unixepoch'), 'Unknown expense 2', 20, 1);
insert into expenses(date, merchant, amount, category_id) values (datetime(1517510802, 'unixepoch'), 'Unknown expense 3', 30, 1);

insert into expenses(date, merchant, amount, category_id) values (datetime(1517510802, 'unixepoch'), 'Other expense 2', 20, 2);
insert into expenses(date, merchant, amount, category_id) values (datetime(1517510802, 'unixepoch'), 'Other expense 3', 30, 2);

insert into expenses(date, merchant, amount, category_id) values (datetime(1517510802, 'unixepoch'), 'Test Cat 1 expense 2', 20, 3);
insert into expenses(date, merchant, amount, category_id) values (datetime(1517510802, 'unixepoch'), 'Test Cat 1 expense 3', 30, 3);
insert into expenses(date, merchant, amount, category_id) values (datetime(1517510802, 'unixepoch'), 'Test Cat 2 expense 2', 20, 4);
insert into expenses(date, merchant, amount, category_id) values (datetime(1517510802, 'unixepoch'), 'Test Cat 2 expense 3', 30, 4);
insert into expenses(date, merchant, amount, category_id) values (datetime(1517510802, 'unixepoch'), 'Test Cat 3 expense 2', 20, 5);
insert into expenses(date, merchant, amount, category_id) values (datetime(1517510802, 'unixepoch'), 'Test Cat 3 expense 3', 30, 5);




--- Mar

insert into expenses(date, merchant, amount, category_id) values (datetime(1519930002, 'unixepoch'), 'Unknown expense 4', 40, 1);
insert into expenses(date, merchant, amount, category_id) values (datetime(1519930002, 'unixepoch'), 'Unknown expense 5', 50, 1);

insert into expenses(date, merchant, amount, category_id) values (datetime(1519930002, 'unixepoch'), 'Other expense 4', 40, 2);
insert into expenses(date, merchant, amount, category_id) values (datetime(1519930002, 'unixepoch'), 'Other expense 5', 50, 2);

insert into expenses(date, merchant, amount, category_id) values (datetime(1519930002, 'unixepoch'), 'Test Cat 1 expense 4', 40, 3);
insert into expenses(date, merchant, amount, category_id) values (datetime(1519930002, 'unixepoch'), 'Test Cat 1 expense 5', 50, 3);
insert into expenses(date, merchant, amount, category_id) values (datetime(1519930002, 'unixepoch'), 'Test Cat 2 expense 4', 40, 4);
insert into expenses(date, merchant, amount, category_id) values (datetime(1519930002, 'unixepoch'), 'Test Cat 2 expense 5', 50, 4);
insert into expenses(date, merchant, amount, category_id) values (datetime(1519930002, 'unixepoch'), 'Test Cat 3 expense 4', 40, 5);
insert into expenses(date, merchant, amount, category_id) values (datetime(1519930002, 'unixepoch'), 'Test Cat 3 expense 5', 50, 5);


--- Apr

--- Mar

insert into expenses(date, merchant, amount, category_id) values (datetime(1522603693, 'unixepoch'), 'Unknown expense 6', 60, 1);
insert into expenses(date, merchant, amount, category_id) values (datetime(1522603693, 'unixepoch'), 'Unknown expense 7', 70, 1);

insert into expenses(date, merchant, amount, category_id) values (datetime(1522603693, 'unixepoch'), 'Other expense 6', 60, 2);
insert into expenses(date, merchant, amount, category_id) values (datetime(1522603693, 'unixepoch'), 'Other expense 7', 70, 2);

insert into expenses(date, merchant, amount, category_id) values (datetime(1522603693, 'unixepoch'), 'Test Cat 1 expense 6', 60, 3);
insert into expenses(date, merchant, amount, category_id) values (datetime(1522603693, 'unixepoch'), 'Test Cat 1 expense 7', 70, 3);
insert into expenses(date, merchant, amount, category_id) values (datetime(1522603693, 'unixepoch'), 'Test Cat 2 expense 6', 60, 4);
insert into expenses(date, merchant, amount, category_id) values (datetime(1522603693, 'unixepoch'), 'Test Cat 2 expense 7', 70, 4);
insert into expenses(date, merchant, amount, category_id) values (datetime(1522603693, 'unixepoch'), 'Test Cat 3 expense 6', 60, 5);
insert into expenses(date, merchant, amount, category_id) values (datetime(1522603693, 'unixepoch'), 'Test Cat 3 expense 7', 70, 5);


--- now

insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Unknown expense 8', 80, 1);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Unknown expense 9', 90, 1);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Unknown expense 10', 100, 1);

insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Other expense 8', 80, 2);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Other expense 9', 90, 2);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Other expense 10', 100, 2);

insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Test Cat 1 expense 8', 80, 3);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Test Cat 1 expense 9', 90, 3);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Test Cat 1 expense 10', 100, 3);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Test Cat 2 expense 8', 80, 4);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Test Cat 2 expense 9', 90, 4);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Test Cat 2 expense 10', 100, 4);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Test Cat 3 expense 8', 80, 5);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Test Cat 3 expense 9', 90, 5);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Test Cat 3 expense 10', 100, 5);




----- expenses_mapping

insert into expenses_mapping values ('WWW.ALIEXPRESS.COM', 2);