package dev.hanluc.expensetracker.budgets.infrastructure.api;

import dev.hanluc.expensetracker.budgets.applicaion.FindByIdBudgetUseCase;
import dev.hanluc.expensetracker.budgets.domain.exception.BudgetException;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetResponse;
import dev.hanluc.expensetracker.budgets.infrastructure.api.mappers.BudgetDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetBudgetsApiController {

  private final FindByIdBudgetUseCase findByIdBudgetUseCase;
  private final BudgetDtoMapper budgetDtoMapper;

  public GetBudgetsApiController(
    FindByIdBudgetUseCase findByIdBudgetUseCase,
    BudgetDtoMapper budgetDtoMapper
  ) {
    this.findByIdBudgetUseCase = findByIdBudgetUseCase;
    this.budgetDtoMapper = budgetDtoMapper;
  }

  public ResponseEntity<BudgetResponse> get(String budgetId) {
    return findByIdBudgetUseCase.findById(budgetId)
      .fold(
        errors -> {
          throw new BudgetException(errors);
        },
        budget -> ResponseEntity.ok().body(budgetDtoMapper.toBudgetDto(budget))
      );
  }

}
