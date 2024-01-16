package com.vlpa.spring.expenseimporter;

import com.vlpa.spring.expenseimporter.model.ExpenseCategory;
import com.vlpa.spring.expenseimporter.model.ExpensePattern;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MainApplicationRunner {

    public static void main( String[] args ) throws Exception {

        //input parameters
        String inputCommand = "import";
        String inputBank = "td";
        String inputCardType = "credit";
        String inputDate = "01-01-2024";//dd-MM-yyyy
//        String inputDate = "01-12-2023";

        ExpensesImporterApplication application = new ExpensesImporterApplication();

//        List<ExpenseCategory> categories = application.getCategoriesFromExcel();

//        Map<String, List<ExpensePattern>> patternsMapping = application.getPatternsMappingFromExcel();

        application.execute(inputBank, inputCardType, inputDate);

//        application.storeExpensesIntoExcel();

//        Calendar c = Calendar.getInstance();
//        c.setTime(new Date());
//        c.add(Calendar.MONTH, 1);
//
//        System.out.println("date: " + c.getTime());
//        System.out.println("current month: " + c.get(Calendar.MONTH));


    }
}
