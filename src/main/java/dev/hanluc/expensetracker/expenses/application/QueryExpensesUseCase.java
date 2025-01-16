package dev.hanluc.expensetracker.expenses.application;

import dev.hanluc.expensetracker.expenses.domain.Expense;
import dev.hanluc.expensetracker.expenses.domain.repository.ExpenseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class QueryExpensesUseCase {

  private static final int MAX_FROM_DAYS = 1;

  private final ExpenseRepository expenseRepository;

  public QueryExpensesUseCase(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  public Page<Expense> query(ExpenseQuery query) {
    final var startDate = Optional.ofNullable(query.startDate()).orElse(OffsetDateTime.now().minusDays(MAX_FROM_DAYS));
    final var endDate = Optional.ofNullable(query.endDate()).orElse(OffsetDateTime.now());

    return expenseRepository
      .findAllByTransactionDateBetweenOrderByTransactionDateDesc(startDate, endDate, query.pageable());
  }

  public record ExpenseQuery(
    String filter,
    OffsetDateTime startDate,
    OffsetDateTime endDate,
    Pageable pageable
  ) {
  }

}
