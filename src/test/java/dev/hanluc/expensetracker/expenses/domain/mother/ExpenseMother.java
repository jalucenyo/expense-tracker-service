package dev.hanluc.expensetracker.expenses.domain.mother;


import dev.hanluc.expensetracker.common.domain.vo.Money;
import dev.hanluc.expensetracker.expenses.domain.Expense;
import dev.hanluc.expensetracker.expenses.domain.ExpenseBuilder;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ExpenseMother {

  public static Expense random() {
    return new ExpenseBuilder()
      .setId(UUID.randomUUID())
      .setAmount(new Money(130L, 2))
      .setDescription("Expense 01")
      .setTransactionDate(OffsetDateTime.now())
      .setPaymentMethod("CASH")
      .setVendor("Amazon")
      .setRecurrence("WEEKLY")
      .setNotes("This is a note")
      .createExpense();
  }

}
