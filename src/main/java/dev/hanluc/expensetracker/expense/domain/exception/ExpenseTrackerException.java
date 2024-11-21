package dev.hanluc.expensetracker.expense.domain.exception;

import dev.hanluc.expensetracker.common.domain.DomainException;
import dev.hanluc.expensetracker.common.domain.vo.ResultError;

import java.util.List;

public class ExpenseTrackerException extends DomainException {
  public ExpenseTrackerException(List<ResultError> errors) {
    super(errors);
  }
}
