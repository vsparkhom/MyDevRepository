package vlpa.expman.view.modal;

import javafx.scene.control.Alert;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.view.UIBuilder;
import vlpa.expman.view.modal.categories.CategoriesManagementWindow;
import vlpa.expman.view.modal.categories.ModifyCategoryWindow;
import vlpa.expman.view.modal.expenses.AddExpenseWindow;
import vlpa.expman.view.modal.expenses.ModifyExpenseWindow;

public class ModalWindowsHelper {

    public static CategoriesManagementWindow getCategoriesManagementWindow(UIBuilder builder, MainProcessor processor) {
        return new CategoriesManagementWindow(builder, processor);
    }

    public static ModifyCategoryWindow getModifyCategoryWindow(UIBuilder builder, Category category) {
        return new ModifyCategoryWindow(builder, category);
    }

    public static AddExpenseWindow getAddExpenseWindow(UIBuilder builder, MainProcessor processor) {
        return new AddExpenseWindow(builder, processor);
    }

    public static ModifyExpenseWindow getModifyExpenseWindow(UIBuilder builder, MainProcessor processor, Expense expense) {
        return new ModifyExpenseWindow(builder, processor, expense);
    }

    public static Alert getConfirmationDialog(String headerMsg, String contentMsg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(headerMsg);
        alert.setContentText(contentMsg);
        return alert;
    }
}
