package dev.hanluc.expensetracker.budgets.domain;

import dev.hanluc.expensetracker.expense.domain.vo.Money;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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

}
