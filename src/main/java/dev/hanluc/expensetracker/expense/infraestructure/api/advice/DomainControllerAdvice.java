package dev.hanluc.expensetracker.expense.infraestructure.api.advice;

import dev.hanluc.expensetracker.expense.domain.vo.Error;
import dev.hanluc.expensetracker.expense.domain.exception.ExpenseTrackerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@ControllerAdvice
public class DomainControllerAdvice {

  @ExceptionHandler(ExpenseTrackerException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ProblemDetail handleBadRequestException(ExpenseTrackerException exception) {
    ProblemDetail problemDetails = ProblemDetail.forStatus(HttpStatus.CONFLICT);
    problemDetails.setProperty("errors", exception.getValidationErrors().stream()
      .collect(Collectors.toMap(Error::field, Error::message)));
    return problemDetails;
  }

}