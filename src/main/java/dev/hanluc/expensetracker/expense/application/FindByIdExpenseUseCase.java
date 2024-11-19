package dev.hanluc.expensetracker.expense.application;

import dev.hanluc.expensetracker.expense.domain.Expense;
import dev.hanluc.expensetracker.expense.domain.vo.Result;

public interface FindByIdExpenseUseCase {

  Result<Expense> findById(String id);

}
