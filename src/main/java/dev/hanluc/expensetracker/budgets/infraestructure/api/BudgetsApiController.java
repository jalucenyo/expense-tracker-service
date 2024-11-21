package dev.hanluc.expensetracker.budgets.infraestructure.api;

import dev.hanluc.expensetracker.api.spec.controller.BudgetsApi;
import dev.hanluc.expensetracker.api.spec.dto.BudgetCreateDto;
import dev.hanluc.expensetracker.api.spec.dto.BudgetDto;
import dev.hanluc.expensetracker.api.spec.dto.BudgetPaginatedDto;
import dev.hanluc.expensetracker.budgets.applicaion.CreateBudgetUseCase;
import dev.hanluc.expensetracker.budgets.domain.exception.BudgetException;
import dev.hanluc.expensetracker.budgets.infraestructure.api.mappers.BudgetCreateDtoMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class BudgetsApiController implements BudgetsApi {

  private final CreateBudgetUseCase createBudgetUseCase;
  private final BudgetCreateDtoMapper budgetCreateDtoMapper;

  public BudgetsApiController(CreateBudgetUseCase createBudgetUseCase,
                              BudgetCreateDtoMapper budgetCreateDtoMapper) {
    this.createBudgetUseCase = createBudgetUseCase;
    this.budgetCreateDtoMapper = budgetCreateDtoMapper;
  }

  @Override
  public ResponseEntity<Void> createBudget(BudgetCreateDto budgetCreateDto) {
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
  public ResponseEntity<BudgetDto> getBudget(String budgetId) {
    return null;
  }

  @Override
  public ResponseEntity<BudgetPaginatedDto> getBudgets(Pageable pageable) {
    return null;
  }

}
