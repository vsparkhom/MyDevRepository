package vlpa.spring.expman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vlpa.spring.expman.controller.period.PeriodHolder;
import vlpa.spring.expman.entity.Category;
import vlpa.spring.expman.entity.ExpenseReport;
import vlpa.spring.expman.service.ExpenseManagerService;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ExpenseManagerService expenseManagerService;

    private boolean isDebugEnabled = true;//TODO: move logging setting to Log4J

    @RequestMapping("/")
    public String showIndexPage() {
        return "debug_index";
    }

    @RequestMapping("/mytest")
    public String showMyTestPage() {
        return "mytest";
    }

    @RequestMapping("/summary")
    public String displaySummaryPage(Model model) {

        model.addAttribute("currentPeriodIndex", PeriodHolder.getInstance().getCurrentPeriod().getIndex());
        model.addAttribute("periodsList", PeriodHolder.getInstance().getPeriodsList());

        List<Category> allCategories = expenseManagerService.getAllCategories();
        model.addAttribute("allCategories", allCategories);

        debug("Categories list:");
        for (Category category : allCategories) {
            debug(category);
        }

        Category summary = expenseManagerService.calculateSummary(allCategories);
        model.addAttribute("summary", summary);
        debug("Summary: " + summary);

        return "summary";
    }

    @RequestMapping("/period")
    public String updatePeriod(@RequestParam("currentPeriodIndex") int currentPeriodIndex, Model model) {
        PeriodHolder.getInstance().setCurrentPeriod(currentPeriodIndex);
        return displaySummaryPage(model);
    }

    public ExpenseManagerService getExpenseManagerService() {
        return expenseManagerService;
    }

    public void setExpenseManagerService(ExpenseManagerService expenseManagerService) {
        this.expenseManagerService = expenseManagerService;
    }

    private void debug(Object message) {
        if (isDebugEnabled) {
            System.out.println("[DEBUG]" + message);
        }
    }
}
