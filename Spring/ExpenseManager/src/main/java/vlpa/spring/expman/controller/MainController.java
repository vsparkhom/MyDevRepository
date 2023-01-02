package vlpa.spring.expman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vlpa.spring.expman.controller.period.PeriodHolder;
import vlpa.spring.expman.entity.Category;
import vlpa.spring.expman.entity.Expense;
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

        initPeriodParameters(model);

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

    private void initPeriodParameters(Model model) {
        model.addAttribute("currentPeriodIndex", PeriodHolder.getInstance().getCurrentPeriod().getIndex());
        model.addAttribute("periodsList", PeriodHolder.getInstance().getPeriodsList());
    }

    @RequestMapping("/category")
    public String displayCategoryPage(@RequestParam("categoryId") int categoryId, Model model) {
        initPeriodParameters(model);

        Category category = expenseManagerService.getCategoryById(categoryId);
        model.addAttribute("category", category);

        List<Expense> expenses = expenseManagerService.getCategoryExpensesForCurrentPeriod(category);
        model.addAttribute("expenses", expenses);

        return "category";
    }

    @RequestMapping("/management-categories")
    public String displayManagementCategoriesPage(Model model) {
        model.addAttribute("category", new Category());

        List<Category> allCategories = expenseManagerService.getAllCategories();
        model.addAttribute("allCategories", allCategories);

        return "management-categories";
    }

    @RequestMapping("/management-expenses")
    public String displayManagementExpensesPage() {
        //TODO: implement
        return "management-expenses";
    }

    @RequestMapping("/period")
    public String updatePeriod(@RequestParam("currentPeriodIndex") int currentPeriodIndex,
                               @RequestParam("params") String params,
                               Model model) {
        PeriodHolder.getInstance().setCurrentPeriod(currentPeriodIndex);

        if (params.startsWith("category")) {
            String[] parameters = params.split("_");
            Integer categoryId = Integer.valueOf(parameters[1]);
            return displayCategoryPage(categoryId, model);
        }
        return displaySummaryPage(model);
    }

    @RequestMapping("/saveCategory")
    public String saveCategory(@ModelAttribute("category") Category category) {
        expenseManagerService.saveCategory(category);

        return "redirect:management-categories";
    }

    @RequestMapping("/removeCategory")
    public String removeCategory(@RequestParam("id") int categoryId) {
        Category category = expenseManagerService.getCategoryById(categoryId);
        expenseManagerService.removeCategory(category);

        return "redirect:management-categories";
    }

    @RequestMapping("/editCategory")
    public String editCategory(@RequestParam("id") int categoryId, Model model) {
        Category category = expenseManagerService.getCategoryById(categoryId);
        debug("Prepare category object for update: " + category);

        model.addAttribute("category", category);

        return "edit-category";
    }

    public ExpenseManagerService getExpenseManagerService() {
        return expenseManagerService;
    }

    public void setExpenseManagerService(ExpenseManagerService expenseManagerService) {
        this.expenseManagerService = expenseManagerService;
    }

    private void debug(Object message) {
        if (isDebugEnabled) {
            System.out.println("[DEBUG] " + message);
        }
    }
}
