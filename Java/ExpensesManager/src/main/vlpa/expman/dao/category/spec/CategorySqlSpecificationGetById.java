package vlpa.expman.dao.category.spec;

import vlpa.expman.dao.DBQueries;
import vlpa.expman.dao.SqlSpecification;

public class CategorySqlSpecificationGetById implements SqlSpecification {



    private long id;

    public CategorySqlSpecificationGetById(long id) {
        this.id = id;
    }

    @Override
    public String toSqlClause() {
        return String.format(DBQueries.SQLiteDBQueries.GET_CATEGORY_BY_ID, id);
    }
}
