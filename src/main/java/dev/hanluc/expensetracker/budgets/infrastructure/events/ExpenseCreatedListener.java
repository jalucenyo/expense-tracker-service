package dev.hanluc.expensetracker.budgets.infrastructure.events;

import dev.hanluc.expensetracker.budgets.applicaion.ExpenseAllocateUseCase;
import dev.hanluc.expensetracker.budgets.applicaion.ExpenseAllocateUseCase.ExpenseAllocate;
import dev.hanluc.expensetracker.budgets.domain.exception.BudgetException;
import dev.hanluc.expensetracker.expenses.domain.events.ExpenseCreatedEvent;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
public class ExpenseCreatedListener {

  private  final ExpenseAllocateUseCase expenseAllocateUseCase;

  public ExpenseCreatedListener(ExpenseAllocateUseCase expenseAllocateUseCase) {
    this.expenseAllocateUseCase = expenseAllocateUseCase;
  }

  @ApplicationModuleListener
  public void onExpenseCreated(ExpenseCreatedEvent event) {
    expenseAllocateUseCase.allocate(new ExpenseAllocate(event.category(), event.amount(), event.transactionDate()))
      .fold(
        _ -> {
          throw new BudgetException("Failed to allocate expense");
        },
        _ -> null
      );
  }

}
