package dev.hanluc.expensetracker.budgets.mother;

import static org.instancio.Select.field;

import dev.hanluc.expensetracker.budgets.domain.Budget;
import dev.hanluc.expensetracker.common.domain.vo.Money;
import org.instancio.Instancio;
import org.instancio.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class BudgetMother {

  private Model<Budget> mother;

  public BudgetMother(Model<Budget> mother) {
    this.mother = mother;
  }

  public static Model<Budget> random(){
    return Instancio.of(Budget.class)
        .generate(field(Money.class, "value"), gen -> gen.longs().range(10000L, 20000L))
        .generate(field(Money.class, "exponent"), gen -> gen.ints().range(1, 2))
        .generate(field(Budget::getStartDate), gen -> gen.temporal().offsetDateTime().past())
        .generate(field(Budget::getEndDate), gen -> gen.temporal().offsetDateTime().future())
        .supply(field(Budget::getCategory), gen -> gen.oneOf("HOME", "FOOD", "TRANSPORT", "HEALTH", "EDUCATION"))
        .toModel();
  }

  public static BudgetMother customize(){
    return new BudgetMother(Instancio.of(random()).toModel());
  }

  public Budget create(){
    return Instancio.of(mother).create();
  }

  public BudgetMother withConsumed(long value, int exponent) {
    mother = Instancio.of(mother)
        .set(field(Budget::getConsumed), new Money(value, exponent))
        .toModel();
    return this;
  }

  public static Page<Budget> randomPage(int size){
    return new PageImpl<>(Instancio.ofList(random()).size(size).create());
  }

  public static Page<Budget> emptyPage(){
    return new PageImpl<>(List.of());
  }

}
