package company;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "You tube Api ", version = "3.0", description = "You tube Information"))
public class YoutubeApplication {
	public static void main(String[] args) {
		SpringApplication.run(YoutubeApplication.class, args);
	}
}