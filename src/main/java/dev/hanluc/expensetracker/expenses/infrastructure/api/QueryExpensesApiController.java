package dev.hanluc.expensetracker.expenses.infrastructure.api;

import dev.hanluc.expensetracker.expenses.application.QueryExpenseUseCase;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpensePaginatedResponse;
import dev.hanluc.expensetracker.expenses.infrastructure.api.mappers.response.ExpensePaginatedResponseMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class QueryExpensesApiController {

  private final QueryExpenseUseCase queryExpenseUseCase;

  private final ExpensePaginatedResponseMapper mapper;

  public QueryExpensesApiController(
      QueryExpenseUseCase queryExpenseUseCase,
      ExpensePaginatedResponseMapper mapper
  ) {
    this.queryExpenseUseCase = queryExpenseUseCase;
    this.mapper = mapper;
  }

  public ResponseEntity<ExpensePaginatedResponse> query(String filter, OffsetDateTime startDate, OffsetDateTime endDate,
      Pageable pageable) {
    final var results = queryExpenseUseCase.query(new QueryExpenseUseCase.ExpenseQuery(filter, startDate, endDate, pageable));
    final var response = mapper.toResponse(results);
    return ResponseEntity.ok(response);
  }

}
