package vlpa.spring.expman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vlpa.spring.expman.dao.CategoryDAO;
import vlpa.spring.expman.entity.Category;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryManagerService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Transactional
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    @Transactional
    public Category getCategoryById(int categoryId) {
        return categoryDAO.getCategoryById(categoryId);
    }

    public void saveCategory(Category category) {
        categoryDAO.saveCategory(category);
    }

    public void removeCategory(Category category) {
        categoryDAO.removeCategory(category);
    }

}
