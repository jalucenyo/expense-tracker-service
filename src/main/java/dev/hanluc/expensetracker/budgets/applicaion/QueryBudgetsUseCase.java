package dev.hanluc.expensetracker.budgets.applicaion;

import dev.hanluc.expensetracker.budgets.domain.Budget;
import dev.hanluc.expensetracker.budgets.domain.repository.BudgetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QueryBudgetsUseCase {

  private final BudgetRepository budgetRepository;

  public QueryBudgetsUseCase(BudgetRepository budgetRepository) {
    this.budgetRepository = budgetRepository;
  }

  public Page<Budget> query(BudgetQuery query) {
    return budgetRepository.findAll(query.pageable());
  }

  public record BudgetQuery(
      Pageable pageable
  ) {

  }

}
