package dev.hanluc.expensetracker.expense.application;

import dev.hanluc.expensetracker.expense.domain.Expense;
import dev.hanluc.expensetracker.expense.domain.vo.Money;
import dev.hanluc.expensetracker.expense.domain.vo.Result;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.time.OffsetDateTime;

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

    String notes

  ) {

    public Expense toExpense() {
      return Expense.builder()
        .amount(amount)
        .description(description)
        .notes(notes)
        .paymentMethod(paymentMethod)
        .recurrence(recurrence)
        .transactionDate(transactionDate)
        .vendor(vendor)
        .build();
    }
  }
}
