package dev.hanluc.expensetracker.budgets.applicaion;

import dev.hanluc.expensetracker.budgets.domain.Budget;
import dev.hanluc.expensetracker.budgets.domain.repository.BudgetRepository;
import dev.hanluc.expensetracker.common.domain.vo.Result;
import dev.hanluc.expensetracker.common.domain.vo.ResultError;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QueryBudgetUseCaseImpl implements FindByIdBudgetUseCase {

  private final BudgetRepository budgetRepository;

  public QueryBudgetUseCaseImpl(BudgetRepository budgetRepository) {
    this.budgetRepository = budgetRepository;
  }

  @Override
  public Result<Budget> findById(String id) {
    return budgetRepository.findById(UUID.fromString(id))
      .map(Result::success)
      .orElseGet(() -> Result.failure(ResultError.of("Budget not found")));
  }

}
