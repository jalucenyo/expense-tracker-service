package dev.hanluc.expensetracker.budgets.infrastructure.api.advice;

import dev.hanluc.expensetracker.budgets.domain.exception.BudgetException;
import dev.hanluc.expensetracker.common.ProblemDetailUtils;
import dev.hanluc.expensetracker.common.domain.vo.ResultError;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@ControllerAdvice
public class BudgetExceptionAdvice {

  @ExceptionHandler(BudgetException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ProblemDetail handleBadRequestException(BudgetException exception) {
    ProblemDetail problemDetails = ProblemDetail.forStatus(HttpStatus.CONFLICT);
    problemDetails.setProperty("errors", exception.getValidationErrors().stream()
      .collect(Collectors.toMap(ResultError::field, ResultError::message)));
    return problemDetails;
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ProblemDetail handleConstraintViolation(ConstraintViolationException exception) {
    return ProblemDetailUtils.fromContraintViolation(exception);
  }

}