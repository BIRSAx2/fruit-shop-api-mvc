package dev.mouhieddine.springmvcrestexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author : Mouhieddine.dev
 * @since : 1/19/2021, Tuesday
 **/
@EnableSwagger2
@Configuration
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .pathMapping("/")
            .apiInfo(metaData());
  }

  private ApiInfo metaData() {

    Contact contact = new Contact("Mouhieddine Sabir", "https://mouhieddine.dev",
            "info@mouhieddine.dev");

    return new ApiInfo(
            "Fruit Shop API",
            "Spring Boot Fruit Shop implementation",
            "1.0",
            "Terms of Service: blah",
            contact,
            "Apache License Version 2.0",
            "https://www.apache.org/licenses/LICENSE-2.0",
            new ArrayList<>());
  }
}
