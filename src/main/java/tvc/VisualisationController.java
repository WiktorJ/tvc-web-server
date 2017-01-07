package tvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by wjurasz on 19.07.16.
 */
@RestController
@RequestMapping(path = "/api/tvc/nodes/{topologyName}")
public class VisualisationController {

    @Autowired
    private VisualisationService visualisationService;

    /**
     * @param topologyName Name of topology in database or @VisualisationDataDao implementation bean.
     * @param queryName    Name of the query to be performed in database.
     * @return Flat json structure representing requested set of data.
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Map<String, Object>> getNodes(@PathVariable String topologyName,
                                              @RequestParam(required = false) String queryName,
                                              @RequestParam Map<String, String> queryCustomParameters) throws IOException, SQLException {
        return visualisationService.getAll(queryName, topologyName, queryCustomParameters);
    }

    /**
     * @param topologyName          Name of topology in database or @VisualisationDataDao implementation bean.
     * @param nodeId                Id of node which will be consider root for this request.
     * @param queryName             Name of the query to be performed in database.
     * @param distance              Distance counting from node with @nodeId.
     *                              All nodes that have distance less or equal to given should be retrieved from topology.
     * @param queryCustomParameters Custom parameters that can be passed to database query or @VisualisationDataDao implementation
     * @return Flat json structure representing requested set of data.
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{nodeId}")
    public List<Map<String, Object>> getByDistance(@PathVariable String topologyName,
                                                   @PathVariable String nodeId,
                                                   @RequestParam(required = false) String queryName,
                                                   @RequestParam Long distance,
                                                   @RequestParam Map<String, String> queryCustomParameters) throws IOException, SQLException {
        return visualisationService.getByDistance(queryName, topologyName, nodeId, distance, queryCustomParameters);
    }


}
