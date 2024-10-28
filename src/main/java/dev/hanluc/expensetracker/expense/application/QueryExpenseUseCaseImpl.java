package dev.hanluc.expensetracker.expense.application;

import dev.hanluc.expensetracker.expense.domain.Expense;
import dev.hanluc.expensetracker.expense.domain.repository.ExpenseRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class QueryExpenseUseCaseImpl implements QueryExpenseUseCase {

  private final ExpenseRepository expenseRepository;

  public QueryExpenseUseCaseImpl(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  @Override
  public Page<Expense> query(ExpenseQuery query) {
    return expenseRepository.findAll(query.pageable());
  }

}
