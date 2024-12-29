package dev.hanluc.expensetracker.accounts.domain.exception;

import dev.hanluc.expensetracker.common.domain.DomainException;
import dev.hanluc.expensetracker.common.domain.vo.ResultError;

import java.util.List;

public class AccountException extends DomainException {

  public AccountException(List<ResultError> errors) {
    super(errors);
  }

  public AccountException(String message) {
    //TODO: Pending refactor to use ResultError
    super(null);
  }

}
