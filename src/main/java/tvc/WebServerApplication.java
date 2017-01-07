package tvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;

@SpringBootApplication
public class WebServerApplication extends WebMvcAutoConfiguration {

	public static void main(String[] args) {
		SpringApplication.run(WebServerApplication.class, args);
	}

}
