package vlpa.expman.view.modal.expenses;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import vlpa.expman.controller.ExpenseUtils;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.view.UIBuilder;

import java.time.ZoneId;
import java.util.Date;

public class ModifyExpenseWindow extends BaseExpenseOperationWindow {

    private Expense expense;

    public ModifyExpenseWindow(UIBuilder builder, MainProcessor processor, Expense expense) {
        this.builder = builder;
        this.processor = processor;
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
    protected EventHandler<ActionEvent> getApplyButtonAction() {
        return event -> {

            String expenseName = getNameInput().getText();
            Date expenseDate = Date.from(getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            double expenseAmount = Double.valueOf(getAmountInput().getText());//TODO: add validation
            int selectedCategoryIndex = getCategoriesComboBox().getSelectionModel().getSelectedIndex();
            Category selectedCategory = getCategories().get(selectedCategoryIndex);

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
                processor.updateExpense(expense);
            }

            builder.updateView();
            getStage().close();
        };
    }
}
