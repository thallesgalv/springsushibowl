package com.sushibowl.sushibowl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI sushibowlOpenAPI() {
		return new OpenAPI().info(new Info().title("API do Projeto Sushibowl")
				.description("Desenvolvido por Felipe Gameiro, Karoline Teodoro, Pedro Luiz Salvino, Thalles Galvão e Wesley Nascimento")
				.version("v0.0.1")
				.contact(new Contact()
						.name("Pedro Salvino").email("pedro.salvino@fatec.sp.gov.br"))
				.license(new License()
						.name("Apache 2.0").url("http://springdoc.org")));
	 }
}
