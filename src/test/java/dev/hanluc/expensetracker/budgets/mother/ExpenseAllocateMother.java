package dev.hanluc.expensetracker.budgets.mother;

import static org.instancio.Select.field;

import dev.hanluc.expensetracker.Mother;
import dev.hanluc.expensetracker.budgets.applicaion.ExpenseAllocateUseCase.ExpenseAllocate;
import dev.hanluc.expensetracker.common.domain.vo.Money;
import org.instancio.Instancio;

public class ExpenseAllocateMother {

  public static Mother<ExpenseAllocate> random() {
    return new Mother<>(
        Instancio.of(ExpenseAllocate.class)
            .supply(field(ExpenseAllocate::amount), random -> new Money(random.longRange(1000L, 2000L), random.intRange(1, 2)))
            .generate(field(ExpenseAllocate::transactionDate), gen -> gen.temporal().offsetDateTime().past())
            .toModel()
    );
  }

}
