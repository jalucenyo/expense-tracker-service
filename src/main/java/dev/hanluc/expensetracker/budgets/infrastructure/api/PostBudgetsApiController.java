package dev.hanluc.expensetracker.budgets.infrastructure.api;

import dev.hanluc.expensetracker.budgets.applicaion.CreateBudgetUseCase;
import dev.hanluc.expensetracker.budgets.domain.exception.BudgetException;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetCreateRequest;
import dev.hanluc.expensetracker.budgets.infrastructure.api.mappers.request.BudgetCreateRequestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class PostBudgetsApiController {

  private final CreateBudgetUseCase createBudgetUseCase;
  private final BudgetCreateRequestMapper mapper;

  public PostBudgetsApiController(
    CreateBudgetUseCase createBudgetUseCase,
    BudgetCreateRequestMapper mapper
  ) {
    this.createBudgetUseCase = createBudgetUseCase;
    this.mapper = mapper;
  }

  public ResponseEntity<Void> post(BudgetCreateRequest budgetCreateDto) {
    final var budgetCreate = mapper.toBudgetCreate(budgetCreateDto);

    return createBudgetUseCase.create(budgetCreate)
      .fold(
        errors -> {
          throw new BudgetException(errors);
        },
        budgetCreated -> ResponseEntity.created(buildLocation(budgetCreated.getId().toString())).build()
      );
  }

  private URI buildLocation(String id) {
    return ServletUriComponentsBuilder.fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(id)
      .toUri();
  }

}
