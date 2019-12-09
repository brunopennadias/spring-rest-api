package digital.b2w.planets.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Value("${api.key}")
	private String key;

	@Bean
	public Docket api() {
		//Adding Header
//        ParameterBuilder aParameterBuilder = new ParameterBuilder();
//        aParameterBuilder.name("Authorization").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        
//        List<Parameter> aParameters = new ArrayList<Parameter>();
//        aParameters.add(aParameterBuilder.build());
		
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("digital.b2w.planets.controller"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
				//.globalOperationParameters(aParameters);
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("api-planets REST", "API", "B2W", "Terms of service",
				new Contact("B2W Digital", "https://ri.b2w.digital/", "myeaddress@company.com"), "License of API", "API license URL",
				Collections.emptyList());
	}
	
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
