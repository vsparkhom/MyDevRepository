package vlpa.expman.view.modal.expenses;

import vlpa.expman.controller.ExpenseUtils;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.view.UIBuilder;

import java.util.Date;

public class ModifyExpenseWindow extends BaseExpenseOperationWindow {

    private Expense expense;

    public ModifyExpenseWindow(UIBuilder builder, MainProcessor processor, Expense expense) {
        setBuilder(builder);
        setProcessor(processor);
        this.expense = expense;
        init();
    }

    @Override
    protected void init() {
        super.init();

        getNameInput().setText(expense.getName());
        setDate(ExpenseUtils.fromDateToLocalDate(expense.getDate()));
        getAmountInput().setText("" + expense.getAmount());
    }

    @Override
    protected String getWindowTitle() {
        return "Modify expense";
    }

    @Override
    protected void selectProperCategory() {
        for (int i = 0; i < getCategoriesData().size(); i++) {
            if (getCategoriesData().get(i).equals(expense.getCategory().getName())) {
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
        boolean isChanged = false;
        if (!expense.getName().equals(expenseName)) {
            expense.setName(expenseName);
            isChanged = true;
        }
        if (!expense.getDate().equals(expenseDate)) {
            expense.setDate(expenseDate);
            isChanged = true;
        }
        if (expense.getAmount() != expenseAmount) {
            expense.setAmount(expenseAmount);
            isChanged = true;
        }
        if (expense.getCategory().getId() != selectedCategory.getId()) {
            expense.setCategory(selectedCategory);
            isChanged = true;
        }

        if (isChanged) {
            getProcessor().updateExpense(expense);
        }

        getBuilder().updateView();
        getStage().close();
    }
}
