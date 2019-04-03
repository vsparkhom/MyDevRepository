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
        getDescrTextArea().setText(expense.getDescription());
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
    protected void selectProperBank() {
        for (int i = 0; i < getBanks().size(); i++) {
            if (getBanks().get(i).equals(expense.getBank())) {
                getBankComboBox().getSelectionModel().select(i);
                break;
            }
        }
    }

    @Override
    protected void performApplyActions(Expense updatedExpense) {
        boolean isChanged = false;
        if (!expense.getName().equals(updatedExpense.getName())) {
            expense.setName(updatedExpense.getName());
            isChanged = true;
        }
        if (!expense.getDate().equals(updatedExpense.getDate())) {
            expense.setDate(updatedExpense.getDate());
            isChanged = true;
        }
        if (expense.getAmount() != updatedExpense.getAmount()) {
            expense.setAmount(updatedExpense.getAmount());
            isChanged = true;
        }
        if (expense.getCategory().getId() != updatedExpense.getCategory().getId()) {
            expense.setCategory(updatedExpense.getCategory());
            isChanged = true;
        }

        if (expense.getBank() != updatedExpense.getBank()) {
            expense.setBank(updatedExpense.getBank());
            isChanged = true;
        }
        if (expense.getDescription() != updatedExpense.getDescription()) {
            expense.setDescription(updatedExpense.getDescription());
            isChanged = true;
        }

        if (isChanged) {
            getProcessor().updateExpense(expense);
        }

        getBuilder().updateView();
        getStage().close();
    }
}
