package dev.hanluc.expensetracker.common.asserts;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import org.assertj.core.api.AbstractAssert;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class ValidationAssert<T> extends AbstractAssert<ValidationAssert<T>, Set<ConstraintViolation<T>>> {

  protected ValidationAssert(Set<ConstraintViolation<T>> constaint) {
    super(constaint, ValidationAssert.class);
  }

  public static <T> ValidationAssert<T> then(Set<ConstraintViolation<T>> constraint) {
    return new ValidationAssert<>(constraint);
  }

  public ValidationAssert<T> isEmpty() {
    if(!actual.isEmpty()) {
      failWithMessage("Expected error not empty constraints violations %s", actual);
    }
    return this;
  }

  public ValidationAssert<T> hasFields(String... fields) {
    isNotNull();

    final var expectedFields = List.of(fields);
    final var actualFields = actual.stream().map(ConstraintViolation::getPropertyPath).map(Path::toString).toList();

    final var missingFields = Stream.concat(
        expectedFields.stream().filter(e -> !actualFields.contains(e)),
        actualFields.stream().filter(e -> !expectedFields.contains(e))
        ).toList();

    if (!missingFields.isEmpty()) {
      failWithMessage("Expected error missing fields check: %s", missingFields);
    }

    return this;
  }

}
