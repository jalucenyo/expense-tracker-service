package dev.hanluc.expensetracker.budgets.domain;

import dev.hanluc.expensetracker.common.domain.vo.Money;

import java.time.OffsetDateTime;
import java.util.UUID;

public class BudgetBuilder {
  private UUID id;
  private String name;
  private Money amount;
  private OffsetDateTime startDate;
  private OffsetDateTime endDate;

  public BudgetBuilder setId(UUID id) {
    this.id = id;
    return this;
  }

  public BudgetBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public BudgetBuilder setAmount(Money amount) {
    this.amount = amount;
    return this;
  }

  public BudgetBuilder setStartDate(OffsetDateTime startDate) {
    this.startDate = startDate;
    return this;
  }

  public BudgetBuilder setEndDate(OffsetDateTime endDate) {
    this.endDate = endDate;
    return this;
  }

  public Budget createBudget() {
    return new Budget(id, name, amount, startDate, endDate);
  }
}