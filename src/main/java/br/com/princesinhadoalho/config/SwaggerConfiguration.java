package br.com.princesinhadoalho.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

			@Bean
			public Docket api() {
				return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.basePackage("br.com.princesinhadoalho"))
		            		.paths(PathSelectors.ant("/**"))
					.build()
					.apiInfo(apiInfo());		
			}	
			private ApiInfo apiInfo() {
			    return new ApiInfo(
			      "Sistema Princesinha do Alho", 
			      "Sistema desenvolvido em Java Spring Boot, Hibernate e JPA.", 
			      "Versão 1.0",
			      "",     
			      new Contact("Eric Rangel", "https://www.linkedin.com/in/ericrangelrj/", 
			    		  	"b.ericrangel@hotmail.com"), 
			      			"", 
			      			"",  
			      			Collections.emptyList());
			}	
	
}
