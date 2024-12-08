package dev.hanluc.expensetracker.budgets.infrastructure.events;

import dev.hanluc.expensetracker.TestContainersConfiguration;
import dev.hanluc.expensetracker.budgets.domain.Budget;
import dev.hanluc.expensetracker.budgets.domain.repository.BudgetRepository;
import dev.hanluc.expensetracker.common.domain.vo.Money;
import dev.hanluc.expensetracker.expenses.domain.events.ExpenseCreatedEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;
import org.springframework.test.context.jdbc.Sql;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;

@ApplicationModuleTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, mode = ApplicationModuleTest.BootstrapMode.ALL_DEPENDENCIES)
@Import(TestContainersConfiguration.class)
@Sql(scripts = "classpath:db/budget/data-init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "classpath:db/budget/data-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class ExpenseCreatedListenerTest {

  @Autowired
  private BudgetRepository budgetRepository;

  @Test
  void given_expense_created_event_then_increment_budget_consumed(Scenario scenario) {
    final var expectedUpdatedBudgetId = UUID.fromString("22a2b9a8-a538-4a2a-ad2d-5e2dfca9a972");
    final var createdExpenseEvent = new ExpenseCreatedEvent(
      "Food",
      new Money(100L, 2),
      OffsetDateTime.now());

    scenario.publish(createdExpenseEvent)
      .andWaitAtMost(Duration.ofSeconds(1))
      .andWaitForStateChange(() -> budgetRepository.findById(expectedUpdatedBudgetId).map(Budget::getConsumed))
      .andVerify(consumed -> {
        then(consumed).isPresent();
        then(consumed.get()).isEqualTo(new Money(100L, 2));
      });
  }

}
