package com.vlpa.spring.expenseimporter;

public interface ApplicationConfig {

    // Excel data

    interface Excel {

        String HOUSE_EXCEL_FILE = "Housing_2024_test.xlsx";

        interface Tabs {
            int CATEGORIES_TAB_INDEX = 1;
            int MAPPING_TAB_INDEX = 2;

            int MONTH_JANUARY = 4;
        }
    }

    //CSV data

    interface CSV {

        String CSV_FILE = "TD_CC_export_EXAMPLE.csv";
    }
}
