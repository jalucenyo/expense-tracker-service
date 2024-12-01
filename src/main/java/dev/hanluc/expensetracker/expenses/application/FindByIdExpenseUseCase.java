package dev.hanluc.expensetracker.expenses.application;

import dev.hanluc.expensetracker.common.domain.vo.Result;
import dev.hanluc.expensetracker.expenses.domain.Expense;

public interface FindByIdExpenseUseCase {

  Result<Expense> findById(String id);

}
