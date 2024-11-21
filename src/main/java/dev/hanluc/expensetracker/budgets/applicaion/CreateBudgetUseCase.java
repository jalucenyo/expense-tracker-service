package dev.hanluc.expensetracker.budgets.applicaion;

import dev.hanluc.expensetracker.budgets.domain.Budget;
import dev.hanluc.expensetracker.common.domain.vo.Money;
import dev.hanluc.expensetracker.common.domain.vo.Result;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.time.OffsetDateTime;

@Validated
public interface CreateBudgetUseCase {

  Result<Budget> create(@Valid BudgetCreate budgetCreate);

  record BudgetCreate(
    @NotEmpty
    @Size(min = 3, max = 255)
    @Pattern(regexp = "^[\\p{L}\\p{N}\\s]+$")
    String name,

    @NotNull
    Money amount,

    @NotNull
    OffsetDateTime startDate,

    @NotNull
    OffsetDateTime endDate

  ) {
    public Budget toBudget() {
      return new Budget(name, amount, startDate, endDate);
    }
  }

}
