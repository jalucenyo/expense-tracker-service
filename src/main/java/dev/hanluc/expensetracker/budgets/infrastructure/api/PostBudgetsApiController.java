package dev.hanluc.expensetracker.budgets.infrastructure.api;

import dev.hanluc.expensetracker.budgets.applicaion.CreateBudgetUseCase;
import dev.hanluc.expensetracker.budgets.domain.exception.BudgetException;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetCreateRequest;
import dev.hanluc.expensetracker.budgets.infrastructure.api.mappers.BudgetCreateDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class PostBudgetsApiController {

  private final CreateBudgetUseCase createBudgetUseCase;
  private final BudgetCreateDtoMapper budgetCreateDtoMapper;

  public PostBudgetsApiController(
    CreateBudgetUseCase createBudgetUseCase,
    BudgetCreateDtoMapper budgetCreateDtoMapper
  ) {
    this.createBudgetUseCase = createBudgetUseCase;
    this.budgetCreateDtoMapper = budgetCreateDtoMapper;
  }

  public ResponseEntity<Void> post(BudgetCreateRequest budgetCreateDto) {
    final var budgetCreate = budgetCreateDtoMapper.toBudgetCreate(budgetCreateDto);

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
