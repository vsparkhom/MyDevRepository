package vlpa.expman.view.modal.pattern;

import vlpa.expman.model.Category;
import vlpa.expman.model.ImportPattern;
import vlpa.expman.model.PatternPriority;
import vlpa.expman.model.PatternType;

public class PatternCreator {

    public static ImportPattern create(PatternDataWindow window) {
        String patternText = window.getPatternTextInput().getText();
        int selectedIndex = window.getCategoriesComboBox().getSelectionModel().getSelectedIndex();
        Category category = window.getCategories().get(selectedIndex);
        PatternType type = getPatternType(window);
        PatternPriority patternPriority = PatternPriority.getPatternPriorityByName(
                window.getPrioritiesComboBox().getSelectionModel().getSelectedItem());
        double amount = getAmount(window, type);
        return ImportPattern.builder()
                .setText(patternText)
                .setCategory(category)
                .setType(type)
                .setPriority(patternPriority)
                .setAmount(amount).build();
    }

    private static PatternType getPatternType(PatternDataWindow window) {
        String selectedType = window.getPatternTypesComboBox().getSelectionModel().getSelectedItem();
        return PatternType.getPatternTypeByDisplayName(selectedType);
    }

    private static double getAmount(PatternDataWindow window, PatternType type) {
        double amount = 0;
        String amountText = window.getAmountInput().getText();
        if (type == PatternType.AMOUNT && (amountText != null && !amountText.isEmpty())) {
            amount = Double.parseDouble(amountText);
        }
        return amount;
    }
}
