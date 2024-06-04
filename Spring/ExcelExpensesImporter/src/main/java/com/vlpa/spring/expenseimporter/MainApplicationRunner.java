package com.vlpa.spring.expenseimporter;

import com.vlpa.spring.expenseimporter.model.Bank;
import com.vlpa.spring.expenseimporter.model.CardType;
import com.vlpa.spring.expenseimporter.model.ImportRequestData;
import com.vlpa.spring.expenseimporter.repository.ExpenseParsingHelper;

import java.io.File;
import java.net.URL;

public class MainApplicationRunner {

    public static void main(String[] args) throws Exception {

        LoggerUtils.setCurrentLevel(LoggerUtils.LogLevel.ALL);

        //input parameters
//        String inputBank = "td";
        String inputBank = "pcf";
        String inputCardType = "credit";
//        String inputCardType = "debit";
        String monthNumber = "5";//dd-MM-yyyy
//        String monthNumber = "01-12-2023";

        // prepare request data
        ImportRequestData requestData = new ImportRequestData();
        requestData.setBank(Bank.resolveBank(inputBank));
        requestData.setCardType(CardType.resolveCardType(inputCardType));
        requestData.setBeginningOfTheMonth(Integer.valueOf(monthNumber));

        /* START EXPORTING */

        ExpensesImporterApplication application = new ExpensesImporterApplication();

//        List<ExpenseCategory> categories = application.getCategoriesFromExcel();

//        Map<String, List<ExpensePattern>> patternsMapping = application.getPatternsMappingFromExcel();

//        application.execute(requestData);//TODO
        application.storeData(requestData);//TODO

        // prepare request data
        ImportRequestData requestData2 = new ImportRequestData();
        requestData2.setBank(Bank.resolveBank("td"));
        requestData2.setCardType(CardType.resolveCardType("credit"));
        requestData2.setBeginningOfTheMonth(Integer.valueOf(monthNumber));

        application.storeData(requestData2);//TODO



//        application.storeExpensesIntoExcel();

//        Calendar c = Calendar.getInstance();
//        c.setTime(new Date());
//        c.add(Calendar.MONTH, 1);
//
//        System.out.println("date: " + c.getTime());
//        System.out.println("current month: " + c.get(Calendar.MONTH));

//        URL resource = MainApplicationRunner.class.getResource("resource/Housing_2024_vlpa.xlsx");
//        System.out.println("path: " + resource);
//
//        File directory = new File("resource/Housing_2024_vlpa.xlsx");
//        System.out.println(directory.getAbsolutePath());


        //--------------

        String cellValue = "E-TRANSFER<amount=1800>";
        System.out.println("Expression: " + ExpenseParsingHelper.parseExpressionFromAmountBasedPattern(cellValue));
        System.out.println("Amount: " + ExpenseParsingHelper.parseAmountValue(cellValue));
    }
}
