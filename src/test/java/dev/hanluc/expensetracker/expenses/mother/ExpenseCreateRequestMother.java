package dev.hanluc.expensetracker.expenses.mother;

import dev.hanluc.expensetracker.Mother;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpenseCreateRequest;
import org.instancio.Instancio;

public class ExpenseCreateRequestMother {

  public static Mother<ExpenseCreateRequest> random() {
    return new Mother<>(
        Instancio.of(ExpenseCreateRequest.class)
            .toModel()
    );
  }

}
