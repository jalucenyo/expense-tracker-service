package dev.hanluc.expensetracker.expenses.domain.events;

import dev.hanluc.expensetracker.common.domain.vo.Money;

import java.time.OffsetDateTime;

public record ExpenseCreatedEvent(
  String category,
  Money amount,
  OffsetDateTime transactionDate
) {
}
