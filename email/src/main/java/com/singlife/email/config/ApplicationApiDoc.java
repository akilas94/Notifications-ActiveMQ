package com.singlife.email.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationApiDoc {

  @Bean
  public OpenAPI applicationAPI() {
    return new OpenAPI().info(
        new Info()
            .title("Rename This API")
            .description("This is a template service - remember to change this description")
            .version("v1.0.0")
    );
  }

}
