package com.vlpa.spring.expenseimporter;

public interface ExcelConfig {

    String FILE_NAME = "Configuration.xlsx";
    String TABLE_HEADER_SYMBOL = "#";

    interface TabIndex {
        int CATEGORIES = 0;
        int MAPPING = 1;
    }
}
