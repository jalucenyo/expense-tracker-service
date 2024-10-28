package dev.hanluc.expensetracker.expense.application;

import dev.hanluc.expensetracker.expense.domain.Expense;
import dev.hanluc.expensetracker.expense.domain.repository.ExpenseRepository;
import dev.hanluc.expensetracker.expense.domain.vo.Result;
import org.springframework.stereotype.Service;

@Service
public class CreateExpenseUseCaseImpl implements CreateExpenseUseCase {

  private final ExpenseRepository expenseRepository;

  public CreateExpenseUseCaseImpl(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  @Override
  public Result<Expense> create(ExpenseCreate expenseCreate) {
    return Result.success(expenseRepository.save(expenseCreate.toExpense()));
  }

}
