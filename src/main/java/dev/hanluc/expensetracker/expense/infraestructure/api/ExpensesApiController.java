package dev.hanluc.expensetracker.expense.infraestructure.api;

import dev.hanluc.expensetracker.expense.application.CreateExpenseUseCase;
import dev.hanluc.expensetracker.expense.application.DeleteExpenseUseCase;
import dev.hanluc.expensetracker.expense.application.QueryExpenseUseCase;
import dev.hanluc.expensetracker.expense.domain.exception.ExpenseTrackerException;
import dev.hanluc.expensetracker.expense.infraestructure.api.mappers.ExpenseCreateDtoMapper;
import dev.hanluc.expensetracker.expense.infraestructure.api.mappers.ExpensePaginatedDtoMapper;
import dev.hanluc.expensetracker.expense.infraestructure.api.spec.controller.ExpensesApi;
import dev.hanluc.expensetracker.expense.infraestructure.api.spec.dto.ExpenseCreateDto;
import dev.hanluc.expensetracker.expense.infraestructure.api.spec.dto.ExpenseDto;
import dev.hanluc.expensetracker.expense.infraestructure.api.spec.dto.ExpensePaginatedDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.OffsetDateTime;

@RestController
public class ExpensesApiController implements ExpensesApi {

  private final CreateExpenseUseCase createExpenseUseCase;
  private final ExpenseCreateDtoMapper expenseCreateDtoMapper;

  private final QueryExpenseUseCase queryExpenseUseCase;
  private final ExpensePaginatedDtoMapper expensePaginatedDtoMapper;

  private final DeleteExpenseUseCase deleteExpenseUseCase;

  public ExpensesApiController(
    CreateExpenseUseCase createExpenseUseCase,
    ExpenseCreateDtoMapper expenseCreateDtoMapper,
    QueryExpenseUseCase queryExpenseUseCase, ExpensePaginatedDtoMapper expensePaginatedDtoMapper, DeleteExpenseUseCase deleteExpenseUseCase) {
    this.createExpenseUseCase = createExpenseUseCase;
    this.expenseCreateDtoMapper = expenseCreateDtoMapper;
    this.queryExpenseUseCase = queryExpenseUseCase;
    this.expensePaginatedDtoMapper = expensePaginatedDtoMapper;
    this.deleteExpenseUseCase = deleteExpenseUseCase;
  }

  @Override
  public ResponseEntity<Void> createExpense(ExpenseCreateDto expenseCreateDto) {

    final var expenseCreate = expenseCreateDtoMapper.toExpenseCreate(expenseCreateDto);

    return createExpenseUseCase.create(expenseCreate)
      .fold(
        errors -> {
          throw new ExpenseTrackerException(errors);
        },
        expenseCreated -> ResponseEntity.created(buildLocation(expenseCreated.getId().toString())).build());
  }

  private URI buildLocation(String id) {
    return ServletUriComponentsBuilder.fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(id)
      .toUri();
  }

  @Override
  public ResponseEntity<Void> deleteExpense(String expenseId) {
    return deleteExpenseUseCase.delete(new DeleteExpenseUseCase.ExpenseDelete(expenseId))
      .fold(
        errors -> {
          throw new ExpenseTrackerException(errors);
        },
        expenseCreated -> ResponseEntity.noContent().build()
      );
  }

  @Override
  public ResponseEntity<ExpensePaginatedDto> listExpenses(String filter, OffsetDateTime startDate, OffsetDateTime endDate, Pageable pageable) {
    return ResponseEntity.ok().body(expensePaginatedDtoMapper.toExpensePaginatedDto(
      queryExpenseUseCase.query(new QueryExpenseUseCase.ExpenseQuery(filter, startDate, endDate, pageable))));
  }

  @Override
  public ResponseEntity<Void> updateExpense(String expenseId, ExpenseDto expenseDto) {
    return null;
  }

}
