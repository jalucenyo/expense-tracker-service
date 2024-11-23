package dev.hanluc.expensetracker.expenses.application.asserts;

import jakarta.validation.ConstraintViolation;
import org.assertj.core.api.AbstractAssert;

import java.util.Set;

public class ValidationAssert<T> extends AbstractAssert<ValidationAssert<T>, Set<ConstraintViolation<T>>> {

  protected ValidationAssert(Set<ConstraintViolation<T>> constaint) {
    super(constaint, ValidationAssert.class);
  }

  public static <T> ValidationAssert<T> then(Set<ConstraintViolation<T>> constraint) {
    return new ValidationAssert<>(constraint);
  }

  public ValidationAssert<T> hasFields(String... fields) {
    isNotNull();
    for (String field : fields) {
      boolean found = actual.stream().anyMatch(e -> e.getPropertyPath().toString().equals(field));
      if (!found) {
        failWithMessage("Expected error to contain field <%s> but was not found", field);
      }
    }
    return this;
  }

}
