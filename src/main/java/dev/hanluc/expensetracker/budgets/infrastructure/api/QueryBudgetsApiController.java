package dev.hanluc.expensetracker.budgets.infrastructure.api;

import dev.hanluc.expensetracker.budgets.applicaion.QueryBudgetsUseCase;
import dev.hanluc.expensetracker.budgets.applicaion.QueryBudgetsUseCase.BudgetQuery;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetPaginatedResponse;
import dev.hanluc.expensetracker.budgets.infrastructure.api.mappers.response.BudgetPaginatedResponseMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QueryBudgetsApiController {

  private final QueryBudgetsUseCase queryBudgetsUseCase;

  private final BudgetPaginatedResponseMapper mapper;

  public QueryBudgetsApiController(
      QueryBudgetsUseCase queryBudgetsUseCase,
      BudgetPaginatedResponseMapper mapper
  ) {
    this.queryBudgetsUseCase = queryBudgetsUseCase;
    this.mapper = mapper;
  }

  public ResponseEntity<BudgetPaginatedResponse> query(Pageable pageable) {
    final var result = queryBudgetsUseCase.query(new BudgetQuery(pageable));
    final var response = mapper.toResponse(result);
    return ResponseEntity.ok(response);
  }

}
