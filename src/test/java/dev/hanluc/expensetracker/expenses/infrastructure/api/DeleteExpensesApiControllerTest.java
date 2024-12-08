package dev.hanluc.expensetracker.expenses.infrastructure.api;

import dev.hanluc.expensetracker.TestContainersConfiguration;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpensePaginatedResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.BDDAssertions.then;

@ApplicationModuleTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import({TestContainersConfiguration.class})
@Sql(scripts = "classpath:db/expense/data-init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "classpath:db/expense/data-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class DeleteExpensesApiControllerTest {

  @Autowired
  TestRestTemplate restTemplate;

  @Test
  @Sql(scripts = "classpath:db/expense/data-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  @Sql(scripts = "classpath:db/expense/data-init.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void should_delete_expense() {

    restTemplate.delete("/expenses/" + "8b540e0f-0a73-4459-8b91-8f3b9d71ec30");

    ResponseEntity<ExpensePaginatedResponse> response = restTemplate.getForEntity("/expenses", ExpensePaginatedResponse.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    // TODO: Add assert get expense by id to check if the expense was deleted
  }

  @Test
  void should_return_error_when_id_format_incorrect() {

    ResponseEntity<ProblemDetail> response = restTemplate
      .exchange("/expenses/" + "INCORRECT_ID", HttpMethod.DELETE, null, ProblemDetail.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

}