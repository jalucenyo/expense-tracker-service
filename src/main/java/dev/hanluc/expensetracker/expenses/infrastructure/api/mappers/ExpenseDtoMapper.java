package dev.hanluc.expensetracker.expenses.infrastructure.api.mappers;

import dev.hanluc.expensetracker.expenses.domain.Expense;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpenseResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseDtoMapper {

  ExpenseResponse toExpenseDto(Expense expense);

}
