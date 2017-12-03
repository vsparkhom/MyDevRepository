package vlpa.expman.controller;

import vlpa.expman.model.Expense;

import java.util.Collection;

public interface DataProcessor {

    Collection<Expense> importExpenses(String fileName);
}
