package vlpa.expman.dao;

import vlpa.expman.dao.expense.ExpensesDAO;
import vlpa.expman.dao.expense.sqlite.ExpensesDAOImpl;

import java.util.HashMap;
import java.util.Map;

//TODO: use factory or delete
public class ExpenseManagerDAOFactory {

    public static final String SQLITE_DAO = "SQLITE";

    private static Map<String, Class<? extends ExpensesDAO>> daoMapping = new HashMap<>();
    private static ExpensesDAO instance;
    private static String daoType = SQLITE_DAO;

    static {
        daoMapping.put(SQLITE_DAO, ExpensesDAOImpl.class);
    }

    private ExpenseManagerDAOFactory() {
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

    public static String getDaoType() {
        return daoType;
    }

    public static void setDaoType(String dt) {
        daoType = dt;
    }
}
