package vlpa.expman.dao.expense;


import vlpa.expman.model.Expense;

import java.util.List;

public interface ExpensesDAO {

    List<Expense> queryExpenses(String query);
}
