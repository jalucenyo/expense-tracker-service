package dev.hanluc.expensetracker.expense.application;

import dev.hanluc.expensetracker.common.domain.vo.Result;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.UUID;
import org.springframework.validation.annotation.Validated;

@Validated
public interface DeleteExpenseUseCase {

  Result<Void> delete(@Valid ExpenseDelete expenseDelete);

  record ExpenseDelete(

    @NotEmpty(message = "Id is required")
    @UUID
    String id

  ) {

  }

}
