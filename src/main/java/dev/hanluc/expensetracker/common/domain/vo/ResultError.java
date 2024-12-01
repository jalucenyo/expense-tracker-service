package dev.hanluc.expensetracker.common.domain.vo;

public record ResultError(
  String field,
  String message,
  ErrorType type
) {

  public enum ErrorType {
    PARAMS_INVALID
  }

  public static ResultError of(String message) {
    return new ResultError(null, message, ErrorType.PARAMS_INVALID);
  }

  public static ResultError ofPramInvalid(String field, String message) {
    return new ResultError(field, message, ErrorType.PARAMS_INVALID);
  }

}