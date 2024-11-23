package dev.hanluc.expensetracker.expenses.application;

import dev.hanluc.expensetracker.common.domain.vo.Result;
import dev.hanluc.expensetracker.expenses.domain.repository.ExpenseRepository;
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
