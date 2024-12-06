package dev.hanluc.expensetracker.budgets.applicaion;

import dev.hanluc.expensetracker.budgets.domain.Budget;
import dev.hanluc.expensetracker.budgets.domain.repository.BudgetRepository;
import dev.hanluc.expensetracker.common.domain.vo.Result;
import dev.hanluc.expensetracker.common.domain.vo.ResultError;
import org.springframework.stereotype.Service;

@Service
public class ExpenseAllocateUseCaseService implements ExpenseAllocateUseCase {

  private final BudgetRepository budgetRepository;

  public ExpenseAllocateUseCaseService(BudgetRepository budgetRepository) {
    this.budgetRepository = budgetRepository;
  }

  @Override
  public Result<Budget> allocate(ExpenseAllocate event) {
    return budgetRepository.findByCategoryAndDate(event.category(), event.transactionDate())
      .map(budget -> {
        budget.incrementConsumed(event.amount());
        return Result.success(budgetRepository.save(budget));
      })
      .orElseGet(() -> Result.failure(ResultError.of("Budget not found")));
  }

}
