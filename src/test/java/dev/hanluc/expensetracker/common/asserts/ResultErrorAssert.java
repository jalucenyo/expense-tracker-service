package dev.hanluc.expensetracker.common.asserts;

import dev.hanluc.expensetracker.common.domain.vo.ResultError;
import org.assertj.core.api.AbstractAssert;

import java.util.List;

public class ResultErrorAssert extends AbstractAssert<ResultErrorAssert, List<ResultError>> {

  protected ResultErrorAssert(List<ResultError> error) {
    super(error, ResultErrorAssert.class);
  }

  public static ResultErrorAssert then(List<ResultError> error) {
    return new ResultErrorAssert(error);
  }

  public ResultErrorAssert hasErrorFields(String... fields) {
    isNotNull();
    if (actual.size() != fields.length) {
      failWithMessage("Expected error to have <%s> fields but had <%s>", fields.length, actual.size());
    }
    for (String field : fields) {
      boolean found = actual.stream().anyMatch(e -> e.field().equals(field));
      if (!found) {
        failWithMessage("Expected error to contain field <%s> but was not found", field);
      }
    }
    return this;
  }

  public ResultErrorAssert hasMessage(String message) {
    isNotNull();
    boolean found = actual.stream().anyMatch(e -> e.message().equals(message));
    if (!found) {
      failWithMessage("Expected error to contain message <%s> but was not found", message);
    }
    return this;
  }

  public ResultErrorAssert hasErrorTypes(ResultError.ErrorType... types) {
    isNotNull();
    for (ResultError.ErrorType type : types) {
      boolean found = actual.stream().anyMatch(e -> e.type().equals(type));
      if (!found) {
        failWithMessage("Expected error to contain type <%s> but was not found", type);
      }
    }
    return this;
  }

}
