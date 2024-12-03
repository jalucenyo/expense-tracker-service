package dev.hanluc.expensetracker.budgets.infrastructure.api;

import dev.hanluc.expensetracker.budgets.applicaion.CreateBudgetUseCase;
import dev.hanluc.expensetracker.budgets.applicaion.FindByIdBudgetUseCase;
import dev.hanluc.expensetracker.budgets.domain.exception.BudgetException;
import dev.hanluc.expensetracker.budgets.infrastructure.api.controller.BudgetsApi;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetCreateRequest;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetPaginatedResponse;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetResponse;
import dev.hanluc.expensetracker.budgets.infrastructure.api.mappers.BudgetCreateDtoMapper;
import dev.hanluc.expensetracker.budgets.infrastructure.api.mappers.BudgetDtoMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class BudgetsApiController implements BudgetsApi {

  private final CreateBudgetUseCase createBudgetUseCase;
  private final BudgetCreateDtoMapper budgetCreateDtoMapper;
  private final FindByIdBudgetUseCase findByIdBudgetUseCase;
  private final BudgetDtoMapper budgetDtoMapper;

  public BudgetsApiController(CreateBudgetUseCase createBudgetUseCase,
                              BudgetCreateDtoMapper budgetCreateDtoMapper,
                              FindByIdBudgetUseCase findByIdBudgetUseCase,
                              BudgetDtoMapper budgetDtoMapper) {
    this.createBudgetUseCase = createBudgetUseCase;
    this.budgetCreateDtoMapper = budgetCreateDtoMapper;
    this.findByIdBudgetUseCase = findByIdBudgetUseCase;
    this.budgetDtoMapper = budgetDtoMapper;
  }

  @Override
  public ResponseEntity<Void> createBudget(BudgetCreateRequest budgetCreateDto) {
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

  @Override
  public ResponseEntity<BudgetResponse> getBudget(String budgetId) {
    return findByIdBudgetUseCase.findById(budgetId)
      .fold(
        errors -> {
          throw new BudgetException(errors);
        },
        budget -> ResponseEntity.ok().body(budgetDtoMapper.toBudgetDto(budget))
      );
  }

  @Override
  public ResponseEntity<BudgetPaginatedResponse> getBudgets(Pageable pageable) {
    return null;
  }

}
