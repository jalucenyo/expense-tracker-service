package dev.hanluc.expensetracker.budgets.application;

import dev.hanluc.expensetracker.budgets.mother.BudgetMother;
import dev.hanluc.expensetracker.budgets.applicaion.ExpenseAllocateUseCase;
import dev.hanluc.expensetracker.budgets.applicaion.ExpenseAllocateUseCase.ExpenseAllocate;
import dev.hanluc.expensetracker.budgets.domain.Budget;
import dev.hanluc.expensetracker.budgets.domain.repository.BudgetRepository;
import dev.hanluc.expensetracker.common.asserts.ResultErrorAssert;
import dev.hanluc.expensetracker.common.domain.vo.Money;
import dev.hanluc.expensetracker.common.domain.vo.Result;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.instancio.Select.field;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExpenseAllocateUseCaseTest {

  private final BudgetRepository budgetRepository = mock(BudgetRepository.class);
  private final ExpenseAllocateUseCase expenseAllocateUseCase = new ExpenseAllocateUseCase(budgetRepository);

  @Test
  void given_expense_when_budget_category_and_date_exist_then_increment_budget() {
    final var transactionDate = OffsetDateTime.parse("2024-12-04T00:00:00.000Z");
    final var createdExpense = new ExpenseAllocate("Food", new Money(100L, 2), transactionDate);
    final var existBudget = Instancio.of(BudgetMother.random())
        .set(field(Budget::getConsumed), new Money(0L, 2))
        .create();
    when(budgetRepository.findByCategoryAndDate(createdExpense.category(), transactionDate))
      .thenReturn(Optional.of(existBudget));
    when(budgetRepository.save(any())).then(invocation -> invocation.getArgument(0));

    Result<Budget> result = expenseAllocateUseCase.allocate(createdExpense);

    then(result.isSuccess()).isTrue();
    then(result.value().getConsumed()).isEqualTo(new Money(100L, 2));
  }

  @Test
  void given_expense_when_budget_category_and_date_not_exist_then_return_error() {
    final var transactionDate = OffsetDateTime.parse("2024-12-04T00:00:00.000Z");
    final var createdExpense = new ExpenseAllocate("Food", new Money(100L, 2), transactionDate);
    when(budgetRepository.findByCategoryAndDate(createdExpense.category(), transactionDate))
      .thenReturn(Optional.empty());

    Result<Budget> result = expenseAllocateUseCase.allocate(createdExpense);

    then(result.isFailure()).isTrue();
    ResultErrorAssert.then(result.errors()).hasMessage("Budget not found");
  }

}
