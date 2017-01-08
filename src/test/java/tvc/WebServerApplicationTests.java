package tvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebServerApplicationTests {


	@Bean
	public VisualisationService visualisationService() {
		return new VisualisationService();
	}

	@Test
	public void contextLoads() {
	}

}
