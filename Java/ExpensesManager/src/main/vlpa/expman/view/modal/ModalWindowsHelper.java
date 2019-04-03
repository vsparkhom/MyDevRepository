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

    public static CategoriesManagementWindow getCategoriesManagementWindow(UIBuilder builder, MainProcessor processor) {
        return new CategoriesManagementWindow(builder, processor);
    }

    public static <T extends Category> ModifyCategoryWindow<T> getModifyCategoryWindow(UIBuilder builder, T category) {
        return new ModifyCategoryWindow(builder, category);
    }

    public static AddExpenseWindow getAddExpenseWindow(UIBuilder builder, MainProcessor processor) {
        return new AddExpenseWindow(builder, processor);
    }

    public static ModifyExpenseWindow getModifyExpenseWindow(UIBuilder builder, MainProcessor processor, Expense expense) {
        return new ModifyExpenseWindow(builder, processor, expense);
    }

    public static ImportExpensesWindow getImportExpensesWindow(UIBuilder builder) {
        return new ImportExpensesWindow(builder);
    }

    public static GeneratePatternWindow<String> getGeneratePatternWindow(UIBuilder builder, MainProcessor processor, String expenseMerchant) {
        return new GeneratePatternWindow(builder, processor, expenseMerchant);
    }

    public static PatternManagementWindow getPatternManagementWindow(UIBuilder builder, MainProcessor processor) {
        return new PatternManagementWindow(builder, processor);
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
