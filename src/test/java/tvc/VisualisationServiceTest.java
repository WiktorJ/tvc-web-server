package tvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by wiktor on 08/01/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { QueryResultRepository.class})
@EnableConfigurationProperties
public class VisualisationServiceTest {

    @InjectMocks
    private VisualisationService visualisationService;

    @Mock
    private QueryResultRepository queryResultRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws SQLException, IOException {
        Map<String, String> queryCustomParameters = new HashMap<>();
        queryCustomParameters.put("A", "B");
        queryCustomParameters.put("queryName", "B");
        given(this.queryResultRepository.executeQuery(anyString(),  anyString(), anyMapOf(String.class, String.class)))
                .willReturn(new QueryResult(new ArrayList<>()));

        visualisationService.getAll("", "", queryCustomParameters);

        assertThat(queryCustomParameters.containsKey("queryName")).isFalse();

    }

    @Test
    public void testGetBYDistance() throws SQLException, IOException {
        Map<String, String> queryCustomParameters = new HashMap<>();
        queryCustomParameters.put("A", "B");
        queryCustomParameters.put("queryName", "B");
        given(this.queryResultRepository.executeQuery(anyString(),  anyString(), anyMapOf(String.class, String.class)))
                .willReturn(new QueryResult(new ArrayList<>()));

        visualisationService.getByDistance("", "", "some_id", 0L, queryCustomParameters);

        assertThat(queryCustomParameters.containsKey("queryName")).isFalse();
        assertThat(queryCustomParameters.get("node_id")).isEqualTo("some_id");

    }

}
