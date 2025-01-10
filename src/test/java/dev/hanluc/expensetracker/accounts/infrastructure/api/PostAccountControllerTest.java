package dev.hanluc.expensetracker.accounts.infrastructure.api;

import dev.hanluc.expensetracker.TestContainersConfiguration;
import dev.hanluc.expensetracker.accounts.infrastructure.api.dto.AccountCreateRequest;
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
class PostAccountControllerTest {

  @Autowired
  TestRestTemplate restTemplate;

  @Test
  void should_error_when_account_already_exists() {
    final var request = new AccountCreateRequest().email("test1@test.local").password("1234");

    ResponseEntity<Void> response = restTemplate.postForEntity("/accounts", request, Void.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
  }

  @Test
  void should_create_account_when_valid_request() {
    final var request = new AccountCreateRequest().email("new_user@test.local").password("1234");

    ResponseEntity<Void> response = restTemplate.postForEntity("/accounts", request, Void.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    then(response.getHeaders().getLocation()).isNotNull();
  }

}