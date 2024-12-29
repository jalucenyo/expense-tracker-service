package dev.hanluc.expensetracker.expenses.application;

import dev.hanluc.expensetracker.common.domain.vo.Money;
import dev.hanluc.expensetracker.common.domain.vo.Result;
import dev.hanluc.expensetracker.expenses.domain.Expense;
import dev.hanluc.expensetracker.expenses.domain.events.ExpenseCreatedEvent;
import dev.hanluc.expensetracker.expenses.domain.repository.ExpenseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@Validated
public class CreateExpenseUseCase {

  private final ExpenseRepository expenseRepository;
  private final ApplicationEventPublisher eventPublisher;

  public CreateExpenseUseCase(
    ExpenseRepository expenseRepository,
    ApplicationEventPublisher eventPublisher) {
    this.expenseRepository = expenseRepository;
    this.eventPublisher = eventPublisher;
  }

  @Transactional
  public Result<Expense> create(@Valid ExpenseCreate expenseCreate) {
    return Result.success(expenseRepository.save(expenseCreate.toExpense()))
      .fold(
        Result::failure,
        expense -> {
          sendDomainEvent(expense);
          return Result.success(expense);
        }
      );
  }

  private void sendDomainEvent(Expense expense) {
    eventPublisher.publishEvent(new ExpenseCreatedEvent(
      expense.getCategory(),
      expense.getAmount(),
      expense.getTransactionDate()));
  }

  public record ExpenseCreate(

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

    Expense toExpense() {
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
