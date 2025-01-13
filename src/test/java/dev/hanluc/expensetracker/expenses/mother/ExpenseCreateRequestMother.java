package dev.hanluc.expensetracker.expenses.mother;

import dev.hanluc.expensetracker.Mother;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpenseCreateRequest;
import org.instancio.Instancio;
import org.instancio.Model;

public class ExpenseCreateRequestMother {

  private static final Mother<ExpenseCreateRequest> mother = new Mother<>(ExpenseCreateRequestMother::randomModel);

  private static Model<ExpenseCreateRequest> randomModel() {
    return Instancio.of(ExpenseCreateRequest.class)
        .toModel();
  }

  public static Mother<ExpenseCreateRequest> random() {
    return mother;
  }

}
