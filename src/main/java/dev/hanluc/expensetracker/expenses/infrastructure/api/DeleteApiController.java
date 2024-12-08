package dev.hanluc.expensetracker.expenses.infrastructure.api;

import dev.hanluc.expensetracker.expenses.application.DeleteExpenseUseCase;
import dev.hanluc.expensetracker.expenses.domain.exception.ExpenseTrackerException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteApiController {

  private final DeleteExpenseUseCase deleteExpenseUseCase;

  public DeleteApiController(
    DeleteExpenseUseCase deleteExpenseUseCase
  ) {
    this.deleteExpenseUseCase = deleteExpenseUseCase;
  }

  public ResponseEntity<Void> delete(String expenseId) {
    return deleteExpenseUseCase.delete(new DeleteExpenseUseCase.ExpenseDelete(expenseId))
      .fold(
        errors -> {
          throw new ExpenseTrackerException(errors);
        },
        expenseCreated -> ResponseEntity.noContent().build()
      );
  }

}
