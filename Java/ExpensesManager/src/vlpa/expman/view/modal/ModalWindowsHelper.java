package vlpa.expman.view.modal;

import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.view.UIBuilder;

public class ModalWindowsHelper {

    public static CategoriesManagementDialog getCategoriesManagementDialog(UIBuilder builder, MainProcessor processor) {
        return new CategoriesManagementDialog(builder, processor);
    }

    public static UpdateCategoryDialog getUpdateCategoryDialog(UIBuilder builder, Category category) {
        return new UpdateCategoryDialog(builder, category);
    }
}
