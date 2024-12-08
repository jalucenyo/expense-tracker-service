package dev.hanluc.expensetracker.budgets.infrastructure.api;

import dev.hanluc.expensetracker.TestContainersConfiguration;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetResponse;
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
@Sql(scripts = "classpath:db/budget/data-init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "classpath:db/budget/data-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class GetBudgetsApiControllerTest {

  @Autowired
  TestRestTemplate restTemplate;

  @Test
  void should_find_budget_by_id() {
    final var budgetId = "22a2b9a8-a538-4a2a-ad2d-5e2dfca9a972";

    ResponseEntity<BudgetResponse> response = restTemplate.getForEntity("/budgets/" + budgetId, BudgetResponse.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    then(response.getBody()).isNotNull();
    then(response.getBody().getId()).isEqualTo(budgetId);
  }

}