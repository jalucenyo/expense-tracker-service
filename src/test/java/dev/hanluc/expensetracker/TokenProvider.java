package dev.hanluc.expensetracker;

import dev.hanluc.expensetracker.accounts.infrastructure.api.dto.SignInRequest;
import dev.hanluc.expensetracker.accounts.infrastructure.api.dto.SignInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.function.Supplier;

@Service
public class TokenProvider implements Supplier<String> {

  @Autowired
  private ServletWebServerApplicationContext webServerContext;

  private String token;

  @Override
  public String get() {
    return token;
  }

  public void validToken() {
    signInWithUser("test1@test.local", "1234");
  }

  private void signInWithUser(String email, String password) {
    int port = webServerContext.getWebServer().getPort();

    final var signInResponse = RestClient.builder()
      .baseUrl("http://localhost:" + port).build()
      .post()
      .uri("/accounts/sign-in")
      .body(new SignInRequest().email(email).password(password))
      .retrieve()
      .body(SignInResponse.class);
    this.token = signInResponse.getToken();
  }

}
