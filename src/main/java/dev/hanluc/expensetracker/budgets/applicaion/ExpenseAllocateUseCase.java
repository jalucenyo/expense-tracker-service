package dev.hanluc.expensetracker.budgets.applicaion;

import dev.hanluc.expensetracker.budgets.domain.Budget;
import dev.hanluc.expensetracker.common.domain.vo.Money;
import dev.hanluc.expensetracker.common.domain.vo.Result;

import java.time.OffsetDateTime;

public interface ExpenseAllocateUseCase {

  Result<Budget> allocate(ExpenseAllocate event);

  record ExpenseAllocate(String category, Money amount, OffsetDateTime transactionDate) { }

}
