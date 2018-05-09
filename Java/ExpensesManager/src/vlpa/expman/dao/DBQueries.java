package vlpa.expman.dao;

public class DBQueries {

    public static class SQLiteDBQueries {

        public static final String GET_ALL_CATEGORIES = "select * from categories";
        public static final String GET_CATEGORY_BY_ID = "select * from categories where id = ?";

        public static final String GET_ALL_EXPENSES = "select * from expenses";
        public static final String GET_ALL_EXPENSES_FOR_PERIOD = "select * from expenses where date between ? and ?";
        public static final String GET_EXPENSES_BY_CATEGORY_ID = "select * from expenses where category_id = ?";
        public static final String GET_EXPENSES_BY_CATEGORY_ID_FOR_SPECIFIC_PERIOD = "select * from expenses where category_id = ?\n" +
                "and date between ? and date(?, '+1 day')";

        public static final String GET_EXPENSES_MAPPING = "select * from expenses_mapping";
        public static final String GET_LIMIT_FOR_ALL_CATEGORIES = "select sum(\"limit\") as total_limit from categories";







    }

}
