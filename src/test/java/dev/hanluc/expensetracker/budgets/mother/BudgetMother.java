package dev.hanluc.expensetracker.budgets.mother;

import static org.instancio.Select.field;

import dev.hanluc.expensetracker.Mother;
import dev.hanluc.expensetracker.budgets.domain.Budget;
import dev.hanluc.expensetracker.common.domain.vo.Money;
import org.instancio.Instancio;

public class BudgetMother {

  public static Mother<Budget> random() {
    return new Mother<>(
        Instancio.of(Budget.class)
            .generate(field(Money.class, "value"), gen -> gen.longs().range(10000L, 20000L))
            .generate(field(Money.class, "exponent"), gen -> gen.ints().range(1, 2))
            .generate(field(Budget::getStartDate), gen -> gen.temporal().offsetDateTime().past())
            .generate(field(Budget::getEndDate), gen -> gen.temporal().offsetDateTime().future())
            .supply(field(Budget::getCategory), gen -> gen.oneOf("HOME", "FOOD", "TRANSPORT", "HEALTH", "EDUCATION"))
            .toModel()
    );
  }

}
