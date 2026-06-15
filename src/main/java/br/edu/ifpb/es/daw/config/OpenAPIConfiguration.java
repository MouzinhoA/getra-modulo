package br.edu.ifpb.es.daw.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenAPIConfiguration {

	@Bean
	OpenAPI customOpenAPI() {
	    return new OpenAPI()
	            .info(new Info()
	                    .title("API Getra")
	                    .description("API REST do sistema Getra")
	                    .version("1.0.0")
	                    .contact(new Contact()
	                            .name("Código no github")
	                            .url("https://github.com/MouzinhoA/getra-modulo"))
	                    .license(new License()
	                            .name("Apache 2.0")
	                            .url("http://www.apache.org/licenses/LICENSE-2.0")))
				.tags(Arrays.asList(new Tag().name("getra").description("API Getra")));
    }
}
