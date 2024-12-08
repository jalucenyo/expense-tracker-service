package dev.hanluc.expensetracker.expenses.infrastructure.api;

import dev.hanluc.expensetracker.expenses.application.FindByIdExpenseUseCase;
import dev.hanluc.expensetracker.expenses.domain.exception.ExpenseTrackerException;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpenseResponse;
import dev.hanluc.expensetracker.expenses.infrastructure.api.mappers.ExpenseDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetExpensesApiController {

  private final FindByIdExpenseUseCase findByIdExpenseUseCase;
  private final ExpenseDtoMapper expenseResponseMapper;

  public GetExpensesApiController(
    FindByIdExpenseUseCase findByIdExpenseUseCase,
    ExpenseDtoMapper expenseResponseMapper
  ) {
    this.findByIdExpenseUseCase = findByIdExpenseUseCase;
    this.expenseResponseMapper = expenseResponseMapper;
  }

  public ResponseEntity<ExpenseResponse> get(String expenseId) {
    return findByIdExpenseUseCase.findById(expenseId)
      .fold(
        errors -> {
          throw new ExpenseTrackerException(errors);
        },
        expense -> ResponseEntity.ok().body(expenseResponseMapper.toExpenseDto(expense))
      );
  }

}
