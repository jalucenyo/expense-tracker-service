package dev.hanluc.expensetracker.expense.application;

import dev.hanluc.expensetracker.expense.domain.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.OffsetDateTime;

public interface QueryExpenseUseCase {

  Page<Expense> query(ExpenseQuery query);

  record ExpenseQuery(
    String filter,
    OffsetDateTime startDate,
    OffsetDateTime endDate,
    Pageable pageable
  ) {
  }

}
