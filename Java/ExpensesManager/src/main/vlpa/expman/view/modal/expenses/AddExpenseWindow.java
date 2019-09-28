package vlpa.expman.view.modal.expenses;

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
    protected void performApplyActions(Expense updatedExpense) {
        getProcessor().addExpense(updatedExpense);
        getBuilder().updateView();
        getStage().close();
    }
}
