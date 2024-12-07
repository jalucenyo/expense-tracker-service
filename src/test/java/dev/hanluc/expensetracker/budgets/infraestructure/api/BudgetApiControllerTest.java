package dev.hanluc.expensetracker.budgets.infraestructure.api;

import dev.hanluc.expensetracker.TestContainersConfiguration;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetCreateRequest;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetPaginatedResponse;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetResponse;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.Money;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.OffsetDateTime;

import static org.assertj.core.api.BDDAssertions.then;

@Testcontainers
@ApplicationModuleTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestContainersConfiguration.class)
@Sql(scripts = "classpath:db/budget/data-init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "classpath:db/budget/data-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class BudgetApiControllerTest {

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

  @Disabled("This test is failing because the endpoint is not implemented")
  @Test
  void should_find_all_budgets() {
    ResponseEntity<BudgetPaginatedResponse> response = restTemplate.getForEntity("/budgets", BudgetPaginatedResponse.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    then(response.getBody()).isNotNull();
    then(response.getBody().getTotalElements()).isEqualTo(10);
    then(response.getBody().getContent()).hasSize(5);
  }

  @Test
  void should_create_budget() {
    BudgetCreateRequest createBudget = new BudgetCreateRequest()
      .name("Budget 1")
      .amount(new Money().value(983L).exponent(2))
      .startDate(OffsetDateTime.now())
      .endDate(OffsetDateTime.now().plusMonths(1))
      .category("Category 1");

    ResponseEntity<Void> response = restTemplate.postForEntity("/budgets", createBudget, Void.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    then(response.getHeaders().getLocation()).isNotNull();
    String uuidRegex = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$";
    then(response.getHeaders().getLocation().toString()).containsPattern(uuidRegex);
  }

}
