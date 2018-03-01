package vlpa.expman.dao;

import vlpa.expman.model.ExpensesReport;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public interface ExpenseReportDAO {

    ExpensesReport getExpensesReportForAllCategories(Connection conn, Date start, Date end) throws SQLException;

    ExpensesReport getExpensesReportForCategory(Connection conn, long categoryId, Date start, Date end) throws SQLException;
}
