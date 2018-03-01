package vlpa.expman.controller.imprt;

import vlpa.expman.model.Expense;
import java.util.Collection;

public abstract class AbstractDataImporter implements DataImporter {

    @Override
    public Collection<Expense> importExpensesFromFile(String fileName) {
        return getDataFromFile(fileName);
    }

    protected abstract Collection<Expense> getDataFromFile(String fileName);

}
