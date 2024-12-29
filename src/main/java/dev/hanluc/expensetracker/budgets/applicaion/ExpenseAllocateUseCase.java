package dev.hanluc.expensetracker.budgets.applicaion;

import dev.hanluc.expensetracker.budgets.domain.Budget;
import dev.hanluc.expensetracker.budgets.domain.repository.BudgetRepository;
import dev.hanluc.expensetracker.common.domain.vo.Money;
import dev.hanluc.expensetracker.common.domain.vo.Result;
import dev.hanluc.expensetracker.common.domain.vo.ResultError;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class ExpenseAllocateUseCase {

  private final BudgetRepository budgetRepository;

  public ExpenseAllocateUseCase(BudgetRepository budgetRepository) {
    this.budgetRepository = budgetRepository;
  }

  public Result<Budget> allocate(ExpenseAllocate event) {
    return budgetRepository.findByCategoryAndDate(event.category(), event.transactionDate())
      .map(budget -> {
        budget.incrementConsumed(event.amount());
        return Result.success(budgetRepository.save(budget));
      })
      .orElseGet(() -> Result.failure(ResultError.of("Budget not found")));
  }

  public record ExpenseAllocate(String category, Money amount, OffsetDateTime transactionDate) {
  }

}
