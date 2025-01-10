package com.vlpa.spring.expenseimporter;

public interface ExcelConfig {

    // Excel data
    String FILE_NAME = "Configuration.xlsx";

    interface TabIndex {
        int CATEGORIES = 0;
        int MAPPING = 1;
    }
}
