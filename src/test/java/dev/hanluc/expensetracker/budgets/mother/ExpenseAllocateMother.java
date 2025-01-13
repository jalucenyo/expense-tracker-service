package dev.hanluc.expensetracker.budgets.mother;

import static org.instancio.Select.field;

import dev.hanluc.expensetracker.budgets.applicaion.ExpenseAllocateUseCase.ExpenseAllocate;
import dev.hanluc.expensetracker.budgets.domain.Budget;
import dev.hanluc.expensetracker.common.domain.vo.Money;
import org.instancio.Instancio;
import org.instancio.Model;

public class ExpenseAllocateMother {

  public static Model<ExpenseAllocate> randomModel() {
    return Instancio.of(ExpenseAllocate.class)
        .supply(field(ExpenseAllocate::amount), random -> new Money(random.longRange(1000L, 2000L), random.intRange(1, 2)))
        .generate(field(ExpenseAllocate::transactionDate), gen -> gen.temporal().offsetDateTime().past())
        .toModel();
  }

  public static ExpenseAllocate random(){
    return Instancio.of(randomModel()).create();
  }

  public static ExpenseAllocate randomWithAmount(long value, int exponent){
    return Instancio.of(randomModel())
        .set(field(ExpenseAllocate::amount), new Money(value, exponent))
        .create();
  }

  public static ExpenseAllocate relatedTo(Budget budget){
    return Instancio.of(randomModel())
        .set(field(ExpenseAllocate::category), budget.getCategory())
        .supply(field(ExpenseAllocate::amount), random ->
            new Money(random.longRange(0L, budget.getAmount().value()), budget.getAmount().exponent()))
        .create();
  }

}
