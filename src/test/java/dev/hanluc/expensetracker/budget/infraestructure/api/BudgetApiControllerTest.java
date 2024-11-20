package dev.hanluc.expensetracker.budget.infraestructure.api;

import dev.hanluc.expensetracker.api.spec.dto.BudgetDto;
import dev.hanluc.expensetracker.api.spec.dto.BudgetPaginatedDto;
import dev.hanluc.expensetracker.api.spec.dto.MoneyDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.BDDAssertions.then;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BudgetApiControllerTest {

  @Container
  @ServiceConnection
  static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(
    DockerImageName.parse("postgres:latest"));

  @Autowired
  TestRestTemplate restTemplate;

  @Test
  void should_find_budget_by_id() {
    final var budgetId = "0989de36-843b-4be8-882a-4ec9b219b1f3";

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
    BudgetDto budgetDto = new BudgetDto()
      .name("Budget 1")
      .amount(new MoneyDto().value(983L).exponent(2));

    ResponseEntity<Void> response = restTemplate.postForEntity("/budgets", budgetDto, Void.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    then(response.getHeaders().getLocation()).isNotNull();
    String uuidRegex = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$";
    then(response.getHeaders().getLocation().toString()).containsPattern(uuidRegex);
  }

}
