package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI customOpenApi() {
		OpenAPI openAPI = new OpenAPI()
				.components(new Components().addSecuritySchemes("bearerAuth",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
				.info(new Info().title("ProdutosAPI")
						.description("API RESTful para controle de produtos.").version("v1")
						.contact(new Contact().name("Samuel Maciel").email("samuelmsilva@outlook.com.br")
								.url("https://github.com/samuelmsilva2v"))
						.license(new License().name("MIT License").url("https://opensource.org/licenses/MIT")))
				.addSecurityItem(new SecurityRequirement().addList("bearerAuth"));

		return openAPI;
	}
}
