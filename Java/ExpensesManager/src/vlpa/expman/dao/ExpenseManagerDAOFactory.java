package vlpa.expman.dao;

import vlpa.expman.dao.category.CategoriesDAO;
import vlpa.expman.dao.category.sqlite.CategoriesDAOImpl;
import vlpa.expman.dao.expense.ExpensesDAO;
import vlpa.expman.dao.expense.sqlite.ExpensesDAOImpl;

import java.util.HashMap;
import java.util.Map;

public class ExpenseManagerDAOFactory {

    public static final String SQLITE_DAO = "SQLITE";

    private static String daoType = SQLITE_DAO;


    private ExpenseManagerDAOFactory() {
    }

    public static String getDaoType() {
        return daoType;
    }

    public static void setDaoType(String dt) {
        daoType = dt;
    }

    public static class ExpensesDAOFactory {

        private static Map<String, Class<? extends ExpensesDAO>> daoMapping = new HashMap<>();
        private static ExpensesDAO instance;

        static {
            daoMapping.put(SQLITE_DAO, ExpensesDAOImpl.class);
        }

        public static ExpensesDAO getInstance() {
            try {
                if (instance == null) {
                    Class<? extends ExpensesDAO> daoClass = daoMapping.get(daoType);
                    if (daoClass == null) {
                        throw  new InstantiationException("DAO class type not found: " + daoType);
                    }
                    instance = daoClass.newInstance();
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return instance;
        }
    }

    public static class CategoriesDAOFactory {

        private static Map<String, Class<? extends CategoriesDAO>> daoMapping = new HashMap<>();
        private static CategoriesDAO instance;

        static {
            daoMapping.put(SQLITE_DAO, CategoriesDAOImpl.class);
        }

        public static CategoriesDAO getInstance() {
            try {
                if (instance == null) {
                    Class<? extends CategoriesDAO> daoClass = daoMapping.get(daoType);
                    if (daoClass == null) {
                        throw  new InstantiationException("DAO class type not found: " + daoType);
                    }
                    instance = daoClass.newInstance();
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return instance;
        }
    }

}
