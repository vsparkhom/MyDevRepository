--- clear tables 

delete from expenses;
delete from expense_patterns;
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

--- 4 months ago

insert into expenses(date, merchant, amount, category_id) values (date('now','-4 months'), 'Unknown Expense 1', 10, 1);

insert into expenses(date, merchant, amount, category_id) values (date('now','-4 months'), 'Other Expense 1', 10, 2);

insert into expenses(date, merchant, amount, category_id) values (date('now','-4 months'), 'Test Expense 1.1', 10, 3);
insert into expenses(date, merchant, amount, category_id) values (date('now','-4 months'), 'Test Expense 2.1', 10, 4);
insert into expenses(date, merchant, amount, category_id) values (date('now','-4 months'), 'Test Expense 3.1', 10, 5);

--- 3 months ago

insert into expenses(date, merchant, amount, category_id) values (date('now','-3 months'), 'Unknown Expense 2', 20, 1);
insert into expenses(date, merchant, amount, category_id) values (date('now','-3 months'), 'Unknown Expense 3', 30, 1);

insert into expenses(date, merchant, amount, category_id) values (date('now','-3 months'), 'Other Expense 2', 20, 2);
insert into expenses(date, merchant, amount, category_id) values (date('now','-3 months'), 'Other Expense 3', 30, 2);

insert into expenses(date, merchant, amount, category_id) values (date('now','-3 months'), 'Test Expense 1.2', 20, 3);
insert into expenses(date, merchant, amount, category_id) values (date('now','-3 months'), 'Test Expense 1.3', 30, 3);
insert into expenses(date, merchant, amount, category_id) values (date('now','-3 months'), 'Test Expense 2.2', 20, 4);
insert into expenses(date, merchant, amount, category_id) values (date('now','-3 months'), 'Test Expense 2.3', 30, 4);
insert into expenses(date, merchant, amount, category_id) values (date('now','-3 months'), 'Test Expense 3.2', 20, 5);
insert into expenses(date, merchant, amount, category_id) values (date('now','-3 months'), 'Test Expense 3.3', 30, 5);

--- 2 months ago

insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Unknown Expense 4', 40, 1);
insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Unknown Expense 5', 50, 1);

insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Other Expense 4', 40, 2);
insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Other Expense 5', 50, 2);

insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Test Expense 1.4', 40, 3);
insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Test Expense 1.5', 50, 3);
insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Test Expense 2.4', 40, 4);
insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Test Expense 2.5', 50, 4);
insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Test Expense 3.4', 40, 5);
insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Test Expense 3.5', 50, 5);

--- 1 months ago

insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Unknown Expense 6', 60, 1);
insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Unknown Expense 7', 70, 1);

insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Other Expense 6', 60, 2);
insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Other Expense 7', 70, 2);

insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Test Expense 1.6', 60, 3);
insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Test Expense 1.7', 70, 3);
insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Test Expense 2.6', 60, 4);
insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Test Expense 2.7', 70, 4);
insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Test Expense 3.6', 60, 5);
insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Test Expense 3.7', 70, 5);

--- now

insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Unknown Expense 8', 80, 1);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Unknown Expense 9', 90, 1);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Unknown Expense 10', 100, 1);

insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Other Expense 8', 80, 2);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Other Expense 9', 90, 2);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Other Expense 10', 100, 2);

insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Test Expense 1.8', 80, 3);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Test Expense 1.9', 90, 3);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Test Expense 1.10', 100, 3);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Test Expense 2.8', 80, 4);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Test Expense 2.9', 90, 4);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Test Expense 2.10', 100, 4);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Test Expense 3.8', 80, 5);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Test Expense 3.9', 90, 5);
insert into expenses(date, merchant, amount, category_id) values (date('now'), 'Test Expense 3.10', 100, 5);




----- expense_patterns

insert into expense_patterns(pattern, category_id, type_id) values ('%TEST%EXPENSE 1%', 3, 1);
insert into expense_patterns(pattern, category_id, type_id) values ('%TEST%EXPENSE 2%', 4, 1);
insert into expense_patterns(pattern, category_id, type_id) values ('%TEST%EXPENSE 3%', 5, 1);
insert into expense_patterns(pattern, category_id, type_id) values ('%TEST%EXPENSE 4%', 6, 1);

insert into expense_patterns(pattern, category_id, type_id, priority) values ('%SKIP%EXPENSE 1%', 2, 2, 100);
insert into expense_patterns(pattern, category_id, type_id, priority) values ('%SKIP%EXPENSE 2%', 2, 2, 100);

insert into expense_patterns(pattern, category_id, type_id, priority) values ('%AMOUNT%EXPENSE 1%', 2, 3, 1000, 1500.0);
