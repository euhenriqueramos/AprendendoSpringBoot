package br.com.henrique.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	@Bean
	OpenAPI customOpensAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Aprendendo Java Spring")
						.version("v1")
						.description("Curso")
						.termsOfService("http://google.com.br")
						.license(new License().name("Apache 2.0")
								.url("http://google.com.br"))
						);
	}

}
