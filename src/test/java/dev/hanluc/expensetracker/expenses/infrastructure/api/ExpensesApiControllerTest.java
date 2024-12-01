package dev.hanluc.expensetracker.expenses.infrastructure.api;

import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpenseCreateDto;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpenseDto;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpensePaginatedDto;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.MoneyDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.*;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.BDDAssertions.then;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:db/data-init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "classpath:db/data-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class ExpensesApiControllerTest {

  @Container
  @ServiceConnection
  static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(
    DockerImageName.parse("postgres:latest"));

  @Autowired
  TestRestTemplate restTemplate;

  @Test
  void should_find_expense_by_id() {
    final var expenseId = "0989de36-843b-4be8-882a-4ec9b219b1f3";

    ResponseEntity<ExpenseDto> response = restTemplate.getForEntity("/expenses/" + expenseId, ExpenseDto.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    then(response.getBody()).isNotNull();
    then(response.getBody().getId()).isEqualTo(expenseId);
  }

  @Test
  void should_filter_between_dates(){
      final var startDate = ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC).minusMonths(1).minusDays(1);
      final var endDate = ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC).minusMonths(1).plusDays(1);
      ResponseEntity<ExpensePaginatedDto> response = restTemplate.getForEntity(
        "/expenses?startDate=" + startDate.format(DateTimeFormatter.ISO_DATE_TIME) + "&endDate="+ endDate.format(DateTimeFormatter.ISO_DATE_TIME),
        ExpensePaginatedDto.class);

      then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
      then(response.getBody()).isNotNull();
      then(response.getBody().getContent()).hasSizeGreaterThan(0);
      then(response.getBody().getContent())
        .extracting("transactionDate")
        .allMatch(date -> {
          OffsetDateTime transactionDate = OffsetDateTime.parse(date.toString());
          return transactionDate.isAfter(startDate.toOffsetDateTime()) && transactionDate.isBefore(endDate.toOffsetDateTime());
      });
  }

  @Test
  void should_find_all_expenses() {
    ResponseEntity<ExpensePaginatedDto> response = restTemplate.getForEntity("/expenses", ExpensePaginatedDto.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    then(response.getBody()).isNotNull();
    then(response.getBody().getContent())
      .extracting("transactionDate")
      .allMatch(date -> {
        OffsetDateTime transactionDate = OffsetDateTime.parse(date.toString());
        return transactionDate.isAfter(OffsetDateTime.now().minusDays(1)) && transactionDate.isBefore(OffsetDateTime.now());
      });
  }

  @Test
  void should_find_paginated_expenses() {
    ResponseEntity<ExpensePaginatedDto> response = restTemplate.getForEntity("/expenses?page=0&size=2", ExpensePaginatedDto.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    then(response.getBody()).isNotNull();
    then(response.getBody().getTotalElements()).isEqualTo(5);
    then(response.getBody().getContent()).hasSize(2);
  }

  @Test
  @Sql(scripts = "classpath:db/data-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  @Sql(scripts = "classpath:db/data-init.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void should_create_expense() {
    ResponseEntity<Void> response = restTemplate.postForEntity("/expenses",
      new ExpenseCreateDto()
        .description("Test 1")
        .vendor("Vendor 1")
        .transactionDate(OffsetDateTime.now())
        .recurrence(ExpenseCreateDto.RecurrenceEnum.NONE)
        .amount(new MoneyDto().value(983L).exponent(2))
        .paymentMethod(ExpenseCreateDto.PaymentMethodEnum.CASH)
      , Void.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    then(response.getHeaders().getLocation()).isNotNull();
    String uuidRegex = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$";
    then(response.getHeaders().getLocation().toString()).containsPattern(uuidRegex);
  }

  @Test
  void should_return_error_when_description_is_empty() {
    ResponseEntity<ProblemDetail> response = restTemplate.postForEntity("/expenses",
      new ExpenseCreateDto()
        .description("")
        .vendor("")
        .transactionDate(OffsetDateTime.now())
        .recurrence(ExpenseCreateDto.RecurrenceEnum.NONE)
        .amount(new MoneyDto().value(983L).exponent(2))
        .paymentMethod(ExpenseCreateDto.PaymentMethodEnum.CASH)
      , ProblemDetail.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  @Sql(scripts = "classpath:db/data-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  @Sql(scripts = "classpath:db/data-init.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void should_delete_expense() {

    restTemplate.delete("/expenses/" + "8b540e0f-0a73-4459-8b91-8f3b9d71ec30");

    ResponseEntity<ExpensePaginatedDto> response = restTemplate.getForEntity("/expenses", ExpensePaginatedDto.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    // TODO: Add assert get expense by id to check if the expense was deleted
  }

  @Test
  void should_return_error_when_id_format_incorrect() {

    ResponseEntity<ProblemDetail> response = restTemplate
      .exchange("/expenses/" + "INCORRECT_ID", HttpMethod.DELETE, null, ProblemDetail.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

}
