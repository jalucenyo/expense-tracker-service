package dev.hanluc.expensetracker.expenses.infrastructure.api;

import dev.hanluc.expensetracker.expenses.application.FindByIdExpenseUseCase;
import dev.hanluc.expensetracker.expenses.domain.exception.ExpenseTrackerException;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpenseResponse;
import dev.hanluc.expensetracker.expenses.infrastructure.api.mappers.response.ExpenseResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetExpensesApiController {

  private final FindByIdExpenseUseCase findByIdExpenseUseCase;

  private final ExpenseResponseMapper mapper;

  public GetExpensesApiController(
      FindByIdExpenseUseCase findByIdExpenseUseCase,
      ExpenseResponseMapper mapper
  ) {
    this.findByIdExpenseUseCase = findByIdExpenseUseCase;
    this.mapper = mapper;
  }

  public ResponseEntity<ExpenseResponse> get(String expenseId) {
    return findByIdExpenseUseCase.findById(expenseId)
        .fold(
            errors -> {
              throw new ExpenseTrackerException(errors);
            },
            expense -> ResponseEntity.ok().body(mapper.toResponse(expense))
        );
  }

}
