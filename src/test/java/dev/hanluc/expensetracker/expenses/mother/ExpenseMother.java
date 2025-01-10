package dev.hanluc.expensetracker.expenses.mother;

import static org.instancio.Select.field;

import dev.hanluc.expensetracker.common.domain.vo.Money;
import dev.hanluc.expensetracker.expenses.domain.Expense;
import org.instancio.Instancio;
import org.instancio.Model;

import java.util.List;

public class ExpenseMother {

  public static Model<Expense> random() {
    return Instancio.of(Expense.class)
        .ignore(field("domainEvents"))
        .ignore(field("version"))
        .generate(field(Money.class, "value"), gen -> gen.longs().range(1000L, 2000L))
        .generate(field(Money.class, "exponent"), gen -> gen.ints().range(1, 2))
        .toModel();
  }

  public static List<Expense> random(int size) {
    return Instancio.ofList(random()).size(size).create();
  }

}
