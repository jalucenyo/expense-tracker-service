package dev.hanluc.expensetracker.budgets.infrastructure.api;

import dev.hanluc.expensetracker.budgets.applicaion.FindByIdBudgetUseCase;
import dev.hanluc.expensetracker.budgets.domain.exception.BudgetException;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetResponse;
import dev.hanluc.expensetracker.budgets.infrastructure.api.mappers.response.BudgetResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetBudgetsApiController {

  private final FindByIdBudgetUseCase findByIdBudgetUseCase;
  private final BudgetResponseMapper budgetResponseMapper;

  public GetBudgetsApiController(
    FindByIdBudgetUseCase findByIdBudgetUseCase,
    BudgetResponseMapper budgetResponseMapper
  ) {
    this.findByIdBudgetUseCase = findByIdBudgetUseCase;
    this.budgetResponseMapper = budgetResponseMapper;
  }

  public ResponseEntity<BudgetResponse> get(String budgetId) {
    return findByIdBudgetUseCase.findById(budgetId)
      .fold(
        errors -> {
          throw new BudgetException(errors);
        },
        budget -> ResponseEntity.ok().body(budgetResponseMapper.toResponse(budget))
      );
  }

}
