package dev.hanluc.expensetracker.expense.application;

import dev.hanluc.expensetracker.expense.application.CreateExpenseUseCase.ExpenseCreate;
import dev.hanluc.expensetracker.expense.application.asserts.ValidationAssert;
import dev.hanluc.expensetracker.expense.domain.repository.ExpenseRepository;
import dev.hanluc.expensetracker.expense.domain.vo.Money;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.Set;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.mock;

class CreateExpenseUseCaseTest {

  private final ExpenseRepository expenseRepository = mock(ExpenseRepository.class);
  private final CreateExpenseUseCase createExpenseUseCase = new CreateExpenseUseCaseImpl(null);
  private static Validator validator;

  @BeforeAll
  static void setupValidator() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void given_null_or_empty_all_fields_when_validate_then_return_invalid() {
    ExpenseCreate expenseCreate = new ExpenseCreate(
      null,
      "",
      null,
      "",
      "",
      "",
      "");

    Set<ConstraintViolation<ExpenseCreate>> violations = validator.validate(expenseCreate);

    ValidationAssert.then(violations)
      .hasFields("description", "amount", "transactionDate", "paymentMethod", "vendor");
  }

  @Test
  void given_null_fields_when_validate_then_return_invalid() {
    ExpenseCreate expenseCreate = new ExpenseCreate(
      new Money(1L, 1 ),
      null,
      null,
      null,
      null,
      null,
      null);

    Set<ConstraintViolation<ExpenseCreate>> violations = validator.validate(expenseCreate);

    ValidationAssert.then(violations)
      .hasFields("description", "transactionDate", "paymentMethod", "vendor", "recurrence");
  }

  @Test
  void given_amount_is_null_when_validate_then_return_invalid() {
    ExpenseCreate expenseCreate = new ExpenseCreate(
      null,
      "description",
      OffsetDateTime.now(),
      "CREDIT_CARD",
      "vendor",
      "DAILY",
      "");

    Set<ConstraintViolation<ExpenseCreate>> violations = validator.validate(expenseCreate);

    ValidationAssert.then(violations)
      .hasFields("amount");
  }

  @Disabled
  @Test
  void given_amount_is_zero_when_validate_then_return_invalid() {
    ExpenseCreate expenseCreate = new ExpenseCreate(
      new Money(0L, 0),
      "description",
      OffsetDateTime.now(),
      "CREDIT_CARD",
      "vendor",
      "DAILY",
      "");

    Set<ConstraintViolation<ExpenseCreate>> violations = validator.validate(expenseCreate);

    ValidationAssert.then(violations)
      .hasFields("amount");
  }

  @Test
  void given_all_fields_correct_when_validate_then_return_valid() {
    ExpenseCreate expenseCreate = new ExpenseCreate(
      new Money(1L, 1),
      "description",
      OffsetDateTime.now(),
      "CREDIT_CARD",
      "vendor",
      "DAILY",
      "");

    Set<ConstraintViolation<ExpenseCreate>> violations = validator.validate(expenseCreate);

    then(violations.isEmpty()).isTrue();
  }

  @Test
  void given_description_contains_invalid_chars_when_validate_then_return_invalid() {
    ExpenseCreate expenseCreate = new ExpenseCreate(
      new Money(1L, 1),
      "\"''",
      OffsetDateTime.now(),
      "CREDIT_CARD",
      "vendor",
      "DAILY",
      "");

    Set<ConstraintViolation<ExpenseCreate>> violations = validator.validate(expenseCreate);

    ValidationAssert.then(violations)
      .hasFields("description");
  }

}
