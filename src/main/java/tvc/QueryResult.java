package tvc;

import java.util.List;
import java.util.Map;

/**
 * Created by kpenar on 2016-08-10.
 */
public class QueryResult {

    private List<Map<String, Object>> result;

    public QueryResult(List<Map<String, Object>> result) {
        this.result = result;
    }

    public List<Map<String, Object>> getResult() {
        return result;
    }

    public void setResult(List<Map<String, Object>> result) {
        this.result = result;
    }
}
