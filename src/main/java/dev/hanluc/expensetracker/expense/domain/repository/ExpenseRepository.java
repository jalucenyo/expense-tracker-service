package dev.hanluc.expensetracker.expense.domain.repository;

import dev.hanluc.expensetracker.expense.domain.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExpenseRepository extends
  CrudRepository<Expense, UUID>,
  PagingAndSortingRepository<Expense, UUID> {

}
