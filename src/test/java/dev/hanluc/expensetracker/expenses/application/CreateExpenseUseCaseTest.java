package dev.hanluc.expensetracker.expenses.application;

import dev.hanluc.expensetracker.expenses.application.CreateExpenseUseCase.ExpenseCreate;
import dev.hanluc.expensetracker.common.asserts.ValidationAssert;
import dev.hanluc.expensetracker.expenses.domain.repository.ExpenseRepository;
import dev.hanluc.expensetracker.expenses.mother.ExpenseCreateMother;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;

class CreateExpenseUseCaseTest {

  private final ExpenseRepository expenseRepository = mock(ExpenseRepository.class);

  private final CreateExpenseUseCase createExpenseUseCase = new CreateExpenseUseCase(null, null);

  private static Validator validator;

  @BeforeAll
  static void setupValidator() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @ParameterizedTest
  @MethodSource("provideEmptyField")
  void given_empty_fields_when_validate_then_return_invalid(List<String> fields) {
    ExpenseCreate expenseCreate = ExpenseCreateMother.withEmptyField(fields.toArray(String[]::new));

    Set<ConstraintViolation<ExpenseCreate>> violations = validator.validate(expenseCreate);

    ValidationAssert.then(violations).hasFields(fields.toArray(String[]::new));
  }

  private static Stream<List<String>> provideEmptyField() {
    return Stream.of(
        List.of("description"),
        List.of("paymentMethod"),
        List.of("vendor"),
        List.of("category"),
        List.of("description", "paymentMethod", "vendor", "category")

    );
  }

  @ParameterizedTest
  @MethodSource("provideNullField")
  void given_null_fields_when_validate_then_return_invalid(List<String> fields) {
    ExpenseCreate expenseCreate = ExpenseCreateMother.withNullField(fields.toArray(String[]::new));

    Set<ConstraintViolation<ExpenseCreate>> violations = validator.validate(expenseCreate);

    ValidationAssert.then(violations).hasFields(fields.toArray(String[]::new));
  }

  private static Stream<List<String>> provideNullField() {
    return Stream.of(
        List.of("amount"),
        List.of("transactionDate"),
        List.of("recurrence"),
        List.of("amount", "transactionDate", "recurrence")
    );
  }

  @Test
  void given_all_fields_correct_when_validate_then_return_valid() {
    ExpenseCreate expenseCreate = ExpenseCreateMother.random();

    Set<ConstraintViolation<ExpenseCreate>> violations = validator.validate(expenseCreate);

    ValidationAssert.then(violations).isEmpty();
  }

  @Test
  void given_description_contains_invalid_chars_when_validate_then_return_invalid() {
    ExpenseCreate expenseCreate = ExpenseCreateMother.withFieldValue("description", "\"''");

    Set<ConstraintViolation<ExpenseCreate>> violations = validator.validate(expenseCreate);

    ValidationAssert.then(violations).hasFields("description");
  }

}
