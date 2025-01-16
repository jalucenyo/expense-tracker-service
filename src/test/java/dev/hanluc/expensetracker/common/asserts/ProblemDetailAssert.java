package dev.hanluc.expensetracker.common.asserts;

import static java.util.function.Predicate.not;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.assertj.core.api.AbstractAssert;
import org.springframework.http.ProblemDetail;

public class ProblemDetailAssert extends AbstractAssert<ProblemDetailAssert, ProblemDetail> {

  protected ProblemDetailAssert(ProblemDetail actual) {
    super(actual, ProblemDetailAssert.class);
  }

  public static ProblemDetailAssert then(ProblemDetail actual) {
    return new ProblemDetailAssert(actual);
  }

  public ProblemDetailAssert hasTitle(String expected) {
    isNotNull();
    if (!Objects.equals(actual.getTitle(), expected)) {
      failWithMessage("Assert error, expected title: <%s> with content: <%s>", expected, actual.getTitle());
    }
    return this;
  }

  public ProblemDetailAssert hasDetail(String expected) {
    isNotNull();
    if (!Objects.equals(actual.getDetail(), expected)) {
      failWithMessage("Assert error, expected detail: <%s> with content: <%s>", expected, actual.getDetail());
    }
    return this;
  }

  public ProblemDetailAssert hasFieldError(String... expected) {
    isNotNull();
    if (!actual.getProperties().containsKey("errors")) {
      failWithMessage("Assert error, not contains errors");
    }

    final var fieldsExpected = Arrays.asList(expected);
    final List<String> fieldErrors = ((Map<String, List<String>>) actual.getProperties().get("errors")).keySet().stream()
        .filter(not(fieldsExpected::contains))
        .toList();
    if(!fieldErrors.isEmpty()) {
      failWithMessage("Assert error, unexpected fields: %s", fieldErrors);
    }

    return this;
  }
}
