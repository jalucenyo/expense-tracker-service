package dev.hanluc.expensetracker.expense.domain.exception;

import dev.hanluc.expensetracker.expense.domain.vo.Error;

import java.util.List;

public class ExpenseTrackerException extends RuntimeException {

  private List<Error> validationErrors;

  public ExpenseTrackerException(List<Error> errors) {
    super();
    validationErrors = errors;
  }

  public List<Error> getValidationErrors() {
    return validationErrors;
  }

}
