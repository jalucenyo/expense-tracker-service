package dev.hanluc.expensetracker.expense.domain.vo;

public record Error(
  String field,
  String message,
  ErrorType type
) {

  public enum ErrorType {
    PARAMS_INVALID
  }

  public static Error of(String message) {
    return new Error(null, message, ErrorType.PARAMS_INVALID);
  }

  public static Error ofPramInvalid(String field, String message) {
    return new Error(field, message, ErrorType.PARAMS_INVALID);
  }

}