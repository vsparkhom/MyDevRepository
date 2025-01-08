package com.vlpa.spring.expenseimporter;

public interface ExcelConfig {

    // Excel data
    String FILE_NAME = "Configuration.xlsx";

    interface TabIndex {
        int CATEGORIES = 0;
        int MAPPING = 1;
    }

    interface Categories {
        int START_ROW_INDEX = 15;
    }
}
