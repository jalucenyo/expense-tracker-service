package dev.hanluc.expensetracker.common.domain;

import dev.hanluc.expensetracker.common.domain.vo.Money;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

class MoneyTest {

  @Test
  void should_add_money_with_same_exponent() {
    Money money1 = new Money(100L, 2);
    Money money2 = new Money(200L, 2);

    Money result = money1.add(money2);

    then(result).isEqualTo(new Money(300L, 2));
  }

  @Test
  void should_add_money_with_different_exponent() {
    Money money1 = new Money(100L, 2);
    Money money2 = new Money(200L, 3);

    Money result = money1.add(money2);

    then(result).isEqualTo(new Money(1200L, 3));
  }

  @Test
  void should_throw_exception_when_adding_null_money() {
    Money money = new Money(100L, 2);

    thenThrownBy(() -> money.add(null))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("The money to add cannot be null.");
  }

}
