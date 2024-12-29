package dev.hanluc.expensetracker.budgets.infrastructure.api;

import dev.hanluc.expensetracker.TestContainersConfiguration;
import dev.hanluc.expensetracker.TestRestTemplateConfig;
import dev.hanluc.expensetracker.TokenProvider;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetCreateRequest;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.time.OffsetDateTime;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import({TestContainersConfiguration.class, TestRestTemplateConfig.class})
@Sql(scripts = "classpath:db/budget/data-init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "classpath:db/budget/data-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class PostBudgetsApiControllerTest {

  @Autowired
  TestRestTemplate restTemplate;

  @Autowired
  private TokenProvider tokenProvider;

  @BeforeEach
  public void setUp() {
    tokenProvider.validToken();
  }

  @Test
  void should_create_budget() {
    BudgetCreateRequest requesst = new BudgetCreateRequest()
      .name("Budget 1")
      .amount(new Money().value(983L).exponent(2))
      .startDate(OffsetDateTime.now())
      .endDate(OffsetDateTime.now().plusMonths(1))
      .category("Category 1");

    ResponseEntity<Void> response = restTemplate.postForEntity("/budgets", requesst, Void.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    then(response.getHeaders().getLocation()).isNotNull();
    String uuidRegex = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$";
    then(response.getHeaders().getLocation().toString()).containsPattern(uuidRegex);
  }

}