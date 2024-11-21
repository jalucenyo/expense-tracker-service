package dev.hanluc.expensetracker.common.domain.vo;

import java.util.List;
import java.util.function.Function;

public record Result<T>(T value, List<ResultError> errors) {

  public static <T> Result<T> success(T value) {
    return new Result<>(value, null);
  }

  public static <T> Result<T> failure(ResultError errors) {
    return new Result<>(null, List.of(errors));
  }

  public static <T> Result<T> failure(List<ResultError> errors) {
    return new Result<>(null, errors);
  }

  public boolean isSuccess() {
    return errors == null || errors.isEmpty();
  }

  public boolean isFailure() {
    return errors != null && !errors.isEmpty();
  }

  public <U> U fold(Function<List<ResultError>, U> ifError, Function<T, U> isSuccess) {
    return isSuccess() ? isSuccess.apply(value) : ifError.apply(errors);
  }

}
