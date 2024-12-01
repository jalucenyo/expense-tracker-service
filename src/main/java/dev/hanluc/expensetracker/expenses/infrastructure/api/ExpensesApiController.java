package dev.hanluc.expensetracker.expenses.infrastructure.api;

import dev.hanluc.expensetracker.expenses.application.CreateExpenseUseCase;
import dev.hanluc.expensetracker.expenses.application.DeleteExpenseUseCase;
import dev.hanluc.expensetracker.expenses.application.FindByIdExpenseUseCase;
import dev.hanluc.expensetracker.expenses.application.QueryExpenseUseCase;
import dev.hanluc.expensetracker.expenses.domain.exception.ExpenseTrackerException;
import dev.hanluc.expensetracker.expenses.infrastructure.api.mappers.ExpenseCreateDtoMapper;
import dev.hanluc.expensetracker.expenses.infrastructure.api.mappers.ExpenseDtoMapper;
import dev.hanluc.expensetracker.expenses.infrastructure.api.mappers.ExpensePaginatedDtoMapper;
import dev.hanluc.expensetracker.expenses.infrastructure.api.controller.ExpensesApi;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpenseCreateDto;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpenseDto;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpensePaginatedDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.OffsetDateTime;

@RestController
public class ExpensesApiController implements ExpensesApi {

  private final CreateExpenseUseCase createExpenseUseCase;
  private final ExpenseCreateDtoMapper createRequestMapper;

  private final FindByIdExpenseUseCase findByIdExpenseUseCase;
  private final ExpenseDtoMapper expenseResponseMapper;

  private final QueryExpenseUseCase queryExpenseUseCase;
  private final ExpensePaginatedDtoMapper queryResponseMapper;

  private final DeleteExpenseUseCase deleteExpenseUseCase;


  public ExpensesApiController(
    CreateExpenseUseCase createExpenseUseCase,
    ExpenseCreateDtoMapper createRequestMapper,
    FindByIdExpenseUseCase findByIdExpenseUseCase,
    ExpenseDtoMapper expenseResponseMapper,
    QueryExpenseUseCase queryExpenseUseCase,
    ExpensePaginatedDtoMapper queryResponseMapper,
    DeleteExpenseUseCase deleteExpenseUseCase) {
    this.createExpenseUseCase = createExpenseUseCase;
    this.createRequestMapper = createRequestMapper;
    this.findByIdExpenseUseCase = findByIdExpenseUseCase;
    this.expenseResponseMapper = expenseResponseMapper;
    this.queryExpenseUseCase = queryExpenseUseCase;
    this.queryResponseMapper = queryResponseMapper;
    this.deleteExpenseUseCase = deleteExpenseUseCase;
  }

  @Override
  public ResponseEntity<Void> post(ExpenseCreateDto expenseCreateDto) {
    final var expenseCreate = createRequestMapper.toExpenseCreate(expenseCreateDto);

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
  public ResponseEntity<Void> delete(String expenseId) {
    return deleteExpenseUseCase.delete(new DeleteExpenseUseCase.ExpenseDelete(expenseId))
      .fold(
        errors -> {
          throw new ExpenseTrackerException(errors);
        },
        expenseCreated -> ResponseEntity.noContent().build()
      );
  }

  @Override
  public ResponseEntity<ExpenseDto> get(String expenseId) {
    return findByIdExpenseUseCase.findById(expenseId)
      .fold(
        errors -> {
          throw new ExpenseTrackerException(errors);
        },
        expense -> ResponseEntity.ok().body(expenseResponseMapper.toExpenseDto(expense))
      );
  }

  @Override
  public ResponseEntity<ExpensePaginatedDto> query(String filter, OffsetDateTime startDate, OffsetDateTime endDate, Pageable pageable) {
    return ResponseEntity.ok().body(queryResponseMapper.toExpensePaginatedDto(
      queryExpenseUseCase.query(new QueryExpenseUseCase.ExpenseQuery(filter, startDate, endDate, pageable))));
  }


  @Override
  public ResponseEntity<Void> put(String expenseId, ExpenseDto expenseDto) {
    return null;
  }

}
