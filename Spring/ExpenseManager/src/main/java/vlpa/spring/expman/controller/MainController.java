package vlpa.spring.expman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import vlpa.spring.expman.entity.Category;
import vlpa.spring.expman.service.ExpenseManagerService;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ExpenseManagerService expenseManagerService;

    @RequestMapping("/")
    public String showIndexPage() {
        return "debug_index";
    }

    @RequestMapping("/mytest")
    public String showMyTestPage() {
        return "mytest";
    }

    @RequestMapping("/summary")
    public String showSummaryPage(Model model) {
        List<Category> allCategories = expenseManagerService.getAllCategories();
        model.addAllAttributes(allCategories);

        return "summary";//TODO: implement view properly to render list of categories
    }

    public ExpenseManagerService getExpenseManagerService() {
        return expenseManagerService;
    }

    public void setExpenseManagerService(ExpenseManagerService expenseManagerService) {
        this.expenseManagerService = expenseManagerService;
    }
}
