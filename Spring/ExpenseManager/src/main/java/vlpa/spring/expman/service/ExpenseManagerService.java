package vlpa.spring.expman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vlpa.spring.expman.dao.CategoryDAO;
import vlpa.spring.expman.dao.ExpenseDAO;
import vlpa.spring.expman.entity.Category;
import vlpa.spring.expman.entity.ExpenseReport;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ExpenseManagerService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private ExpenseDAO expenseDAO;

    @Transactional
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    public Category calculateSummary(List<Category> allCategories) {
        Category summary = new Category("Summary");
        ExpenseReport totalExpenseReport = new ExpenseReport();
        summary.setExpenseReport(totalExpenseReport);

        for (Category category : allCategories) {
            summary.setLimit(summary.getLimit() + category.getLimit());
            totalExpenseReport.addAmount(category.getCurrentAmount());
        }
        return summary;
    }
}
