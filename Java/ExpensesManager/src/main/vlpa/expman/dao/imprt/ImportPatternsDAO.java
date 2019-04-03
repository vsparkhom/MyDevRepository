package vlpa.expman.dao.imprt;

import vlpa.expman.model.ImportPattern;

import java.util.List;

public interface ImportPatternsDAO {

    List<ImportPattern> queryPatterns(String query);

    void addPattern(ImportPattern p);

    void removePattern(long id);

    void updatePattern(ImportPattern pattern);

}
