package dev.hanluc.expensetracker.expenses.application;

import dev.hanluc.expensetracker.common.domain.vo.Result;
import dev.hanluc.expensetracker.expenses.domain.repository.ExpenseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Service
@Validated
public class DeleteExpenseUseCase {

  private final ExpenseRepository expenseRepository;

  public DeleteExpenseUseCase(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  public Result<Void> delete(@Valid ExpenseDelete expenseDelete) {
    expenseRepository.deleteById(UUID.fromString(expenseDelete.id()));
    return Result.success(null);
  }

  public record ExpenseDelete(
    @NotEmpty(message = "Id is required")
    @org.hibernate.validator.constraints.UUID
    String id
  ) {
  }

}
