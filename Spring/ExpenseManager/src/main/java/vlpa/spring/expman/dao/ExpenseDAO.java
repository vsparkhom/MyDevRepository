package vlpa.spring.expman.dao;

import vlpa.spring.expman.entity.Expense;

import java.util.Date;
import java.util.List;

public interface ExpenseDAO {

    List<Expense> getAllExpenses();

    List<Expense> getExpensesByCategoryAndDates(long categoryId, Date startDate, Date endDate);

}
