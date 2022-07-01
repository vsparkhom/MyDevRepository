SELECT SUM(amount) FROM expenses WHERE date BETWEEN (DATE_SUB(NOW(), INTERVAL 1 MONTH)) AND DATE_ADD(NOW(),INTERVAL 1 DAY);

select (DATE_SUB(curdate(), INTERVAL 1 MONTH)) FROM DUAL;

INSERT INTO 
    expenses(date, merchant, amount, category_id)
VALUES
    (NOW() - INTERVAL 1 MONTH, 'Test April expense for Other category',   123,  1);