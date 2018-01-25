package com.perscab.db;

public class DBQueries {

    public static final String GET_ACCOUNT_BY_USER_LOGIN = "select * from accounts where login = ?";

    public static final String GET_ACCOUNT_BY_USER_LOGIN_AND_PASS = "select * from accounts where login = ? and password = ?";

    public static final String GET_SERVICE_NAME_BY_TYPE =
            "select\n" +
            "  s.name as service_name, st.name as type_name\n" +
            "from\n" +
            "  accounts a,\n" +
            "  client_services cs,\n" +
            "  services s,\n" +
            "  service_types st\n" +
            "where\n" +
            "  a.login = ? /* User Login */\n" +
            "  and cs.account_id = a.id\n" +
            "  and s.id = cs.service_id\n" +
            "  and s.type_id = ? /* Service Type */\n" +
            "  and st.id = s.type_id";

    public static final String GET_CURRENT_BALLANCE =
            "select\n" +
            "  sum(p.summa) as summa\n" +
            "from\n" +
            "  accounts a,\n" +
            "  payments p\n" +
            "where\n" +
            "  a.id = ? /* Account Id */\n" +
            "  and p.account_id = a.id\n" +
            "  and p.status = 'Not Paid'";

    public static String GET_PAYMENT_DUE_DATE =
            "select\n" +
            "  max(p.pay_before) as due_date\n" +
            "from\n" +
            "  accounts a,\n" +
            "  payments p\n" +
            "where\n" +
            "  a.id = ? /* Account Id */\n" +
            "  and p.account_id = a.id\n" +
            "  and p.status = 'Not Paid'";

    public static String GET_ALL_PAYMENTS_FOR_ACCOUNT =
            "select p.*\n" +
            "from\n" +
            "  accounts a,\n" +
            "  payments p\n" +
            "where\n" +
            "  a.id = ? /* Account Id */\n" +
            "  and p.account_id = a.id";

}
