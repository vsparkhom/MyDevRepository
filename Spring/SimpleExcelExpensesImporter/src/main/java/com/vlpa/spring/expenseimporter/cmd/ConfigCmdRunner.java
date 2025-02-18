package com.vlpa.spring.expenseimporter.cmd;

import com.vlpa.spring.expenseimporter.ExpensesImporterApplication;
import com.vlpa.spring.expenseimporter.model.Category;
import com.vlpa.spring.expenseimporter.model.Pattern;

import java.io.IOException;
import java.util.List;

import static com.vlpa.spring.expenseimporter.LoggerUtils.debug;
import static com.vlpa.spring.expenseimporter.LoggerUtils.info;

public class ConfigCmdRunner implements CommandRunner {

    @Override
    public void execute(String command) throws Exception {
        ExpensesImporterApplication application = new ExpensesImporterApplication();

        String arg = getFirstArgument(command);
        if (isCategoriesConfig(arg)) {
            loadCategoriesFromExcelToDatabase(application);
        } else if (isPatternsConfig(arg)) {
            loadPatternsAndDisplay(application);
        }
    }

    private String getFirstArgument(String command) {
        String[] cmdParts = command.split(" ");
        return cmdParts[1];//TODO: add validation isNotNull
    }

    private void loadPatternsAndDisplay(ExpensesImporterApplication application) throws IOException {
        debug("------------- PATTERNS -------------");
        List<Category> categories = application.getCategoriesFromExcel();
        List<Pattern> patterns = application.getPatternsMappingFromExcel(categories);
        for (Pattern pattern : patterns) {
            debug(pattern.toString());
        }
    }

    private void loadCategoriesFromExcelToDatabase(ExpensesImporterApplication application) throws IOException {
        List<Category> categories = application.getCategoriesFromExcel();
        debug("------------- CATEGORIES -------------");
        for (Category category : categories) {
            info(category.toString());
        }
        application.storeCategoriesToDatabase(categories);
    }

    private boolean isPatternsConfig(String command) {
        return isConfig("patterns", "-p", command);
    }

    private boolean isCategoriesConfig(String command) {
        return isConfig("categories", "-c", command);
    }

    private boolean isConfig(String fullCmd, String shortCmd, String matchCmd) {
        return fullCmd.equalsIgnoreCase(matchCmd) || shortCmd.equalsIgnoreCase(matchCmd);
    }
}
