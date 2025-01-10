package com.vlpa.spring.expenseimporter.dao;

public interface SqlQueries {

    // categories
    String ADD_CATEGORY = "insert into categories(id, name, parent_id, type) values (?, ?, ?, ?)";
    String REMOVE_ALL_CATEGORIES = "delete from categories";

    //expenses
    String GET_ALL_EXPENSES =  "select e.*, c.name as category_name, c.type as category_type, p_category.id as p_category_id, p_category.name as p_category_name\n" +
        "from expenses e\n" +
        "left join categories c\n" +
        "  on c.id = e.category_id\n" +
        "left join categories p_category\n" +
        "  on p_category.id = c.parent_id\n" +
        "where \n" +
        "  e.purchase_date >= date(?) \n" +
        "  and e.purchase_date <  date(?)";
    String ADD_EXPENSE = "insert into expenses(merchant, amount, purchase_date, type, category_id, card_id) values (?, ?, date(?), ?, ?, (select id from cards where name = ?))";
    String REMOVE_ALL_EXPENSES = "delete from expenses where purchase_date >= date(?) and purchase_date < date(?) and card_id in (select id from cards where name = ?)";

//    String ADD_IMPORT_HISTORY_RECORD = "insert into import_history(date, message) values (date('now'), ?)";
}
