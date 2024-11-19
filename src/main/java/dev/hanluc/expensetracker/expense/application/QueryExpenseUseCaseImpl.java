package dev.hanluc.expensetracker.expense.application;

import dev.hanluc.expensetracker.expense.domain.Expense;
import dev.hanluc.expensetracker.expense.domain.repository.ExpenseRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class QueryExpenseUseCaseImpl implements QueryExpenseUseCase {

  private final ExpenseRepository expenseRepository;

  public QueryExpenseUseCaseImpl(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  @Override
  public Page<Expense> query(ExpenseQuery query) {
    final var startDate = Optional.ofNullable(query.startDate()).orElse(OffsetDateTime.now().minusDays(1));
    final var endDate = Optional.ofNullable(query.endDate()).orElse(OffsetDateTime.now());

    return expenseRepository
      .findAllByTransactionDateBetweenOrderByTransactionDateDesc(startDate, endDate, query.pageable());
  }

}
