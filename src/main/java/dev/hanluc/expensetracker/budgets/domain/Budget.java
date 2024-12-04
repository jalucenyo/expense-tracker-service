package dev.hanluc.expensetracker.budgets.domain;

import dev.hanluc.expensetracker.common.domain.vo.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Table
public class Budget {

  @Id
  private UUID id;

  @Version
  private Integer version;

  private String name;

  @Embedded(onEmpty = Embedded.OnEmpty.USE_EMPTY, prefix = "amount_")
  private Money amount;

  private OffsetDateTime startDate;

  private OffsetDateTime endDate;

  private String category;

  public Budget(UUID id, String name, Money amount, OffsetDateTime startDate, OffsetDateTime endDate, String category) {
    this.id = id;
    this.name = name;
    this.amount = amount;
    this.startDate = startDate;
    this.endDate = endDate;
    this.category = category;
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

  public Money getAmount() {
    return amount;
  }

  public OffsetDateTime getStartDate() {
    return startDate;
  }

  public OffsetDateTime getEndDate() {
    return endDate;
  }

  public String getCategory() {
    return category;
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

  @Override
  public String toString() {
    return "Budget{" +
      "id=" + id +
      ", version=" + version +
      ", name='" + name + '\'' +
      ", amount=" + amount +
      ", startDate=" + startDate +
      ", endDate=" + endDate +
      ", category='" + category + '\'' +
      '}';
  }
}
