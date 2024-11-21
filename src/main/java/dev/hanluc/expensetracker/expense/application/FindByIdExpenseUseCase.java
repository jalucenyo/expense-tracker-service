package dev.hanluc.expensetracker.expense.application;

import dev.hanluc.expensetracker.common.domain.vo.Result;
import dev.hanluc.expensetracker.expense.domain.Expense;

public interface FindByIdExpenseUseCase {

  Result<Expense> findById(String id);

}
