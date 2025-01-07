package com.vlpa.spring.expenseimporter.dao.category;

import com.vlpa.spring.expenseimporter.model.Category;

import java.util.List;

public interface CategoriesDAO {

    void saveCategories(List<Category> categories);

    List<Category> readCategories();

    void removeAllCategories();

}
