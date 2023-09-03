package ju.soumyajit.SpeciesCaptureBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class SpeciesCaptureBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpeciesCaptureBackendApplication.class, args);
	}

}
