package vlpa.expman.dao.expense.spec;

import vlpa.expman.controller.ExpenseUtils;
import vlpa.expman.dao.DBQueries;
import vlpa.expman.dao.SqlSpecification;

import java.util.Date;

public class ExpenseSqlSpecificationGetAllForPeriod implements SqlSpecification {

    private Date start;
    private Date end;

    public ExpenseSqlSpecificationGetAllForPeriod(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toSqlClause() {
        return String.format(DBQueries.SQLiteDBQueries.GET_ALL_EXPENSES_FOR_PERIOD,
                ExpenseUtils.fromDate(start), ExpenseUtils.fromDate(end));
    }
}
