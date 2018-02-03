package com.perscab.db;

public class DBQueries {

    public static final String GET_ACCOUNT_BY_USER_LOGIN = "select * from accounts where login = ?";

    public static final String GET_ACCOUNT_BY_USER_LOGIN_AND_PASS = "select * from accounts where login = ? and password = ?";

    public static final String GET_SERVICE_INSTANCE_BY_TYPE =
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

    public static final String GET_INTERNET_SERVICE_INSTANCE_FOR_ACCOUNT =
            "select s.id as service_id, s.name as service_name, st.id as type_id, st.name as type_name, p.price, iso.*\n" +
            "from \n" +
            "client_services sc,\n" +
            "services s,\n" +
            "service_types st,\n" +
            "internet_service_options iso,\n" +
            "plans p\n" +
            "where \n" +
            "sc.account_id = ? /* Account Id */\n" +
            "and sc.service_id = s.id\n" +
            "and s.type_id = 100 /* Internet */\n" +
            "and st.id = s.type_id\n" +
            "and p.id = s.plan_id\n" +
            "and iso.service_id = sc.service_id";

    public static final String GET_TV_SERVICE_INSTANCE_FOR_ACCOUNT =
            "select s.id as service_id, s.name as service_name, st.id as type_id, st.name as type_name, p.price, tvso.*\n" +
            "from \n" +
            "client_services sc,\n" +
            "services s,\n" +
            "service_types st,\n" +
            "tv_service_options tvso,\n" +
            "plans p\n" +
            "where \n" +
            "sc.account_id = ? /* Account Id */\n" +
            "and sc.service_id = s.id\n" +
            "and s.type_id = 101 /* TV */\n" +
            "and st.id = s.type_id\n" +
            "and p.id = s.plan_id\n" +
            "and tvso.service_id = sc.service_id";


    public static final String GET_PHONE_SERVICE_INSTANCE_FOR_ACCOUNT =
            "select s.id as service_id, s.name as service_name, st.id as type_id, st.name as type_name, p.price, phso.*\n" +
            "from \n" +
            "client_services sc,\n" +
            "services s,\n" +
            "service_types st,\n" +
            "phone_service_options phso,\n" +
            "plans p\n" +
            "where \n" +
            "sc.account_id = ? /* Account Id */\n" +
            "and sc.service_id = s.id\n" +
            "and s.type_id = 102 /* Phone */\n" +
            "and st.id = s.type_id\n" +
            "and p.id = s.plan_id\n" +
            "and phso.service_id = sc.service_id";

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

    public static String GET_SUPPORT_SERVICE_INFO =
            "select sc.id as category_id, sc.name as category_name, si_ph.value as phone_number, si_email.value as email\n" +
            "from\n" +
            "  support_categories sc,\n" +
            "  support_info si_ph,\n" +
            "  support_info si_email\n" +
            "where \n" +
            "  sc.id in (\n" +
            "    1, 2, 3, 4\n" +
            "  )\n" +
            "  and si_ph.category_id = sc.id\n" +
            "  and si_ph.attr_id = 100 /* Phone Number */\n" +
            "  and si_email.category_id = sc.id\n" +
            "  and si_email.attr_id = 101 /* Email */";

    public static String GET_SUPPORT_SOCIAL_INFO =
            "select si.category_id, sc.name as category_name, si.attr_id, sa.name, si.value as link\n" +
            "from\n" +
            "  support_info si,\n" +
            "  support_attributes sa,\n" +
            "  support_categories sc\n" +
            "where \n" +
            "  si.category_id = 5\n" +
            "  and si.attr_id in (\n" +
            "    102, 103\n" +
            "  )\n" +
            "  and sa.id = si.attr_id\n" +
            "  and sc.id = si.category_id";

    public static String GET_HARDWARE_FOR_ACCOUNT_BY_SERVICE_TYPE_ID =
            "select * \n" +
            "from hardware\n" +
            "where \n" +
            "account_id = ? /* Account ID */\n" +
            "and service_type_id = ? /* Service Type ID */";

    public static String DEACTIVATE_HARDWARE_FOR_ACCOUNT_BY_SERVICE_TYPE_ID =
            "update hardware\n" +
            "set status = 'Inactive'\n" +
            "where \n" +
            "account_id = ?\n" +
            "and service_type_id = ?";

    public static String DEACTIVATE_SERVICE_FOR_ACCOUNT_BY_SERVICE_TYPE_ID =
            "delete\n" +
            "from client_services\n" +
            "where account_id = ? \n" +
            "and service_id in (\n" +
            "  select service_id\n" +
            "  from services\n" +
            "  where type_id = ?\n" +
            ")";

    public static String GET_SERVICE_PLANS_BY_SERVICE_TYPE_ID =
            "select s.id, s.name, s.type_id, st.name as type_name, p.price\n" +
            "from \n" +
            "services s,\n" +
            "service_types st,\n" +
            "plans p\n" +
            "where\n" +
            "s.type_id = ?\n" +
            "and s.type_id = st.id\n" +
            "and p.id = s.plan_id";

    public static String GET_INTERNET_OPTIONS_BY_SERVICE_ID =
            "select * \n" +
            "from \n" +
            "internet_service_options\n" +
            "where \n" +
            "service_id = ?";

    public static String GET_TV_OPTIONS_BY_SERVICE_ID =
            "select * \n" +
            "from \n" +
            "tv_service_options\n" +
            "where \n" +
            "service_id = ?";

    public static String GET_PHONE_OPTIONS_BY_SERVICE_ID =
            "select * \n" +
            "from \n" +
            "phone_service_options\n" +
            "where \n" +
            "service_id = ?";
}
