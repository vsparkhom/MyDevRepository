package vlpa.expman.view.modal.expenses;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import vlpa.expman.controller.ExpenseUtils;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.view.UIBuilder;

import java.util.Date;

public class AddExpenseWindow extends BaseExpenseOperationWindow {

    public AddExpenseWindow(UIBuilder builder, MainProcessor processor) {
        super(builder, processor);
    }

    @Override
    protected String getWindowTitle() {
        return "Add expense";
    }

    @Override
    protected void selectProperCategory() {
        for (int i = 0; i < getCategories().size(); i++) {
            if (getCategories().get(i).getId() == getBuilder().getCurrentCategoryId()) {
                getCategoriesComboBox().getSelectionModel().select(i);
                break;
            }
        }
    }

    @Override
    protected void performApplyActions(String expenseName,
                                       Date expenseDate,
                                       double expenseAmount,
                                       Category selectedCategory) {
        Expense exp = new Expense(0, expenseName, expenseDate, expenseAmount, selectedCategory);
        getProcessor().addExpense(exp);

        getBuilder().updateView();
        getStage().close();
    }
}
