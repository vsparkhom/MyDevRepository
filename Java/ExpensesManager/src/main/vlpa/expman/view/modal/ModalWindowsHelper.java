package vlpa.expman.view.modal;

import javafx.scene.control.Alert;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.model.ImportPattern;
import vlpa.expman.view.UIBuilder;
import vlpa.expman.view.modal.categories.CategoriesManagementWindow;
import vlpa.expman.view.modal.categories.ModifyCategoryWindow;
import vlpa.expman.view.modal.expenses.AddExpenseWindow;
import vlpa.expman.view.modal.expenses.ImportExpensesWindow;
import vlpa.expman.view.modal.expenses.ModifyExpenseWindow;
import vlpa.expman.view.modal.pattern.GeneratePatternWindow;
import vlpa.expman.view.modal.pattern.ModifyPatternWindow;
import vlpa.expman.view.modal.pattern.PatternManagementWindow;

public class ModalWindowsHelper {

    public static void showCategoriesManagementWindow(UIBuilder builder, MainProcessor processor) {
        new CategoriesManagementWindow(builder, processor).show();
    }

    public static <T extends Category> ModifyCategoryWindow<T> getModifyCategoryWindow(UIBuilder builder, T category) {
        return new ModifyCategoryWindow(builder, category);
    }

    public static void showAddExpenseWindow(UIBuilder builder, MainProcessor processor) {
        new AddExpenseWindow(builder, processor).show();
    }

    public static void showModifyExpenseWindow(UIBuilder builder, MainProcessor processor, Expense expense) {
        new ModifyExpenseWindow(builder, processor, expense).show();
    }

    public static void showImportExpensesWindow(UIBuilder builder) {
        new ImportExpensesWindow(builder).show();
    }

    public static void showGeneratePatternWindow(UIBuilder builder, MainProcessor processor, String expenseMerchant) {
        new GeneratePatternWindow(builder, processor, expenseMerchant).show();
    }

    public static void showPatternManagementWindow(UIBuilder builder, MainProcessor processor) {
        new PatternManagementWindow(builder, processor).show();
    }

    public static <T extends ImportPattern> ModifyPatternWindow<T> getModifyPatternWindow(UIBuilder builder, MainProcessor processor, T pattern) {
        return new ModifyPatternWindow(builder, processor, pattern);
    }

    public static Alert getConfirmationDialog(String headerMsg, String contentMsg) {
        return getDialog(Alert.AlertType.CONFIRMATION, "Confirmation Dialog", headerMsg, contentMsg);
    }

    public static Alert getWarningDialog(String headerMsg, String contentMsg) {
        return getDialog(Alert.AlertType.WARNING, "Warning Dialog", headerMsg, contentMsg);
    }

    public static Alert getInformationDialog(String headerMsg, String contentMsg) {
        return getDialog(Alert.AlertType.INFORMATION, "Information Dialog", headerMsg, contentMsg);
    }

    public static Alert getErrorDialog(String headerMsg, String contentMsg) {
        return getDialog(Alert.AlertType.ERROR, "Error Dialog", headerMsg, contentMsg);
    }

    public static Alert getDialog(Alert.AlertType type, String title, String headerMsg, String contentMsg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerMsg);
        alert.setContentText(contentMsg);
        return alert;
    }
}
