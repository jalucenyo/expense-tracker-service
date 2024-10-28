package dev.hanluc.expensetracker.expense.domain.asserts;

import dev.hanluc.expensetracker.expense.domain.vo.Error;
import org.assertj.core.api.AbstractAssert;

import java.util.List;

public class ErrorAssert extends AbstractAssert<ErrorAssert, List<Error>> {

  protected ErrorAssert(List<Error> error) {
    super(error, ErrorAssert.class);
  }

  public static ErrorAssert then(List<Error> error) {
    return new ErrorAssert(error);
  }

  public ErrorAssert hasErrorFields(String... fields) {
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

  public ErrorAssert hasErrorTypes(Error.ErrorType... types) {
    isNotNull();
    for (Error.ErrorType type : types) {
      boolean found = actual.stream().anyMatch(e -> e.type().equals(type));
      if (!found) {
        failWithMessage("Expected error to contain type <%s> but was not found", type);
      }
    }
    return this;
  }

}
