package vlpa.expman.dao.category.spec;

import vlpa.expman.dao.DBQueries;
import vlpa.expman.dao.SqlSpecification;

public class CategorySqlSpecificationGetAll implements SqlSpecification {
    @Override
    public String toSqlClause() {
        return DBQueries.SQLiteDBQueries.GET_ALL_CATEGORIES;
    }
}
