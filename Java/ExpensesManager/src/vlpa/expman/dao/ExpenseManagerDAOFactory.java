package vlpa.expman.dao;

import java.util.HashMap;
import java.util.Map;

public class ExpenseManagerDAOFactory {

    public static final String FAKE_DAO = "FAKE";
    public static final String SQLITE_DAO = "SQLITE";

    private static Map<String, Class<? extends ExpenseManagerDAO>> daoMapping = new HashMap<>();
    private static ExpenseManagerDAO instance;
    private static String daoType = SQLITE_DAO;

    static {
        daoMapping.put(FAKE_DAO, FakeExpenseManagerDAOImpl.class);
        daoMapping.put(SQLITE_DAO, SQLiteExpenseManagerDAOImpl.class);
    }

    private ExpenseManagerDAOFactory() {
    }

    public static ExpenseManagerDAO getInstance() {
        try {
            if (instance == null) {
                Class<? extends ExpenseManagerDAO> daoClass = daoMapping.get(daoType);
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
