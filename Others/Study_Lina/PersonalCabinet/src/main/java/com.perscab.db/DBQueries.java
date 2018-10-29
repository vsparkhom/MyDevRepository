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
            "  max(p.due_date) as due_date\n" +
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
            "select ic.id as category_id, ic.name as category_name, inf_ph.value as phone_number, inf_email.value as email\n" +
            "from\n" +
            "  info_categories ic,\n" +
            "  information inf_ph,\n" +
            "  information inf_email\n" +
            "where \n" +
            "  ic.id in (\n" +
            "    1, 2, 3, 4\n" +
            "  )\n" +
            "  and inf_ph.category_id = ic.id\n" +
            "  and inf_ph.attr_id = 100 /* Phone Number */\n" +
            "  and inf_email.category_id = ic.id\n" +
            "  and inf_email.attr_id = 101 /* Email */";

    public static String GET_SUPPORT_SOCIAL_INFO =
            "select inf.category_id, ic.name as category_name, inf.attr_id, ia.name, inf.value as link\n" +
            "from\n" +
            "  information inf,\n" +
            "  info_attributes ia,\n" +
            "  info_categories ic\n" +
            "where \n" +
            "  inf.category_id = 5\n" +
            "  and inf.attr_id in (\n" +
            "    102, 103\n" +
            "  )\n" +
            "  and ia.id = inf.attr_id\n" +
            "  and ic.id = inf.category_id";

    public static String GET_HARDWARE_FOR_ACCOUNT_BY_SERVICE_TYPE_ID =
            "select * \n" +
            "from hardware\n" +
            "where \n" +
            "account_id = ? /* Account ID */\n" +
            "and service_type_id = ? /* Service Type ID */";

    public static String DEACTIVATE_HARDWARE_FOR_ACCOUNT_BY_SERVICE_ID =
            "update hardware\n" +
            "set status = 'Inactive', account_id = null\n" +
            "where \n" +
            "account_id = ?\n" +
            "and service_type_id in (\n" +
            "  select type_id\n" +
            "  from \n" +
            "  services\n" +
            "  where \n" +
            "  id = ?\n" +
            ")";

    public static String DEACTIVATE_SERVICE_FOR_ACCOUNT_BY_SERVICE_ID =
            "delete\n" +
            "from client_services\n" +
            "where account_id = ? \n" +
            "and service_id = ?";

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

    public static String FIND_AVAILABLE_HW =
            "select min(hw.id) as hardware_id\n" +
            "from \n" +
            "  services s,\n" +
            "  hardware hw\n" +
            "where \n" +
            "  s.id = ?\n" +
            "  and hw.service_type_id = s.type_id\n" +
            "  and hw.account_id is null\n" +
            "  and status = 'Inactive'";

    public static String RESERVE_HW_FOR_ACCOUNT =
            "update hardware\n" +
            "set \n" +
            "  account_id = ?,\n" +
            "  status = 'Active'\n" +
            "where\n" +
            "  id = ?";

    public static String ACTIVATE_SERVICE_FOR_ACCOUNT =
            "insert into client_services values(? /* account_id */, ? /* service_id */)";
}
