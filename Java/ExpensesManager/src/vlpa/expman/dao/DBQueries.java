package vlpa.expman.dao;

public interface DBQueries {

    interface SQLiteDBQueries {

        String GET_ALL_CATEGORIES = "select * from categories";
        String GET_CATEGORY_BY_ID = "select * from categories where id = %d";
        String ADD_CATEGORY = "insert into categories(name, 'limit') values (?, ?)";
        String REMOVE_CATEGORY = "delete from categories where id = ?";
        String UPDATE_CATEGORY = "update categories set name = ?, 'limit' = ? where id = ?";

        String GET_ALL_EXPENSES = "select * from expenses";
        String GET_ALL_EXPENSES_FOR_PERIOD = "select * from expenses where date between '%s' and '%s'";
        String GET_EXPENSES_BY_CATEGORY_ID = "select * from expenses where category_id = %d";
        String GET_EXPENSES_BY_CATEGORY_ID_FOR_SPECIFIC_PERIOD = "select * from expenses where category_id = %d\n" +
                "and date between '%s' and '%s'";
        String ADD_EXPENSE = "insert into expenses(date, merchant, amount, category_id) values (?, ?, ?, ?)";
        String MERGE_EXPENSE = "insert or replace into expenses(id, date, merchant, amount, category_id) values (\n" +
                                                   "  (select id from expenses where date = ? and merchant = ?)\n" +
                                                   "  , ?, ?, ?, ?)";
        String REMOVE_EXPENSE = "delete from expenses where id = ?";
        String UPDATE_EXPENSE = "update expenses set date = ?, merchant = ?, amount = ?, category_id = ? where id = ?";

        String GET_EXPENSES_MAPPING = "select * from expense_patterns";
        String ADD_PATTERN = "insert into expense_patterns(pattern, category_id, type_id) values (?, ?, ?)";
        String REMOVE_PATTERN = "delete from expense_patterns where id = ?";
        String UPDATE_PATTERN = "update expense_patterns set pattern = ?, category_id = ?, type_id = ? where id = ?";
    }

}
