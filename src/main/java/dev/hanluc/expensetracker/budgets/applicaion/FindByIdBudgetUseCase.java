package dev.hanluc.expensetracker.budgets.applicaion;

import dev.hanluc.expensetracker.budgets.domain.Budget;
import dev.hanluc.expensetracker.common.domain.vo.Result;

public interface FindByIdBudgetUseCase {

  Result<Budget> findById(String id);

}
