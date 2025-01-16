package dev.hanluc.expensetracker.expenses.asserts;

import java.time.OffsetDateTime;
import java.util.List;

import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpensePaginatedResponse;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpenseResponse;
import org.assertj.core.api.AbstractAssert;

public class ExpensePaginatedResponseAssert extends AbstractAssert<ExpensePaginatedResponseAssert, ExpensePaginatedResponse> {

  protected ExpensePaginatedResponseAssert(ExpensePaginatedResponse actual) {
    super(actual, ExpensePaginatedResponseAssert.class);
  }

  public static ExpensePaginatedResponseAssert then(ExpensePaginatedResponse actual) {
    return new ExpensePaginatedResponseAssert(actual);
  }

  public ExpensePaginatedResponseAssert betweenTransactionDate(OffsetDateTime fromDate, OffsetDateTime toDate) {
    isNotNull();
    List<ExpenseResponse> results = actual.getContent().stream()
        .filter(expense -> expense.getTransactionDate().isAfter(toDate)
            || expense.getTransactionDate().isBefore(fromDate))
        .toList();

    if (!results.isEmpty()) {
      failWithMessage("Expected error, the following expense ids should not appear: %s",
          results.stream().map(ExpenseResponse::getId).toList());
    }

    return this;
  }

}
