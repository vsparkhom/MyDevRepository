
use exp_man_spring_db;

/*
------------------------ clear tables 
*/

delete from expenses;
-- delete from expense_patterns;
delete from categories;

/*
------------------------ categories
*/

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
;

/*
------------------------ expense_patterns
*/

-- insert into expense_patterns(pattern, category_id, type_id) values ('%TEST%EXPENSE 1%', 4, 1);
-- insert into expense_patterns(pattern, category_id, type_id) values ('%TEST%EXPENSE 2%', 5, 1);
-- insert into expense_patterns(pattern, category_id, type_id) values ('%TEST%EXPENSE 3%', 6, 1);
-- insert into expense_patterns(pattern, category_id, type_id) values ('%TEST%EXPENSE 4%', 7, 1);
-- insert into expense_patterns(pattern, category_id, type_id) values ('%TEST%EXPENSE 5%', 8, 1);

-- insert into expense_patterns(pattern, category_id, type_id, priority) values ('%SKIP%EXPENSE 1%', 1, 2, 100);
-- insert into expense_patterns(pattern, category_id, type_id, priority) values ('%SKIP%EXPENSE 2%', 1, 2, 100);

-- insert into expense_patterns(pattern, category_id, type_id, priority, amount) values ('%AMOUNT%EXPENSE 1%', 2, 3, 1000, 1500.0);



/*
------------------------ expenses
*/

/*
month 1
*/

INSERT INTO 
    expenses(date, merchant, amount, category_id)
VALUES
    (NOW(), 'Other Expense 1.1',   125,  1),
    (NOW(), 'Other Expense 1.1',   125,  1),
    (NOW(), 'Monthly 1.1',         2250, 2),
    (NOW(), 'Unknown Expense 1.1', 50,   3),
    /* Test category 1 */
    (NOW(), 'Test Expense 1.1.1', 10, 4),
    (NOW(), 'Test Expense 1.1.2', 25, 4),
    (NOW(), 'Test Expense 1.1.3', 65, 4),
    /* Test category 2 */
    (NOW(), 'Test Expense 1.2.1', 100, 5),
    (NOW(), 'Test Expense 1.2.2', 250, 5),
    (NOW(), 'Test Expense 1.2.3', 50,  5),
    /* Test category 3 */
    (NOW(), 'Test Expense 1.3.1', 100, 6),
    (NOW(), 'Test Expense 1.3.2', 180, 6),
    (NOW(), 'Test Expense 1.3.3', 200, 6),
    /* Test category 4 */
    (NOW(), 'Test Expense 1.4.1', 125, 7),
    (NOW(), 'Test Expense 1.4.2', 80,  7),
    (NOW(), 'Test Expense 1.4.3', 195, 7),
    /* Test category 5 */
    (NOW(), 'Test Expense 1.5.1', 35,  8),
    (NOW(), 'Test Expense 1.5.2', 75,  8),
    (NOW(), 'Test Expense 1.5.3', 120, 8)
;


/*
month 2
*/

-- insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Other Expense 2.1',   135,  1);
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Monthly 2.1',         2300, 2);
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Unknown Expense 2.1', 60,   3);

-- insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Test Expense 2.1.1', 35, 4); -- Test category 1
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Test Expense 2.1.2', 75, 4);
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Test Expense 2.1.3', 10, 4);

-- insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Test Expense 2.2.1', 135, 5); -- Test category 2
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Test Expense 2.2.2', 75,  5);
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Test Expense 2.2.3', 206, 5);

-- insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Test Expense 2.3.1', 155, 6); -- Test category 3
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Test Expense 2.3.2', 175, 6);
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Test Expense 2.3.3', 162, 6);

-- insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Test Expense 2.4.1', 99,  7); -- Test category 4
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Test Expense 2.4.2', 131, 7);
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Test Expense 2.4.3', 178, 7);

-- insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Test Expense 2.5.1', 33,  8); -- Test category 5
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Test Expense 2.5.2', 77,  8);
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-1 months'), 'Test Expense 2.5.3', 124, 8);




/*
month 3
*/

-- insert into expenses(date, m erchant, amount, category_id) values (date('now','-2 months'), 'Other Expense 3.1',   115,  1);
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Monthly 3.1',         2200, 2);
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Unknown Expense 3.1', 40,   3);

-- insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Test Expense 3.1.1', 15, 4); -- Test category 1
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Test Expense 3.1.2', 40, 4);
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Test Expense 3.1.3', 25, 4);

-- insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Test Expense 3.2.1', 215, 5); -- Test category 2
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Test Expense 3.2.2', 45,  5);
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Test Expense 3.2.3', 124, 5);

-- insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Test Expense 3.3.1', 135, 6); -- Test category 3
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Test Expense 3.3.2', 270, 6);
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Test Expense 3.3.3', 63, 6);

-- insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Test Expense 3.4.1', 42,  7); -- Test category 4
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Test Expense 3.4.2', 155, 7);
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Test Expense 3.4.3', 195, 7);

-- insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Test Expense 3.5.1', 108, 8); -- Test category 5
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Test Expense 3.5.2', 85,  8);
-- insert into expenses(date, merchant, amount, category_id) values (date('now','-2 months'), 'Test Expense 3.5.3', 33,  8);


/*
3 months ago
*/
-- TBD

/*
4 months ago
*/
-- TBD