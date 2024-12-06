package dev.hanluc.expensetracker.budgets.domain.repository;

import dev.hanluc.expensetracker.budgets.domain.Budget;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BudgetRepository extends
  CrudRepository<Budget, UUID>,
  PagingAndSortingRepository<Budget, UUID> {

  @Query("SELECT * " +
    "FROM budget " +
    "WHERE category = :category " +
    "AND start_date <= :date " +
    "AND end_date >= :date")
  Optional<Budget> findByCategoryAndDate(String category, OffsetDateTime date);

}
