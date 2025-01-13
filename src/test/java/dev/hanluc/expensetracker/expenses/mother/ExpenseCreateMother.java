package dev.hanluc.expensetracker.expenses.mother;

import dev.hanluc.expensetracker.Mother;
import dev.hanluc.expensetracker.expenses.application.CreateExpenseUseCase.ExpenseCreate;
import org.instancio.Instancio;
import org.instancio.Model;

public class ExpenseCreateMother {

  private static final Mother<ExpenseCreate> mother = new Mother<>(ExpenseCreateMother::randomModel);

  private static Model<ExpenseCreate> randomModel() {
    return Instancio.of(ExpenseCreate.class)
        .toModel();
  }

  public static Mother<ExpenseCreate> random() {
    return mother;
  }

}
