package com.vlpa.spring.expenseimporter.dao.category;

import static com.vlpa.spring.expenseimporter.LoggerUtils.warning;

public class CategoriesDAOFactory {

    private static CategoriesDAO instance;

    public static CategoriesDAO getInstance() {
        try {
            if (instance == null) {
                instance = CategoriesDAOImpl.class.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException e) {
            warning("DAO object can't be instantiated due to error: " + e);
        }
        return instance;
    }

}
