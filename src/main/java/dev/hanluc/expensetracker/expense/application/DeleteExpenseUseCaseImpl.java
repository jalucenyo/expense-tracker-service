package dev.hanluc.expensetracker.expense.application;

import dev.hanluc.expensetracker.expense.domain.repository.ExpenseRepository;
import dev.hanluc.expensetracker.expense.domain.vo.Result;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public class DeleteExpenseUseCaseImpl implements DeleteExpenseUseCase {

  private final ExpenseRepository expenseRepository;

  public DeleteExpenseUseCaseImpl(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  @Override
  public Result<Void> delete(ExpenseDelete expenseDelete) {
    expenseRepository.deleteById(UUID.fromString(expenseDelete.id()));
    return Result.success(null);
  }

}
