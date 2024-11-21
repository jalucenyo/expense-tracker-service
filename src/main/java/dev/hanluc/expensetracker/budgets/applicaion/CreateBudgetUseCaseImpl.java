package dev.hanluc.expensetracker.budgets.applicaion;

import dev.hanluc.expensetracker.budgets.domain.Budget;
import dev.hanluc.expensetracker.budgets.domain.repository.BudgetRepository;
import dev.hanluc.expensetracker.common.domain.vo.Result;
import org.springframework.stereotype.Service;

@Service
public class CreateBudgetUseCaseImpl implements CreateBudgetUseCase {

  private final BudgetRepository budgetRepository;

  public CreateBudgetUseCaseImpl(BudgetRepository budgetRepository) {
    this.budgetRepository = budgetRepository;
  }

  @Override
  public Result<Budget> create(BudgetCreate budgetCreate) {
    return Result.success(budgetRepository.save(budgetCreate.toBudget()));
  }

}
