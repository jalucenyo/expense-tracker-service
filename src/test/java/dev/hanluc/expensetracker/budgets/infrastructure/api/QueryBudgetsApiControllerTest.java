package dev.hanluc.expensetracker.budgets.infrastructure.api;

import static org.assertj.core.api.BDDAssertions.then;

import dev.hanluc.expensetracker.TestContainersConfiguration;
import dev.hanluc.expensetracker.TestRestTemplateConfig;
import dev.hanluc.expensetracker.TokenProvider;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetPaginatedResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import({TestContainersConfiguration.class, TestRestTemplateConfig.class})
@Sql(scripts = "classpath:db/budget/data-init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "classpath:db/budget/data-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class QueryBudgetsApiControllerTest {

  @Autowired
  TestRestTemplate restTemplate;

  @Autowired
  TokenProvider tokenProvider;

  @BeforeEach
  public void setUp() {
    tokenProvider.validToken();
  }

  @Test
  void should_find_all_budgets() {
    ResponseEntity<BudgetPaginatedResponse> response = restTemplate.getForEntity("/budgets", BudgetPaginatedResponse.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    then(response.getBody()).isNotNull();
    then(response.getBody().getTotalElements()).isEqualTo(4);
    then(response.getBody().getContent()).hasSize(4);
  }

}
