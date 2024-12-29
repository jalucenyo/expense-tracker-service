package dev.hanluc.expensetracker.expenses.infrastructure.api;

import dev.hanluc.expensetracker.TestContainersConfiguration;
import dev.hanluc.expensetracker.TestRestTemplateConfig;
import dev.hanluc.expensetracker.TokenProvider;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpenseCreateRequest;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import({TestContainersConfiguration.class, TestRestTemplateConfig.class})
@Sql(scripts = "classpath:db/expense/data-init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "classpath:db/expense/data-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class ExpensesApiControllerTest {

  @Autowired
  TestRestTemplate restTemplate;

  @Autowired
  private TokenProvider tokenProvider;

  @MockBean
  PostExpensesApiController postExpensesApiController;
  @MockBean
  GetExpensesApiController getExpensesApiController;
  @MockBean
  QueryExpensesApiController queryExpensesApiController;
  @MockBean
  DeleteExpensesApiController deleteExpensesApiController;

  @BeforeEach
  public void setUp() {
    tokenProvider.validToken();
  }

  @Test
  void should_post_then_call_post_controller() {
    final var requesst = new ExpenseCreateRequest()
      .description("Test 1")
      .vendor("Vendor 1")
      .transactionDate(OffsetDateTime.now(ZoneOffset.UTC))
      .recurrence(ExpenseCreateRequest.RecurrenceEnum.NONE)
      .amount(new Money().value(983L).exponent(2))
      .paymentMethod(ExpenseCreateRequest.PaymentMethodEnum.CASH)
      .category("Category 1");

    restTemplate.postForEntity("/expenses", requesst, Void.class);

    verify(postExpensesApiController).post(requesst);
  }

  @Test
  void should_get_then_call_get_controller() {
    final var expenseId = "0989de36-843b-4be8-882a-4ec9b219b1f3";

    restTemplate.getForEntity("/expenses/" + expenseId, String.class);

    verify(getExpensesApiController).get(expenseId);
  }

  @Test
  void should_get_with_query_params_then_call_query_controller() {
    final var startDate = OffsetDateTime.now(ZoneOffset.UTC).minusDays(1);
    final var endDate = OffsetDateTime.now(ZoneOffset.UTC).minusDays(1);

    restTemplate.getForEntity("/expenses?"
        + "startDate=" + startDate.format(DateTimeFormatter.ISO_DATE_TIME)
        + "&endDate=" + endDate.format(DateTimeFormatter.ISO_DATE_TIME),
      String.class);

    verify(queryExpensesApiController).query(
      null,
      startDate,
      endDate,
      Pageable.ofSize(20)
    );
  }

  @Test
  void should_delete_then_call_delete_controller() {
    final var expenseId = "0989de36-843b-4be8-882a-4ec9b219b1f3";

    restTemplate.delete("/expenses/" + expenseId);

    verify(deleteExpensesApiController).delete(expenseId);
  }

}
