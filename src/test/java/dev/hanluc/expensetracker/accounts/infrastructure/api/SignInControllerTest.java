package dev.hanluc.expensetracker.accounts.infrastructure.api;

import dev.hanluc.expensetracker.TestContainersConfiguration;
import dev.hanluc.expensetracker.accounts.infrastructure.api.dto.SignInRequest;
import dev.hanluc.expensetracker.accounts.infrastructure.api.dto.SignInResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.BDDAssertions.then;

@ApplicationModuleTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestContainersConfiguration.class)
@Sql(scripts = "classpath:db/accounts/data-init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "classpath:db/accounts/data-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class SignInControllerTest {

  @Autowired
  TestRestTemplate restTemplate;

  @Test
  void should_error_unauthorized_when_with_invalid_credentials() {
    final var request = new SignInRequest().email("notexist@test.local").password("1234");

    ResponseEntity<SignInResponse> response = restTemplate.postForEntity("/accounts/sign-in", request, SignInResponse.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
  }

  @Test
  void should_sign_in_when_valid_credentials() {
    final var request = new SignInRequest().email("test1@test.local").password("1234");

    ResponseEntity<SignInResponse> response = restTemplate.postForEntity("/accounts/sign-in", request, SignInResponse.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    then(response.getBody()).isNotNull();
    then(response.getBody().getToken()).isNotNull();
  }

}