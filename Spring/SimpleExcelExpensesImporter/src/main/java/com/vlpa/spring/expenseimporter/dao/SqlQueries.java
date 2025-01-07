package com.vlpa.spring.expenseimporter.dao;

public interface SqlQueries {

    String GET_ALL_CATEGORIES = "select * from categories";
    String GET_CATEGORY_BY_ID = "select * from categories where id = %d";
    String ADD_CATEGORY = "insert into categories(id, name, parent_id, type) values (?, ?, ?, ?)";
    String REMOVE_ALL_CATEGORIES = "delete from categories";
    String UPDATE_CATEGORY = "update categories set name = ?, 'limit' = ? where id = ?";

//    String GET_ALL_EXPENSES = "select * from expenses where purchase_date >= date(?) and purchase_date < date(?)";
    String GET_ALL_EXPENSES =  "select e.*, c.name as category_name, c.type as category_type, p_category.id as p_category_id, p_category.name as p_category_name\n" +
        "from expenses e\n" +
        "left join categories c\n" +
        "  on c.id = e.category_id\n" +
        "left join categories p_category\n" +
        "  on p_category.id = c.parent_id\n" +
        "where \n" +
        "  e.purchase_date >= date(?) \n" +
        "  and e.purchase_date <  date(?)";
    String ADD_EXPENSE = "insert into expenses(merchant, amount, purchase_date, category_id, card_id) values (?, ?, date(?), ?, (select id from cards where name = ?))";
    String MERGE_EXPENSE = "insert or replace into expenses(id, date, merchant, amount, category_id, bank, description) values (\n" +
                                               "  (select id from expenses where date = ? and merchant = ?)\n" +
                                               "  , ?, ?, ?, ?, ?, ?)";
    String REMOVE_ALL_EXPENSES = "delete from expenses where purchase_date >= date(?) and purchase_date < date(?) and card_id in (select id from cards where name = ?)";
    String UPDATE_EXPENSE = "update expenses set date = ?, merchant = ?, amount = ?, category_id = ?, bank = ?, description = ? where id = ?";

    String GET_EXPENSES_MAPPING = "select * from expense_patterns";
    String ADD_PATTERN = "insert into expense_patterns(pattern, category_id, type_id, priority, amount) values (?, ?, ?, ?, ?)";
    String REMOVE_PATTERN = "delete from expense_patterns where id = ?";
    String UPDATE_PATTERN = "update expense_patterns set pattern = ?, category_id = ?, type_id = ?, priority= ?, " +
            "amount = ? where id = ?";

    String ADD_IMPORT_HISTORY_RECORD = "insert into import_history(date, message) values (date('now'), ?)";

}
