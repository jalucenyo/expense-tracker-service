package dev.hanluc.expensetracker.expenses.application;

import dev.hanluc.expensetracker.common.domain.vo.Money;
import dev.hanluc.expensetracker.common.domain.vo.Result;
import dev.hanluc.expensetracker.expenses.domain.Expense;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.time.OffsetDateTime;
import java.util.UUID;

@Validated
public interface CreateExpenseUseCase {

  Result<Expense> create(@Valid ExpenseCreate expenseCreate);

  record ExpenseCreate(

    @NotNull
    Money amount,

    @NotEmpty
    @Size(min = 3, max = 255)
    @Pattern(regexp = "^[\\p{L}\\p{N}\\s]+$")
    String description,

    @NotNull
    OffsetDateTime transactionDate,

    @NotEmpty
    String paymentMethod,

    @NotEmpty
    @Size(min = 3, max = 255)
    String vendor,

    @NotNull
    String recurrence,

    String notes,

    @NotEmpty
    String category

  ) {

    public Expense toExpense() {
      return new Expense(
        UUID.randomUUID(),
        amount,
        description,
        transactionDate,
        paymentMethod,
        vendor,
        recurrence,
        notes,
        category
      );
    }
  }
}
