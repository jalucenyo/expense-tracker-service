package dev.hanluc.expensetracker.expense.application;

import dev.hanluc.expensetracker.expense.domain.Expense;
import dev.hanluc.expensetracker.expense.domain.repository.ExpenseRepository;
import dev.hanluc.expensetracker.expense.domain.vo.Error;
import dev.hanluc.expensetracker.expense.domain.vo.Result;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class QueryExpenseUseCaseImpl implements QueryExpenseUseCase, FindByIdExpenseUseCase {

  private final ExpenseRepository expenseRepository;

  public QueryExpenseUseCaseImpl(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  @Override
  public Page<Expense> query(ExpenseQuery query) {
    final var startDate = Optional.ofNullable(query.startDate()).orElse(OffsetDateTime.now().minusDays(1));
    final var endDate = Optional.ofNullable(query.endDate()).orElse(OffsetDateTime.now());

    return expenseRepository
      .findAllByTransactionDateBetweenOrderByTransactionDateDesc(startDate, endDate, query.pageable());
  }

  @Override
  public Result<Expense> findById(String id) {
    return expenseRepository.findById(UUID.fromString(id))
      .map(Result::success)
      .orElseGet(() -> Result.failure(Error.of("Expense not found")));
  }

}
