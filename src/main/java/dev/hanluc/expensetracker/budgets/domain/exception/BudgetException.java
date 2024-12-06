package dev.hanluc.expensetracker.budgets.domain.exception;

import dev.hanluc.expensetracker.common.domain.DomainException;
import dev.hanluc.expensetracker.common.domain.vo.ResultError;

import java.util.List;

public class BudgetException extends DomainException {

  public BudgetException(List<ResultError> errors) {
    super(errors);
  }

  public BudgetException(String message) {
    //TODO: Pending refactor to use ResultError
    super(null);
  }

}
