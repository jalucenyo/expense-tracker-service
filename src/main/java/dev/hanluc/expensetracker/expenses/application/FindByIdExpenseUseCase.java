package dev.hanluc.expensetracker.expenses.application;

import dev.hanluc.expensetracker.common.domain.vo.Result;
import dev.hanluc.expensetracker.common.domain.vo.ResultError;
import dev.hanluc.expensetracker.expenses.domain.Expense;
import dev.hanluc.expensetracker.expenses.domain.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindByIdExpenseUseCase {

  private final ExpenseRepository expenseRepository;

  public FindByIdExpenseUseCase(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  public Result<Expense> findById(String id) {
    return expenseRepository.findById(UUID.fromString(id))
      .map(Result::success)
      .orElseGet(() -> Result.failure(ResultError.of("Expense not found")));
  }

}
