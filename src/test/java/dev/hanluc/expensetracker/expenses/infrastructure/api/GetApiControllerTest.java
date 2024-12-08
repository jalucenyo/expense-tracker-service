package dev.hanluc.expensetracker.expenses.infrastructure.api;

import dev.hanluc.expensetracker.TestContainersConfiguration;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpenseResponse;
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
@Import({TestContainersConfiguration.class})
@Sql(scripts = "classpath:db/expense/data-init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "classpath:db/expense/data-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class GetApiControllerTest {

  @Autowired
  TestRestTemplate restTemplate;

  @Test
  void should_find_expense_by_id() {
    final var expenseId = "0989de36-843b-4be8-882a-4ec9b219b1f3";

    ResponseEntity<ExpenseResponse> response = restTemplate.getForEntity("/expenses/" + expenseId, ExpenseResponse.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    then(response.getBody()).isNotNull();
    then(response.getBody().getId()).isEqualTo(expenseId);
  }

}