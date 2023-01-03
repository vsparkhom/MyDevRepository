package vlpa.spring.expman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vlpa.spring.expman.entity.Category;
import vlpa.spring.expman.entity.Expense;
import vlpa.spring.expman.service.CategoryManagerService;
import vlpa.spring.expman.service.ExpenseServiceUtils;

import java.util.List;

@Controller
public class CategoriesController {

    @Autowired
    private CategoryManagerService categoryManagerService;

    @RequestMapping("/category")
    public String displayCategoryPage(@RequestParam("categoryId") int categoryId, Model model) {
        ControllerUtils.initPeriodParameters(model);

        Category category = categoryManagerService.getCategoryById(categoryId);
        model.addAttribute("category", category);

        List<Expense> expenses = ExpenseServiceUtils.getCategoryExpensesForCurrentPeriod(category);
        model.addAttribute("expenses", expenses);

        return "category";
    }

    @RequestMapping("/management-categories")
    public String displayManagementCategoriesPage(Model model) {
        model.addAttribute("category", new Category());

        List<Category> allCategories = categoryManagerService.getAllCategories();
        model.addAttribute("allCategories", allCategories);

        return "management-categories";
    }

    @RequestMapping("/saveCategory")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryManagerService.saveCategory(category);

        return "redirect:management-categories";
    }

    @RequestMapping("/removeCategory")
    public String removeCategory(@RequestParam("id") int categoryId) {
        Category category = categoryManagerService.getCategoryById(categoryId);
        categoryManagerService.removeCategory(category);

        return "redirect:management-categories";
    }

    @RequestMapping("/editCategory")
    public String editCategory(@RequestParam("id") int categoryId, Model model) {
        Category category = categoryManagerService.getCategoryById(categoryId);
        ControllerUtils.debug("Prepare category object for update: " + category);

        model.addAttribute("category", category);

        return "edit-category";
    }

    public CategoryManagerService getCategoryManagerService() {
        return categoryManagerService;
    }

    public void setCategoryManagerService(CategoryManagerService categoryManagerService) {
        this.categoryManagerService = categoryManagerService;
    }
}
