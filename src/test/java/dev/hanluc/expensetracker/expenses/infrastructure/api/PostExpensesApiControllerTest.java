package dev.hanluc.expensetracker.expenses.infrastructure.api;

import dev.hanluc.expensetracker.TestContainersConfiguration;
import dev.hanluc.expensetracker.TestRestTemplateConfig;
import dev.hanluc.expensetracker.TokenProvider;
import dev.hanluc.expensetracker.expenses.mother.ExpenseCreateRequestMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import({TestContainersConfiguration.class, TestRestTemplateConfig.class})
@Sql(scripts = "classpath:db/expense/data-init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "classpath:db/expense/data-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class PostExpensesApiControllerTest {

  @Autowired
  TestRestTemplate restTemplate;

  @Autowired
  private TokenProvider tokenProvider;

  @BeforeEach
  public void setUp() {
    tokenProvider.validToken();
  }

  @Test
  void should_create_expense() {
    final var request = ExpenseCreateRequestMother.random().create();

    ResponseEntity<Void> response = restTemplate.postForEntity("/expenses", request, Void.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    then(response.getHeaders().getLocation()).isNotNull();
    String uuidRegex = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$";
    then(response.getHeaders().getLocation().toString()).containsPattern(uuidRegex);
  }

  @Test
  void should_return_error_when_description_is_empty() {
    final var request = ExpenseCreateRequestMother.random().withEmptyField("description");

    ResponseEntity<ProblemDetail> response = restTemplate.postForEntity("/expenses", request, ProblemDetail.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

}