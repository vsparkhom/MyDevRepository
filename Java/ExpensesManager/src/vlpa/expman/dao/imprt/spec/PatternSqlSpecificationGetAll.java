package vlpa.expman.dao.imprt.spec;

import vlpa.expman.dao.DBQueries;
import vlpa.expman.dao.SqlSpecification;

public class PatternSqlSpecificationGetAll implements SqlSpecification {

    @Override
    public String toSqlClause() {
        return DBQueries.SQLiteDBQueries.GET_EXPENSES_MAPPING;
    }

}
