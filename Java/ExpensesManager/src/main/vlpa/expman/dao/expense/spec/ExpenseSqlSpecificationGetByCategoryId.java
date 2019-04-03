package vlpa.expman.dao.expense.spec;

import vlpa.expman.dao.DBQueries;
import vlpa.expman.dao.SqlSpecification;

public class ExpenseSqlSpecificationGetByCategoryId implements SqlSpecification {

    long categoryId;

    public ExpenseSqlSpecificationGetByCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toSqlClause() {
        return String.format(DBQueries.SQLiteDBQueries.GET_EXPENSES_BY_CATEGORY_ID, categoryId);
    }
}

