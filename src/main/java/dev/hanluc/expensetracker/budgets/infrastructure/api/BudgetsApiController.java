package dev.hanluc.expensetracker.budgets.infrastructure.api;

import dev.hanluc.expensetracker.budgets.infrastructure.api.controller.BudgetsApi;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetCreateRequest;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetPaginatedResponse;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BudgetsApiController implements BudgetsApi {

  private final PostBudgetsApiController postBudgetsApiController;
  private final GetBudgetsApiController getBudgetsApiController;

  public BudgetsApiController(
    PostBudgetsApiController postBudgetsApiController,
    GetBudgetsApiController getBudgetsApiController
  ) {
    this.postBudgetsApiController = postBudgetsApiController;
    this.getBudgetsApiController = getBudgetsApiController;
  }

  @Override
  public ResponseEntity<Void> createBudget(BudgetCreateRequest budgetCreateDto) {
    return postBudgetsApiController.post(budgetCreateDto);
  }

  @Override
  public ResponseEntity<BudgetResponse> getBudget(String budgetId) {
    return getBudgetsApiController.get(budgetId);
  }

  @Override
  public ResponseEntity<BudgetPaginatedResponse> getBudgets(Pageable pageable) {
    return null;
  }

}
