package dev.hanluc.expensetracker.expenses.domain;

import dev.hanluc.expensetracker.common.domain.vo.Money;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "expense")
public class Expense {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @EqualsAndHashCode.Include
  private UUID id;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "amount_value"))
  @AttributeOverride(name = "exponent", column = @Column(name = "amount_exponent"))
  private Money amount;

  private String description;

  private OffsetDateTime transactionDate;

  private String paymentMethod;

  private String vendor;

  private String recurrence;

  private String notes;

}
