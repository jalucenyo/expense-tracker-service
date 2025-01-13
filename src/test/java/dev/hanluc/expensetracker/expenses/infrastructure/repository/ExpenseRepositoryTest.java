package dev.hanluc.expensetracker.expenses.infrastructure.repository;

import dev.hanluc.expensetracker.expenses.mother.ExpenseMother;
import dev.hanluc.expensetracker.expenses.domain.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;


import static org.assertj.core.api.BDDAssertions.then;

@Testcontainers
@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ExpenseRepositoryTest {

  @Container
  @ServiceConnection
  static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(
      DockerImageName.parse("postgres:latest"));

  @Autowired
  ExpenseRepository expenseRepository;

  @Test
  void connection_container_established() {
    then(postgresContainer.isCreated()).isTrue();
    then(postgresContainer.isRunning()).isTrue();
  }

  @Test
  void should_count_expenses_then_return_size_greater_zero() {
    int expensesSize = 2;
    expenseRepository.saveAll(ExpenseMother.random().insideList(expensesSize));

    then(expenseRepository.count()).isEqualTo(expensesSize);
  }

}