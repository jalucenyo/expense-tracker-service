package dev.hanluc.expensetracker.expense.infraestructure.api.mappers;

import dev.hanluc.expensetracker.api.spec.dto.ExpenseDto;
import dev.hanluc.expensetracker.expense.domain.Expense;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseDtoMapper {

  ExpenseDto toExpenseDto(Expense expense);

}
