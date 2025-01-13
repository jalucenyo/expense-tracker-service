package dev.hanluc.expensetracker.budgets.application;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import dev.hanluc.expensetracker.budgets.applicaion.ExpenseAllocateUseCase;
import dev.hanluc.expensetracker.budgets.domain.Budget;
import dev.hanluc.expensetracker.budgets.domain.repository.BudgetRepository;
import dev.hanluc.expensetracker.budgets.mother.BudgetMother;
import dev.hanluc.expensetracker.budgets.mother.ExpenseAllocateMother;
import dev.hanluc.expensetracker.common.asserts.ResultErrorAssert;
import dev.hanluc.expensetracker.common.domain.vo.Money;
import dev.hanluc.expensetracker.common.domain.vo.Result;
import org.junit.jupiter.api.Test;

class ExpenseAllocateUseCaseTest {

  private final BudgetRepository budgetRepository = mock(BudgetRepository.class);

  private final ExpenseAllocateUseCase expenseAllocateUseCase = new ExpenseAllocateUseCase(budgetRepository);

  @Test
  void given_expense_when_budget_category_and_date_exist_then_increment_budget() {
    final var expenseAllocate = ExpenseAllocateMother.randomWithAmount(100L, 2);
    final var existBudget = BudgetMother.customize().withConsumed(0L, 2).create();
    when(budgetRepository.findByCategoryAndDate(expenseAllocate.category(), expenseAllocate.transactionDate()))
        .thenReturn(Optional.of(existBudget));
    when(budgetRepository.save(any())).then(invocation -> invocation.getArgument(0));

    Result<Budget> result = expenseAllocateUseCase.allocate(expenseAllocate);

    then(result.isSuccess()).isTrue();
    then(result.value().getConsumed()).isEqualTo(new Money(100L, 2));
  }

  @Test
  void given_expense_when_budget_category_and_date_not_exist_then_return_error() {
    final var expenseAllocate = ExpenseAllocateMother.random();
    when(budgetRepository.findByCategoryAndDate(expenseAllocate.category(), expenseAllocate.transactionDate()))
        .thenReturn(Optional.empty());

    Result<Budget> result = expenseAllocateUseCase.allocate(expenseAllocate);

    then(result.isFailure()).isTrue();
    ResultErrorAssert.then(result.errors()).hasMessage("Budget not found");
  }

}
