package dev.hanluc.expensetracker.expenses.domain;

import dev.hanluc.expensetracker.common.domain.vo.Money;
import dev.hanluc.expensetracker.expenses.application.CreateExpenseUseCase;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Table
public class Expense {

  @Id
  private UUID id;

  @Version
  private Integer version;

  @Embedded(onEmpty = Embedded.OnEmpty.USE_EMPTY, prefix = "amount_")
  private Money amount;

  private String description;

  private OffsetDateTime transactionDate;

  private String paymentMethod;

  private String vendor;

  private String recurrence;

  private String notes;

  public Expense(UUID id, Money amount, String description, OffsetDateTime transactionDate, String paymentMethod, String vendor, String recurrence, String notes) {
    this.id = id;
    this.amount = amount;
    this.description = description;
    this.transactionDate = transactionDate;
    this.paymentMethod = paymentMethod;
    this.vendor = vendor;
    this.recurrence = recurrence;
    this.notes = notes;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Money getAmount() {
    return amount;
  }

  public void setAmount(Money amount) {
    this.amount = amount;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public OffsetDateTime getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(OffsetDateTime transactionDate) {
    this.transactionDate = transactionDate;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public String getVendor() {
    return vendor;
  }

  public void setVendor(String vendor) {
    this.vendor = vendor;
  }

  public String getRecurrence() {
    return recurrence;
  }

  public void setRecurrence(String recurrence) {
    this.recurrence = recurrence;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Expense expense)) return false;
    return Objects.equals(id, expense.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "Expense{" +
      "id=" + id +
      ", version=" + version +
      ", amount=" + amount +
      ", description='" + description + '\'' +
      ", transactionDate=" + transactionDate +
      ", paymentMethod='" + paymentMethod + '\'' +
      ", vendor='" + vendor + '\'' +
      ", recurrence='" + recurrence + '\'' +
      ", notes='" + notes + '\'' +
      '}';
  }
}
