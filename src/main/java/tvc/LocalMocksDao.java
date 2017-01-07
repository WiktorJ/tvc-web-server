package tvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wjurasz on 17.08.16.
 */
@Repository(value = "graphDaoMock")
public class LocalMocksDao implements VisualisationDataDao {

    @Override
    public List<Map<String, Object>> getAll(Map<String, String> customArguments) throws IOException {
        return new ObjectMapper().readValue(new String(Files.readAllBytes(Paths.get("mocked_data/" + customArguments.get("fileName") + ".json")), Charset.forName("UTF-8")),
                new TypeReference<List<HashMap<String, Object>>>() {
                });
    }

    @Override
    public List<Map<String, Object>> getById(String nodeId, long distance, Map<String, String> customArguments) throws IOException {
        return new ObjectMapper().readValue(new String(Files.readAllBytes(Paths.get("mocked_data/" + customArguments.get("fileName") + ".json")), Charset.forName("UTF-8")),
                new TypeReference<List<HashMap<String, Object>>>() {
                });
    }

}
