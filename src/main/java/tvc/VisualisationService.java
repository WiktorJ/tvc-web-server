package tvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by wjurasz on 19.07.16.
 * This service is responsible for choosing right data source for request.
 * It will first look for dao implementation with given topologyName, if there is no match - generic query repository
 * will be used to perform query.
 */
@Service(value = "visualisationService")
public class    VisualisationService {

    @Autowired
    private QueryResultRepository queryResultRepository;

    @Autowired(required=false)
    private Map<String, VisualisationDataDao> dataDaoMap;

    public List<Map<String, Object>> getAll(String queryName,
                                            String topologyName,
                                            Map<String, String> queryCustomParameters) throws IOException, SQLException {
        if (dataDaoMap != null && dataDaoMap.containsKey(topologyName)) {
            return dataDaoMap.get(topologyName).getAll(queryCustomParameters);
        }
        queryCustomParameters.remove("queryName");
        return queryResultRepository.executeQuery(queryName, topologyName, queryCustomParameters).getResult();
    }

    public List<Map<String, Object>> getByDistance(String queryName,
                                                   String topologyName,
                                                   String nodeId,
                                                   long distance,
                                                   Map<String, String> queryCustomParameters) throws IOException, SQLException {
        if (dataDaoMap != null && dataDaoMap.containsKey(topologyName)) {
            return dataDaoMap.get(topologyName).getById(nodeId, distance, queryCustomParameters);
        }
        queryCustomParameters.put("node_id", nodeId);
        queryCustomParameters.remove("queryName");
        return queryResultRepository.executeQuery(queryName, topologyName, queryCustomParameters).getResult();
    }
}
