package dev.hanluc.expensetracker.expenses.infrastructure.api;

import dev.hanluc.expensetracker.TestContainersConfiguration;
import dev.hanluc.expensetracker.TestRestTemplateConfig;
import dev.hanluc.expensetracker.TokenProvider;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpensePaginatedResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import({TestContainersConfiguration.class, TestRestTemplateConfig.class})
@Sql(scripts = "classpath:db/expense/data-init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "classpath:db/expense/data-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class QueryExpensesApiControllerTest {

  @Autowired
  TestRestTemplate restTemplate;

  @Autowired
  private TokenProvider tokenProvider;

  @BeforeEach
  public void setUp() {
    tokenProvider.validToken();
  }

  @Test
  void should_find_all_expenses() {
    ResponseEntity<ExpensePaginatedResponse> response = restTemplate.getForEntity("/expenses", ExpensePaginatedResponse.class);

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
    ResponseEntity<ExpensePaginatedResponse> response = restTemplate.getForEntity("/expenses?page=0&size=2", ExpensePaginatedResponse.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    then(response.getBody()).isNotNull();
    then(response.getBody().getTotalElements()).isEqualTo(5);
    then(response.getBody().getContent()).hasSize(2);
  }

  @Test
  void should_filter_between_dates() {
    final var startDate = ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC).minusMonths(1).minusDays(1);
    final var endDate = ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC).minusMonths(1).plusDays(1);

    ResponseEntity<ExpensePaginatedResponse> response = restTemplate.getForEntity(
      "/expenses?startDate=" + startDate.format(DateTimeFormatter.ISO_DATE_TIME) + "&endDate=" + endDate.format(DateTimeFormatter.ISO_DATE_TIME),
      ExpensePaginatedResponse.class);

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

}