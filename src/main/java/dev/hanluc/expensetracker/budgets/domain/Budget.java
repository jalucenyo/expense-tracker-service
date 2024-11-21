package dev.hanluc.expensetracker.budgets.domain;

import dev.hanluc.expensetracker.common.domain.vo.Money;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "budget")
public class Budget {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @EqualsAndHashCode.Include
  private UUID id;

  private String name;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "amount_value"))
  @AttributeOverride(name = "exponent", column = @Column(name = "amount_exponent"))
  private Money amount;

  private OffsetDateTime startDate;

  private OffsetDateTime endDate;

  public Budget() {
  }

  public Budget(String name, Money amount, OffsetDateTime startDate, OffsetDateTime endDate) {
    this.name = name;
    this.amount = amount;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Money getAmount() {
    return amount;
  }

  public void setAmount(Money amount) {
    this.amount = amount;
  }

  public OffsetDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(OffsetDateTime startDate) {
    this.startDate = startDate;
  }

  public OffsetDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(OffsetDateTime endDate) {
    this.endDate = endDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Budget budget)) return false;
    return Objects.equals(id, budget.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

}
