package vlpa.expman.dao.imprt;

import vlpa.expman.model.ImportPattern;

import java.util.List;

public interface ImportExpensesDAO {

    List<ImportPattern> getMapping();

    void addPattern(ImportPattern p);

}
