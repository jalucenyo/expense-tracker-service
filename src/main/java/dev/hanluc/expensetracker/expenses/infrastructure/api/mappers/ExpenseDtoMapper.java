package dev.hanluc.expensetracker.expenses.infrastructure.api.mappers;

import dev.hanluc.expensetracker.expenses.domain.Expense;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpenseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseDtoMapper {

  ExpenseDto toExpenseDto(Expense expense);

}
