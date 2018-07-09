package vlpa.expman.dao;

public class DBQueries {

    public static class SQLiteDBQueries {

        public static final String GET_ALL_CATEGORIES = "select * from categories";
        public static final String GET_CATEGORY_BY_ID = "select * from categories where id = %d";
        public static final String ADD_CATEGORY = "insert into categories(name, 'limit') values (?, ?)";
        public static final String REMOVE_CATEGORY = "delete from categories where id = ?";
        public static final String UPDATE_CATEGORY = "update categories set name = ?, 'limit' = ? where id = ?";

        public static final String GET_ALL_EXPENSES = "select * from expenses";
        public static final String GET_ALL_EXPENSES_FOR_PERIOD = "select * from expenses where date between '%s' and '%s'";
        public static final String GET_EXPENSES_BY_CATEGORY_ID = "select * from expenses where category_id = %d";
        public static final String GET_EXPENSES_BY_CATEGORY_ID_FOR_SPECIFIC_PERIOD = "select * from expenses where category_id = %d\n" +
                "and date between '%s' and date('%s', '+1 day')";
        public static final String ADD_EXPENSE = "insert into expenses(date, merchant, amount, category_id) values (?, ?, ?, ?)";
        public static final String REMOVE_EXPENSE = "delete from expenses where id = ?";
        public static final String UPDATE_EXPENSE = "update expenses set date = ?, merchant = ?, amount = ?, category_id = ? where id = ?";

        public static final String GET_EXPENSES_MAPPING = "select * from expenses_mapping";
        public static final String ADD_PATTERN = "insert into expenses_mapping(pattern, category_id) values (?, ?)";
        public static final String REMOVE_PATTERN = "delete from expenses_mapping where id = ?";
        public static final String UPDATE_PATTERN = "update expenses_mapping set pattern = ?, category_id = ? where id = ?";



    }

}
