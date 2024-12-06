package dev.hanluc.expensetracker.budgets.domain;

import dev.hanluc.expensetracker.budgets.mother.BudgetMother;
import dev.hanluc.expensetracker.common.domain.vo.Money;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class BudgetTest {

  @Test
  void given_budget_consumed_zero_when_increment_then_consumed_not_zero() {
    Budget budget = BudgetMother.onBudget();
    Money increment = new Money(100L, 2);

    final var result = budget.incrementConsumed(increment);

    then(result.getConsumed().value()).isGreaterThan(0);
    then(result.getConsumed()).isEqualTo(new Money(100L, 2));
  }

  @Test
  void given_budget_consumed_when_increment_then_consumed_add_increment() {
    Budget budget = BudgetMother.onBudgetConsumedGreaterThanZero();
    Money increment = new Money(100L, 2);

    final var result = budget.incrementConsumed(increment);

    then(result.getConsumed()).isEqualTo(new Money(200L, 2));
  }

}
