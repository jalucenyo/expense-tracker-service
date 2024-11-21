package dev.hanluc.expensetracker.common.domain.vo;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Embeddable
public record Money(

  @NotNull
  @Min(1)
  Long value,

  @NotNull
  Integer exponent

) { }
