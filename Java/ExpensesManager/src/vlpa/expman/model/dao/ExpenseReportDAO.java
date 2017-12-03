package vlpa.expman.model.dao;

import vlpa.expman.model.ExpensesReport;

import java.util.Date;

public interface ExpenseReportDAO {

    ExpensesReport getExpensesReportForAllCategories(Date start, Date end);

    ExpensesReport getExpensesReportForCategory(int categoryId, Date start, Date end);
}
