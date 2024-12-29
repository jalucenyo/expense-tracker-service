package dev.hanluc.expensetracker.budgets.applicaion;

import dev.hanluc.expensetracker.budgets.domain.Budget;
import dev.hanluc.expensetracker.budgets.domain.repository.BudgetRepository;
import dev.hanluc.expensetracker.common.domain.vo.Money;
import dev.hanluc.expensetracker.common.domain.vo.Result;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@Validated
public class CreateBudgetUseCase {

  private final BudgetRepository budgetRepository;

  public CreateBudgetUseCase(BudgetRepository budgetRepository) {
    this.budgetRepository = budgetRepository;
  }

  public Result<Budget> create(@Valid BudgetCreate budgetCreate) {
    return Result.success(budgetRepository.save(budgetCreate.toBudget()));
  }

  public record BudgetCreate(
    @NotEmpty
    @Size(min = 3, max = 255)
    @Pattern(regexp = "^[\\p{L}\\p{N}\\s]+$")
    String name,

    @NotNull
    Money amount,

    @NotNull
    OffsetDateTime startDate,

    @NotNull
    OffsetDateTime endDate,

    @NotBlank
    @Size(min = 3, max = 50)
    String category

  ) {
    public Budget toBudget() {
      return new Budget(UUID.randomUUID(), name, amount, startDate, endDate, category);
    }
  }

}
