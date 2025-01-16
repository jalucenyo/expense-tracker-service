package dev.hanluc.expensetracker.expenses.application;

import dev.hanluc.expensetracker.common.domain.vo.Money;
import dev.hanluc.expensetracker.common.domain.vo.Result;
import dev.hanluc.expensetracker.expenses.domain.Expense;
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

    final var expense = createExpense(expenseCreate);

    return Result.success(expenseRepository.save(expense))
      .fold(
        Result::failure,
        expenseCreated -> {
          expense.getDomainEvents().pullEvents(eventPublisher::publishEvent);
          return Result.success(expenseCreated);
        }
      );
  }

  private static Expense createExpense(ExpenseCreate expenseCreate) {
    return Expense.create(
      UUID.randomUUID(),
      expenseCreate.amount(),
      expenseCreate.description(),
      expenseCreate.transactionDate(),
      expenseCreate.paymentMethod(),
      expenseCreate.vendor(),
      expenseCreate.recurrence(),
      expenseCreate.notes(),
      expenseCreate.category()
    );
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
  }

}
