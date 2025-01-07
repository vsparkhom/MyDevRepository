package com.vlpa.spring.expenseimporter;

//TODO: move configuration to the properties file
public interface ExcelConfig {

    // Excel data
    String FILE_NAME = "Configuration.xlsx";

    interface TabIndex {
        int CATEGORIES = 0;
        int MAPPING = 1;
    }

    interface Categories {
        int START_ROW_INDEX = 15;
        int START_COLUMN_INDEX = 8;

        int TAB_INDEX_PREFIX = 2;
    }

    interface Mapping {

    }
}
