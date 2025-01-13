package dev.hanluc.expensetracker.expenses.mother;

import static org.instancio.Select.all;
import static org.instancio.Select.field;

import java.util.Arrays;

import dev.hanluc.expensetracker.expenses.application.CreateExpenseUseCase.ExpenseCreate;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;

public class ExpenseCreateMother {

  public static Model<ExpenseCreate> randomModel() {
    return Instancio.of(ExpenseCreate.class)
        .toModel();
  }

  public static ExpenseCreate random() {
    return Instancio.of(randomModel()).create();
  }

  public static ExpenseCreate withNullField(String... fields) {
    final var fieldSelectors = Arrays.stream(fields).map(Select::field).toArray(org.instancio.Selector[]::new);
    return Instancio.of(randomModel())
        .set(all(fieldSelectors), null)
        .create();
  }
  public static ExpenseCreate withEmptyField(String... fields) {
    final var fieldSelectors = Arrays.stream(fields).map(Select::field).toArray(org.instancio.Selector[]::new);
    return Instancio.of(randomModel())
        .set(all(fieldSelectors), "")
        .create();
  }

  public static ExpenseCreate withFieldValue(String field, Object value) {
    return Instancio.of(randomModel())
        .set(field(field), value)
        .create();
  }

}
