package tvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;


/**
 * This is just prototype repository.
 * Do not use it in production environment as it uses unsafe SQL operations.
 */
@Repository
public class QueryResultRepositoryImpl implements QueryResultRepository {
    private static final Logger logger = LoggerFactory.getLogger(QueryResultRepository.class);

    private final DataSource dataSource;

    @Autowired
    public QueryResultRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public QueryResult executeQuery(String queryName, String domain, Map<String, String> filters) throws SQLException {
        ResultSet resultSet;
        List<Map<String, Object>> result = new LinkedList<>();
        try {
            Statement statement = dataSource.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "
                    + queryName +
                    "(" +  createQuery(filters) + ")");
            ResultSetMetaData metaData = resultSet.getMetaData();
            List<String> columnNames = new ArrayList<>(metaData.getColumnCount());
            for (int i = 1; i <=  metaData.getColumnCount(); i++) {
                columnNames.add(metaData.getColumnName(i));
            }
            while (resultSet.next()) {
                HashMap<String, Object> row = new HashMap<>(metaData.getColumnCount());
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    row.put(columnNames.get(i-1), resultSet.getString(i));
                }
                result.add(row);
            }
        } catch (SQLException e) {
            logger.error("There was exception during SQL query execution", e);
            throw e;
        }
        return new QueryResult(result);
    }

    private String createQuery(Map<String, String> filters) {
        StringBuffer queryKeys = new StringBuffer("ARRAY[");
        StringBuffer queryValues = new StringBuffer("ARRAY[");
        int size = filters.size();
        int i = 0;
        filters.forEach((key, value) -> {
            queryKeys.append("'");
            queryKeys.append(key);
            queryKeys.append("'");
            queryValues.append("'");
            queryValues.append(key);
            queryValues.append("'");
            if(i < size -1) {
                queryKeys.append(", ");
                queryValues.append(", ");
            }
        });
        queryKeys.append("]::TEXT[], ");
        queryValues.append("]::TEXT[]");
        return queryKeys.toString() + queryValues.toString();
    }

}