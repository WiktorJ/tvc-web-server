package tvc;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by wjurasz on 19.07.16.
 * This interface should be implemented if queries should be performed from java level instead of pure sql functions in database.
 * Implementation should be registered as repository in application. To do this one have to add @Repository(value = "topologyName")
 * annotation at top of interface implementation. Value is name under with topology will exist on server.
 * In order to use such implementation one have to provide the same name in dataManager.topologyDetails.name in configuration object on client site.
 */
public interface VisualisationDataDao {
    // TODO: Add customQueryParameters here
    List<Map<String, Object>> getAll(Map<String, String> customArguments) throws IOException;

    List<Map<String, Object>> getById(String nodeId, long distance, Map<String, String> customArguments) throws IOException;

}
