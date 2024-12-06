package dev.hanluc.expensetracker.budgets.mother;

import dev.hanluc.expensetracker.budgets.domain.Budget;
import dev.hanluc.expensetracker.common.domain.vo.Money;

import java.time.OffsetDateTime;
import java.util.UUID;

public class BudgetMother {

  public static Budget onBudget() {
    return new Budget(UUID.randomUUID(),
      "My project budget test",
      new Money(1000L, 2),
      OffsetDateTime.parse("2024-12-01T00:00:00.000Z"),
      OffsetDateTime.parse("2024-12-31T00:00:00.000Z"),
      "Food");
  }

  public static Budget onBudgetConsumedGreaterThanZero() {
    final var budget =  new Budget(UUID.randomUUID(),
      "My project budget test",
      new Money(1000L, 2),
      OffsetDateTime.now(),
      OffsetDateTime.now().plusDays(30),
      "Food");
    budget.incrementConsumed(new Money(100L, 2));
    return budget;
  }

}
