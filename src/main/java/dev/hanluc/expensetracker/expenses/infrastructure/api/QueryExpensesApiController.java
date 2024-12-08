package dev.hanluc.expensetracker.expenses.infrastructure.api;

import dev.hanluc.expensetracker.expenses.application.QueryExpenseUseCase;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpensePaginatedResponse;
import dev.hanluc.expensetracker.expenses.infrastructure.api.mappers.ExpensePaginatedDtoMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class QueryExpensesApiController {

  private final QueryExpenseUseCase queryExpenseUseCase;
  private final ExpensePaginatedDtoMapper queryResponseMapper;

  public QueryExpensesApiController(
    QueryExpenseUseCase queryExpenseUseCase,
    ExpensePaginatedDtoMapper queryResponseMapper
  ) {
    this.queryExpenseUseCase = queryExpenseUseCase;
    this.queryResponseMapper = queryResponseMapper;
  }

  public ResponseEntity<ExpensePaginatedResponse> query(String filter, OffsetDateTime startDate, OffsetDateTime endDate, Pageable pageable) {
    return ResponseEntity.ok().body(queryResponseMapper.toExpensePaginatedDto(
      queryExpenseUseCase.query(new QueryExpenseUseCase.ExpenseQuery(filter, startDate, endDate, pageable))));
  }

}
