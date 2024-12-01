package dev.hanluc.expensetracker.expenses.domain;

import dev.hanluc.expensetracker.common.domain.vo.Money;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ExpenseBuilder {
  private UUID id;
  private Money amount;
  private String description;
  private OffsetDateTime transactionDate;
  private String paymentMethod;
  private String vendor;
  private String recurrence;
  private String notes;

  public ExpenseBuilder setId(UUID id) {
    this.id = id;
    return this;
  }

  public ExpenseBuilder setAmount(Money amount) {
    this.amount = amount;
    return this;
  }

  public ExpenseBuilder setDescription(String description) {
    this.description = description;
    return this;
  }

  public ExpenseBuilder setTransactionDate(OffsetDateTime transactionDate) {
    this.transactionDate = transactionDate;
    return this;
  }

  public ExpenseBuilder setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
    return this;
  }

  public ExpenseBuilder setVendor(String vendor) {
    this.vendor = vendor;
    return this;
  }

  public ExpenseBuilder setRecurrence(String recurrence) {
    this.recurrence = recurrence;
    return this;
  }

  public ExpenseBuilder setNotes(String notes) {
    this.notes = notes;
    return this;
  }

  public Expense createExpense() {
    return new Expense(id, amount, description, transactionDate, paymentMethod, vendor, recurrence, notes);
  }
}