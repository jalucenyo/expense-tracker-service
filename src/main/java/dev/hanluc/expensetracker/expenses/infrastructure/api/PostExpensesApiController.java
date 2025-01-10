package dev.hanluc.expensetracker.expenses.infrastructure.api;

import dev.hanluc.expensetracker.expenses.application.CreateExpenseUseCase;
import dev.hanluc.expensetracker.expenses.domain.exception.ExpenseTrackerException;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpenseCreateRequest;
import dev.hanluc.expensetracker.expenses.infrastructure.api.mappers.request.ExpenseCreateRequestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class PostExpensesApiController {

  private final CreateExpenseUseCase createExpenseUseCase;

  private final ExpenseCreateRequestMapper mapper;

  public PostExpensesApiController(
      CreateExpenseUseCase createExpenseUseCase,
      ExpenseCreateRequestMapper mapper) {
    this.createExpenseUseCase = createExpenseUseCase;
    this.mapper = mapper;
  }

  public ResponseEntity<Void> post(ExpenseCreateRequest request) {
    final var expenseCreate = mapper.toDomain(request);

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

}
