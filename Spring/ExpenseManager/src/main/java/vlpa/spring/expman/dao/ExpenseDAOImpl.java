package vlpa.spring.expman.dao;

import org.springframework.stereotype.Repository;
import vlpa.spring.expman.entity.Expense;

import java.util.Date;
import java.util.List;

@Repository
public class ExpenseDAOImpl implements ExpenseDAO {

    @Override
    public List<Expense> getAllExpenses() {
        return null;
    }

    @Override
    public List<Expense> getExpensesByCategoryAndDates(long categoryId, Date startDate, Date endDate) {
        return null;
    }

}
