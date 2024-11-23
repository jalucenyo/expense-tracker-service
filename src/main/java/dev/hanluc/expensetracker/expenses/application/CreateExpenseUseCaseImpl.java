package dev.hanluc.expensetracker.expenses.application;

import dev.hanluc.expensetracker.common.domain.vo.Result;
import dev.hanluc.expensetracker.expenses.domain.Expense;
import dev.hanluc.expensetracker.expenses.domain.events.ExpenseCreatedEvent;
import dev.hanluc.expensetracker.expenses.domain.repository.ExpenseRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateExpenseUseCaseImpl implements CreateExpenseUseCase {

  private final ExpenseRepository expenseRepository;
  private final ApplicationEventPublisher eventPublisher;

  public CreateExpenseUseCaseImpl(ExpenseRepository expenseRepository, ApplicationEventPublisher eventPublisher) {
    this.expenseRepository = expenseRepository;
    this.eventPublisher = eventPublisher;
  }

  @Override
  @Transactional
  public Result<Expense> create(ExpenseCreate expenseCreate) {
    return Result.success(expenseRepository.save(expenseCreate.toExpense()))
      .fold(
        Result::failure,
        expense -> {
          eventPublisher.publishEvent(new ExpenseCreatedEvent(expense.getId().toString()));
          return Result.success(expense);
        }
      );
  }

}
