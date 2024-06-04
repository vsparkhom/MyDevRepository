package com.vlpa.spring.expenseimporter;

public interface ApplicationConfig {

    // Excel data

    interface Excel {

        String HOUSE_EXCEL_FILE = "Housing_2024_vlpa.xlsx";

        interface Tabs {
            int CATEGORIES_TAB_INDEX = 0;
            int MAPPING_TAB_INDEX = 1;

            int START_ROW_INDEX = 20;
            int START_COLUMN_INDEX = 8;

            int TAB_INDEX_PREFIX = 2;
        }
    }

    //CSV data

    interface CSV {

        String CSV_FILE = "TD_CC_export_EXAMPLE.csv";
    }
}
