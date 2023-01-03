package vlpa.spring.expman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vlpa.spring.expman.controller.period.PeriodHolder;
import vlpa.spring.expman.entity.Category;
import vlpa.spring.expman.service.CategoryManagerService;
import vlpa.spring.expman.service.ExpenseManagerService;
import vlpa.spring.expman.service.ExpenseServiceUtils;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ExpenseManagerService expenseManagerService;

    @Autowired
    private CategoryManagerService categoryManagerService;

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

        ControllerUtils.initPeriodParameters(model);

        List<Category> allCategories = categoryManagerService.getAllCategories();
        model.addAttribute("allCategories", allCategories);

        ControllerUtils.debug("Categories list:");
        for (Category category : allCategories) {
            ControllerUtils.debug(category);
        }

        Category summary = ExpenseServiceUtils.calculateSummary(allCategories);
        model.addAttribute("summary", summary);
        ControllerUtils.debug("Summary: " + summary);

        return "summary";
    }

    @RequestMapping("/management-expenses")
    public String displayManagementExpensesPage() {
        //TODO: implement
        return "management-expenses";
    }

    @RequestMapping("/period")
    public String updatePeriod(@RequestParam("currentPeriodIndex") int currentPeriodIndex,
                               @RequestParam("params") String params,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        PeriodHolder.getInstance().setCurrentPeriod(currentPeriodIndex);

        if (params.startsWith("category")) {
            String[] parameters = params.split("_");
            Integer categoryId = Integer.valueOf(parameters[1]);
//            return displayCategoryPage(categoryId, model);//TODO: test if it's working as expected
            redirectAttributes.addAttribute("categoryId", categoryId);
            return "redirect:/category";//redirect to another controller with parameters
        }
        return displaySummaryPage(model);
    }

    public ExpenseManagerService getExpenseManagerService() {
        return expenseManagerService;
    }

    public void setExpenseManagerService(ExpenseManagerService expenseManagerService) {
        this.expenseManagerService = expenseManagerService;
    }

    public CategoryManagerService getCategoryManagerService() {
        return categoryManagerService;
    }

    public void setCategoryManagerService(CategoryManagerService categoryManagerService) {
        this.categoryManagerService = categoryManagerService;
    }
}
