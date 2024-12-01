package dev.hanluc.expensetracker.common.domain;

import dev.hanluc.expensetracker.common.domain.vo.ResultError;

import java.util.List;

public class DomainException extends RuntimeException {
  private List<ResultError> validationErrors;

  public DomainException(List<ResultError> errors) {
    super();
    validationErrors = errors;
  }

  public List<ResultError> getValidationErrors() {
    return validationErrors;
  }
}
