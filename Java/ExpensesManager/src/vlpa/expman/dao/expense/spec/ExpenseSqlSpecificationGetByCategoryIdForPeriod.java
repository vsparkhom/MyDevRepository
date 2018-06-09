package vlpa.expman.dao.expense.spec;

import vlpa.expman.controller.ExpenseUtils;
import vlpa.expman.dao.DBQueries;

import java.util.Date;

public class ExpenseSqlSpecificationGetByCategoryIdForPeriod extends ExpenseSqlSpecificationGetByCategoryId {

    private Date start;
    private Date end;

    public ExpenseSqlSpecificationGetByCategoryIdForPeriod(long categoryId, Date start, Date end) {
        super(categoryId);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toSqlClause() {
        return String.format(DBQueries.SQLiteDBQueries.GET_EXPENSES_BY_CATEGORY_ID_FOR_SPECIFIC_PERIOD, categoryId,
                ExpenseUtils.fromDate(start), ExpenseUtils.fromDate(end));
    }
}

