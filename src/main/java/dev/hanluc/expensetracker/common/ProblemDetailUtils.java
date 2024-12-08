package dev.hanluc.expensetracker.common;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProblemDetailUtils {

  public static ProblemDetail fromContraintViolation(ConstraintViolationException ex) {
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

  private ProblemDetailUtils() { }

}
