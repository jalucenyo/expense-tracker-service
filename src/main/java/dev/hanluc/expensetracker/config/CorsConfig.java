package dev.hanluc.expensetracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedOrigins("https://expenses-dev.hanluc.dev")
      .allowedOrigins("http://expenses-dev.hanluc.dev")
      .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
  }

}
