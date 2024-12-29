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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import({TestContainersConfiguration.class, TestRestTemplateConfig.class})
class BudgetApiControllerTest {

  @Autowired
  TestRestTemplate restTemplate;

  @Autowired
  private TokenProvider tokenProvider;

  @MockBean
  PostBudgetsApiController postBudgetsApiController;
  @MockBean
  GetBudgetsApiController getBudgetsApiController;

  @BeforeEach
  public void setUp() {
    tokenProvider.setToken(TokenProvider.GENERIC_USER);
  }

  @Test
  void should_post_then_call_post_controller() {
    BudgetCreateRequest requesst = new BudgetCreateRequest()
      .name("Budget 1")
      .amount(new Money().value(983L).exponent(2))
      .startDate(OffsetDateTime.now(ZoneOffset.UTC))
      .endDate(OffsetDateTime.now(ZoneOffset.UTC).plusMonths(1))
      .category("Category 1");

    restTemplate.postForEntity("/budgets", requesst, Void.class);

    verify(postBudgetsApiController).post(requesst);
  }

  @Test
  void should_get_then_call_get_controller() {
    final var budgetId = "0989de36-843b-4be8-882a-4ec9b219b1f3";

    restTemplate.getForEntity("/budgets/" + budgetId, String.class);

    verify(getBudgetsApiController).get(budgetId);
  }

}
