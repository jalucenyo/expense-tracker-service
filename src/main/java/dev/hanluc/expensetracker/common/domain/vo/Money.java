package dev.hanluc.expensetracker.common.domain.vo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record Money(

  @NotNull
  @Min(1)
  Long value,

  @NotNull
  Integer exponent

) { }
