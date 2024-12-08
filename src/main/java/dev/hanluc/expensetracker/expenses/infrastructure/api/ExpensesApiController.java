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
  private final DeleteExpensesApiController deleteExpensesApiController;
  private final GetExpensesApiController getExpensesApiController;
  private final QueryExpensesApiController queryExpensesApiController;
  private final PutExpensesApiController putApiController;

  public ExpensesApiController(
    PostExpensesApiController postExpensesApiController,
    DeleteExpensesApiController deleteExpensesApiController,
    GetExpensesApiController getExpensesApiController,
    QueryExpensesApiController queryExpensesApiController,
    PutExpensesApiController putApiController
  ) {
    this.postExpensesApiController = postExpensesApiController;
    this.deleteExpensesApiController = deleteExpensesApiController;
    this.queryExpensesApiController = queryExpensesApiController;
    this.getExpensesApiController = getExpensesApiController;
    this.putApiController = putApiController;
  }

  @Override
  public ResponseEntity<Void> post(ExpenseCreateRequest request) {
    return postExpensesApiController.post(request);
  }

  @Override
  public ResponseEntity<Void> delete(String expenseId) {
    return deleteExpensesApiController.delete(expenseId);
  }

  @Override
  public ResponseEntity<ExpenseResponse> get(String expenseId) {
    return getExpensesApiController.get(expenseId);
  }

  @Override
  public ResponseEntity<ExpensePaginatedResponse> query(String filter, OffsetDateTime startDate, OffsetDateTime endDate, Pageable pageable) {
    return queryExpensesApiController.query(filter, startDate, endDate, pageable);
  }

  @Override
  public ResponseEntity<Void> put(String expenseId, ExpenseResponse expenseDto) {
    return putApiController.put(expenseId, expenseDto);
  }

}
