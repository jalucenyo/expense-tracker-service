package dev.hanluc.expensetracker;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;

import java.util.Collections;
import java.util.function.Supplier;

@TestConfiguration
public class TestRestTemplateConfig {

  @Bean
  public RestTemplateBuilder restTemplate(Supplier<String> tokenSupplier) {
    ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
      request.getHeaders().add("Authorization", "Bearer " + tokenSupplier.get());
      return execution.execute(request, body);
    };

    return new RestTemplateBuilder().interceptors(Collections.singletonList(interceptor));
  }

  @Bean
  public Supplier<String> tokenSupplier() {
    return new TokenProvider();
  }

}