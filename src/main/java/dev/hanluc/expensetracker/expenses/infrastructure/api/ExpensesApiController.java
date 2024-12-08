package dev.hanluc.expensetracker.expenses.infrastructure.api;

import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpenseCreateRequest;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpensePaginatedResponse;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpenseResponse;
import dev.hanluc.expensetracker.expenses.infrastructure.api.controller.ExpensesApi;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
public class ExpensesApiController implements ExpensesApi {

  private final PostExpensesApiController postExpensesApiController;
  private final DeleteApiController deleteApiController;
  private final GetApiController getApiController;
  private final QueryApiController queryApiController;
  private final PutApiController putApiController;

  public ExpensesApiController(
    PostExpensesApiController postExpensesApiController,
    DeleteApiController deleteApiController,
    GetApiController getApiController,
    QueryApiController queryApiController,
    PutApiController putApiController
  ) {
    this.postExpensesApiController = postExpensesApiController;
    this.deleteApiController = deleteApiController;
    this.queryApiController = queryApiController;
    this.getApiController = getApiController;
    this.putApiController = putApiController;
  }

  @Override
  public ResponseEntity<Void> post(ExpenseCreateRequest request) {
    return postExpensesApiController.post(request);
  }

  @Override
  public ResponseEntity<Void> delete(String expenseId) {
    return deleteApiController.delete(expenseId);
  }

  @Override
  public ResponseEntity<ExpenseResponse> get(String expenseId) {
    return getApiController.get(expenseId);
  }

  @Override
  public ResponseEntity<ExpensePaginatedResponse> query(String filter, OffsetDateTime startDate, OffsetDateTime endDate, Pageable pageable) {
    return queryApiController.query(filter, startDate, endDate, pageable);
  }

  @Override
  public ResponseEntity<Void> put(String expenseId, ExpenseResponse expenseDto) {
    return putApiController.put(expenseId, expenseDto);
  }

}
