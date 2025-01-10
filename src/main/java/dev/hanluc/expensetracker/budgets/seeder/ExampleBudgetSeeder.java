package dev.hanluc.expensetracker.budgets.seeder;

import dev.hanluc.expensetracker.budgets.domain.Budget;
import dev.hanluc.expensetracker.budgets.domain.repository.BudgetRepository;
import dev.hanluc.expensetracker.common.domain.vo.Money;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Configuration
@ConditionalOnProperty(
    value = "budgets.seeder",
    havingValue = "true",
    matchIfMissing = false
)
public class ExampleBudgetSeeder implements ApplicationRunner {

  private final BudgetRepository budgetRepository;

  public ExampleBudgetSeeder(BudgetRepository budgetRepository) {
    this.budgetRepository = budgetRepository;
  }

  @Override
  public void run(ApplicationArguments args) {

    final var examplesBudgets = List.of(
      new Budget(UUID.randomUUID(), "Marketing Campaign Q1", new Money(105000L, 2), OffsetDateTime.now().minusDays(30), OffsetDateTime.now().minusDays(1), "Marketing"),
      new Budget(UUID.randomUUID(), "IT Infrastructure Upgrade", new Money(250000L, 2), OffsetDateTime.now().minusDays(60), OffsetDateTime.now().minusDays(10), "IT"),
      new Budget(UUID.randomUUID(), "Office Supplies 2024", new Money(15000L, 2), OffsetDateTime.now().minusDays(15), OffsetDateTime.now(), "Operations"),
      new Budget(UUID.randomUUID(), "Employee Training Program", new Money(80000L, 2), OffsetDateTime.now().minusDays(90), OffsetDateTime.now().minusDays(30), "HR"),
      new Budget(UUID.randomUUID(), "Product Launch Expenses", new Money(500000L, 2), OffsetDateTime.now().minusDays(120), OffsetDateTime.now().minusDays(90), "Sales"),
      new Budget(UUID.randomUUID(), "Customer Research Study", new Money(65000L, 2), OffsetDateTime.now().minusDays(45), OffsetDateTime.now().minusDays(5), "Research"),
      new Budget(UUID.randomUUID(), "Cloud Migration Project", new Money(300000L, 2), OffsetDateTime.now().minusDays(200), OffsetDateTime.now().minusDays(150), "Technology"),
      new Budget(UUID.randomUUID(), "Corporate Event Organization", new Money(120000L, 2), OffsetDateTime.now().minusDays(180), OffsetDateTime.now().minusDays(90), "Events"),
      new Budget(UUID.randomUUID(), "Sustainability Initiatives", new Money(60000L, 2), OffsetDateTime.now().minusDays(200), OffsetDateTime.now().minusDays(180), "Sustainability"),
      new Budget(UUID.randomUUID(), "Global Expansion Feasibility", new Money(400000L, 2), OffsetDateTime.now().minusDays(100), OffsetDateTime.now().minusDays(50), "Strategy")
    );

    budgetRepository.saveAll(examplesBudgets);
  }

}
