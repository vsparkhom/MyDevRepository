package vlpa.expman.dao.expense.spec;

import vlpa.expman.dao.DBQueries;
import vlpa.expman.dao.SqlSpecification;

public class ExpenseSqlSpecificationGetAll implements SqlSpecification {

    @Override
    public String toSqlClause() {
        return DBQueries.SQLiteDBQueries.GET_ALL_EXPENSES;
    }
}
