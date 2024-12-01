package dev.hanluc.expensetracker.budgets.seeder;

import dev.hanluc.expensetracker.budgets.domain.BudgetBuilder;
import dev.hanluc.expensetracker.budgets.domain.repository.BudgetRepository;
import dev.hanluc.expensetracker.common.domain.vo.Money;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Configuration
public class ExampleBudgetSeeder implements ApplicationRunner {

  private final BudgetRepository budgetRepository;

  public ExampleBudgetSeeder(BudgetRepository budgetRepository) {
    this.budgetRepository = budgetRepository;
  }

  @Override
  public void run(ApplicationArguments args) {

    final var exampleBudgets = List.of(
      new BudgetBuilder().setId(UUID.randomUUID())
        .setAmount(new Money(100L, 2))
        .setName("Budget 1")
        .setStartDate(OffsetDateTime.now().minusMonths(1))
        .setEndDate(OffsetDateTime.now().plusMonths(1))
        .createBudget()
    );

//    budgetRepository.saveAll(exampleBudgets);
  }

}
