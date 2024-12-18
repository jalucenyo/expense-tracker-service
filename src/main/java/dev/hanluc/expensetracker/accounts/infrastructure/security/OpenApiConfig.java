package dev.hanluc.expensetracker.accounts.infrastructure.security;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
      .components(new io.swagger.v3.oas.models.Components()
        .addSecuritySchemes("bearerAuth", new SecurityScheme()
          .name("bearerAuth")
          .type(SecurityScheme.Type.HTTP)
          .scheme("bearer")
          .bearerFormat("JWT")))
      .paths(new io.swagger.v3.oas.models.Paths());
  }

}
