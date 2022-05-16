package vlpa.spring.expman.dao;

import org.springframework.stereotype.Repository;
import vlpa.spring.expman.entity.Category;

import java.util.Collections;
import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public List<Category> getAllCategories() {
        return Collections.emptyList();//TODO: implement Hibernate query
    }
}
