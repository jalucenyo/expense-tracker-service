package dev.hanluc.expensetracker.expense.domain.vo;

import java.util.List;
import java.util.function.Function;

public record Result<T>(T value, List<Error> errors) {

  public static <T> Result<T> success(T value) {
    return new Result<>(value, null);
  }

  public static <T> Result<T> failure(List<Error> errors) {
    return new Result<>(null, errors);
  }

  public boolean isSuccess() {
    return errors == null || errors.isEmpty();
  }

  public boolean isFailure() {
    return errors != null && !errors.isEmpty();
  }

  public <U> U fold(Function<List<Error>, U> ifError, Function<T, U> isSuccess) {
    return isSuccess() ? isSuccess.apply(value) : ifError.apply(errors);
  }

}
