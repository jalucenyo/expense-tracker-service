package dev.hanluc.expensetracker.budget.infraestructure.api;

import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetDto;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetPaginatedDto;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.MoneyDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.OffsetDateTime;

import static org.assertj.core.api.BDDAssertions.then;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:db/budget/data-init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "classpath:db/budget/data-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class BudgetApiControllerTest {

  @Container
  @ServiceConnection
  static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(
    DockerImageName.parse("postgres:latest"));

  @Autowired
  TestRestTemplate restTemplate;

  @Test
  void should_find_budget_by_id() {
    final var budgetId = "22a2b9a8-a538-4a2a-ad2d-5e2dfca9a972";

    ResponseEntity<BudgetDto> response = restTemplate.getForEntity("/budgets/" + budgetId, BudgetDto.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    then(response.getBody()).isNotNull();
    then(response.getBody().getId()).isEqualTo(budgetId);
  }

  @Test
  void should_find_all_budgets() {
    ResponseEntity<BudgetPaginatedDto> response = restTemplate.getForEntity("/budgets", BudgetPaginatedDto.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    then(response.getBody()).isNotNull();
    then(response.getBody().getTotalElements()).isEqualTo(10);
    then(response.getBody().getContent()).hasSize(5);
  }

  @Test
  void should_create_budget() {
    BudgetDto createBudget = new BudgetDto()
      .name("Budget 1")
      .amount(new MoneyDto().value(983L).exponent(2))
      .startDate(OffsetDateTime.now())
      .endDate(OffsetDateTime.now().plusMonths(1));

    ResponseEntity<Void> response = restTemplate.postForEntity("/budgets", createBudget, Void.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    then(response.getHeaders().getLocation()).isNotNull();
    String uuidRegex = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$";
    then(response.getHeaders().getLocation().toString()).containsPattern(uuidRegex);
  }

}
