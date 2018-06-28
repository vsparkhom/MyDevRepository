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
    protected void init() {
        super.init();
    }

    @Override
    protected String getWindowTitle() {
        return "Add expense";
    }

    @Override
    protected void selectProperCategory() {
        getCategoriesComboBox().getSelectionModel().selectFirst();
    }

    @Override
    protected EventHandler<ActionEvent> getApplyButtonAction() {
        return event -> {

            String expenseName = getNameInput().getText();
            Date expenseDate = ExpenseUtils.fromLocalDateToDate(getDate());
            double expenseAmount = Double.valueOf(getAmountInput().getText());//TODO: add validation
            int selectedCategoryIndex = getCategoriesComboBox().getSelectionModel().getSelectedIndex();
            Category selectedCategory = getCategories().get(selectedCategoryIndex);

            Expense exp = new Expense(0, expenseName, expenseDate, expenseAmount, selectedCategory);
            getProcessor().addExpense(exp);

            getBuilder().updateView();
            getStage().close();
        };
    }
}
