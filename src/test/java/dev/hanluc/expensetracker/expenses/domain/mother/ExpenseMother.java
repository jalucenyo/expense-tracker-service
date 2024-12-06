package dev.hanluc.expensetracker.expenses.domain.mother;


import dev.hanluc.expensetracker.common.domain.vo.Money;
import dev.hanluc.expensetracker.expenses.domain.Expense;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ExpenseMother {

  public static Expense random() {
    return new Expense(UUID.randomUUID(),
      new Money(130L, 2),
      "Expense 01", OffsetDateTime.now(),
      "CASH",
      "Amazon",
      "WEEKLY",
      "This is a note",
      "Category 1");
  }

}
