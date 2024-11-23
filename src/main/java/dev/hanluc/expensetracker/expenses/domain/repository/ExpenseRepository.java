package dev.hanluc.expensetracker.expenses.domain.repository;

import dev.hanluc.expensetracker.expenses.domain.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends
  CrudRepository<Expense, UUID>,
  PagingAndSortingRepository<Expense, UUID> {

  Page<Expense> findAllByTransactionDateBetweenOrderByTransactionDateDesc(
    OffsetDateTime startDate,
    OffsetDateTime endDate,
    Pageable pageable
  );

}
