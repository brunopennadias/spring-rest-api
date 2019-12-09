package digital.b2w.planets;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

@SpringBootApplication
public class ApiPlanetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPlanetsApplication.class, args);
	}
	
	@Bean
	public UiConfiguration uiConfig() {
	    return UiConfigurationBuilder.builder()
	             .displayRequestDuration( true ) 
	             .validatorUrl( StringUtils.EMPTY ) // Disable the validator to avoid "Error" at the bottom of the Swagger UI page
	             .build();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
