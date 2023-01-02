package vlpa.spring.expman.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vlpa.spring.expman.entity.Category;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Category> getAllCategories() {
        Session session = sessionFactory.getCurrentSession();
        List<Category> allCategories = session.createQuery("from Category", Category.class).getResultList();
        return allCategories;
    }

    @Override
    @Transactional
    public Category getCategoryById(int categoryId) {
        Session session = sessionFactory.getCurrentSession();
        Category category = session.createQuery("from Category where id = " + categoryId, Category.class).getSingleResult();
        return category;
    }

    @Override
    @Transactional
    public void saveCategory(Category category) {
        System.out.println("[DEBUG] Saving category to database: " + category);
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(category);
        System.out.println("[DEBUG] Category has been successfully stored.");
    }

    @Override
    @Transactional
    public void removeCategory(Category category) {
        System.out.println("[DEBUG] Removing category from database: " + category);
        Session session = sessionFactory.getCurrentSession();
        session.remove(category);
        System.out.println("[DEBUG] Category has been removed.");
    }
}
