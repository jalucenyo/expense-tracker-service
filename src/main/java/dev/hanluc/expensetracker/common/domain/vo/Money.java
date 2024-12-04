package dev.hanluc.expensetracker.common.domain.vo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record Money(

  @NotNull
  @Min(1)
  Long value,

  @NotNull
  Integer exponent

) {

  public Money add(Money money) {
    if (money == null) {
      throw new IllegalArgumentException("The money to add cannot be null.");
    }

    if(this.exponent.equals(money.exponent)) {
      return new Money(this.value + money.value, this.exponent);
    }

    long adjustedValue = (exponent > money.exponent)
      ? money.value * (long) Math.pow(10, (this.exponent - money.exponent))
      : value * (long) Math.pow(10, (money.exponent - this.exponent));

    return new Money(money.value + adjustedValue, money.exponent);
  }

}
