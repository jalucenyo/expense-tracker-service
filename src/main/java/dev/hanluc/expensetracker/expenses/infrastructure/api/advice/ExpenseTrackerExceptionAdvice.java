package dev.hanluc.expensetracker.expenses.infrastructure.api.advice;

import dev.hanluc.expensetracker.common.domain.vo.ResultError;
import dev.hanluc.expensetracker.expenses.domain.exception.ExpenseTrackerException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExpenseTrackerExceptionAdvice {

  @ExceptionHandler(ExpenseTrackerException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ProblemDetail handleBadRequestException(ExpenseTrackerException exception) {
    ProblemDetail problemDetails = ProblemDetail.forStatus(HttpStatus.CONFLICT);
    problemDetails.setProperty("errors", exception.getValidationErrors().stream()
      .collect(Collectors.toMap(ResultError::field, ResultError::message)));
    return problemDetails;
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ProblemDetail handleConstraintViolation(ConstraintViolationException ex) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    problemDetail.setTitle("Validation Error");
    problemDetail.setDetail("Constraint violations occurred.");

    Map<String, List<String>> violations = ex.getConstraintViolations().stream()
      .collect(Collectors.groupingBy(
        violation -> violation.getPropertyPath().toString(),
        Collectors.mapping(ConstraintViolation::getMessage, Collectors.toList())
      ));

    problemDetail.setProperty("errors", violations);
    return problemDetail;
  }

}