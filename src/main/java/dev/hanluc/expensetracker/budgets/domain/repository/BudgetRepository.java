package dev.hanluc.expensetracker.budgets.domain.repository;

import dev.hanluc.expensetracker.budgets.domain.Budget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BudgetRepository extends
  CrudRepository<Budget, UUID>,
  PagingAndSortingRepository<Budget, UUID> {

}
