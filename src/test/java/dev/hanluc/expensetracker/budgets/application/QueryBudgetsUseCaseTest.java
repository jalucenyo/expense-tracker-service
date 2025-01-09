package dev.hanluc.expensetracker.budgets.application;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import dev.hanluc.expensetracker.budgets.applicaion.QueryBudgetsUseCase;
import dev.hanluc.expensetracker.budgets.applicaion.QueryBudgetsUseCase.BudgetQuery;
import dev.hanluc.expensetracker.budgets.domain.Budget;
import dev.hanluc.expensetracker.budgets.domain.repository.BudgetRepository;
import dev.hanluc.expensetracker.budgets.mother.BudgetMother;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

class QueryBudgetsUseCaseTest {

  private final BudgetRepository budgetRepository = mock(BudgetRepository.class);

  private final QueryBudgetsUseCase queryBudgetsUseCase = new QueryBudgetsUseCase(budgetRepository);

  @Test
  void given_budgets_when_query_budgets_then_return_budgets() {
    int numberOfBudgets = 2;
    BudgetQuery query = new BudgetQuery(PageRequest.of(0, 10));
    when(budgetRepository.findAll(query.pageable())).thenReturn(BudgetMother.listOfBudgets(numberOfBudgets));

    Page<Budget> result = queryBudgetsUseCase.query(query);

    then(result).isNotNull();
    then(result.getContent()).hasSize(numberOfBudgets);
  }

  @Test
  void given_empty_budgets_when_query_budgets_then_empty_page() {
    BudgetQuery query = new BudgetQuery(PageRequest.of(0, 10));
    when(budgetRepository.findAll(query.pageable())).thenReturn(BudgetMother.emptyListOfBudgets());

    Page<Budget> result = queryBudgetsUseCase.query(query);

    then(result).isNotNull();
    then(result.getContent()).isEmpty();
  }

}
