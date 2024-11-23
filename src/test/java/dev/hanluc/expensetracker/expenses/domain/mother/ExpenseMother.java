package dev.hanluc.expensetracker.expenses.domain.mother;


import dev.hanluc.expensetracker.common.domain.vo.Money;
import dev.hanluc.expensetracker.expenses.domain.Expense;

import java.time.OffsetDateTime;

public class ExpenseMother {

  public static Expense random() {
    return Expense.builder()
      .amount(new Money(130L, 2))
      .description("Expense 01")
      .transactionDate(OffsetDateTime.now())
      .paymentMethod("CASH")
      .vendor("Amazon")
      .recurrence("WEEKLY")
      .notes("This is a note")
      .build();
  }

}
