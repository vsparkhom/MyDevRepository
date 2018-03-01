package vlpa.expman.controller.imprt;

import vlpa.expman.model.Expense;

import java.util.Collection;

public interface DataImporter {

    Collection<Expense> importExpensesFromFile(String fileName);
}
