package dev.hanluc.expensetracker.budgets.applicaion;

import dev.hanluc.expensetracker.expenses.domain.events.ExpenseCreatedEvent;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
public class ExpenseAllocatorListener {

  @ApplicationModuleListener
  public void onExpenseCreated(ExpenseCreatedEvent event) {

  }

}
