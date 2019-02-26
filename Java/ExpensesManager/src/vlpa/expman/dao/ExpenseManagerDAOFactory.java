package vlpa.expman.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vlpa.expman.dao.category.CategoriesDAO;
import vlpa.expman.dao.category.sqlite.CategoriesDAOImpl;
import vlpa.expman.dao.expense.ExpensesDAO;
import vlpa.expman.dao.expense.sqlite.ExpensesDAOImpl;
import vlpa.expman.dao.imprt.ImportPatternsDAO;
import vlpa.expman.dao.imprt.sql.ImportPatternsDAOImpl;

import java.util.HashMap;
import java.util.Map;

public class ExpenseManagerDAOFactory {

    public static final String SQLITE_DAO = "SQLITE";
    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseManagerDAOFactory.class);

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
                        throw new InstantiationException("DAO class type not found: " + daoType);
                    }
                    instance = daoClass.newInstance();
                }
            } catch (InstantiationException | IllegalAccessException e) {
                LOGGER.error("DAO object can't be instantiated due to error", e);
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
                        throw new InstantiationException("DAO class type not found: " + daoType);
                    }
                    instance = daoClass.newInstance();
                }
            } catch (InstantiationException | IllegalAccessException e) {
                LOGGER.error("DAO object can't be instantiated due to error", e);
            }
            return instance;
        }
    }

    public static class ImportExpensesDAOFactory {

        private static Map<String, Class<? extends ImportPatternsDAO>> daoMapping = new HashMap<>();
        private static ImportPatternsDAO instance;

        static {
            daoMapping.put(SQLITE_DAO, ImportPatternsDAOImpl.class);
        }

        public static ImportPatternsDAO getInstance() {
            try {
                if (instance == null) {
                    Class<? extends ImportPatternsDAO> daoClass = daoMapping.get(daoType);
                    if (daoClass == null) {
                        throw new InstantiationException("DAO class type not found: " + daoType);
                    }
                    instance = daoClass.newInstance();
                }
            } catch (InstantiationException | IllegalAccessException e) {
                LOGGER.error("DAO object can't be instantiated due to error", e);
            }
            return instance;
        }
    }

}
