package dev.hanluc.expensetracker.expenses.mother;

import dev.hanluc.expensetracker.Mother;
import dev.hanluc.expensetracker.expenses.application.CreateExpenseUseCase.ExpenseCreate;
import org.instancio.Instancio;

public class ExpenseCreateMother {

  public static Mother<ExpenseCreate> random() {
    return new Mother<>(
        Instancio.of(ExpenseCreate.class)
            .toModel());
  }

}
