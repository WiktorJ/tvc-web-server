package tvc;

import java.sql.SQLException;
import java.util.Map;

public interface QueryResultRepository {

    QueryResult executeQuery(String queryName, String domain, Map<String, String> filters) throws SQLException;
}
