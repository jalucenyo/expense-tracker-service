package dev.hanluc.expensetracker.expense.infraestructure.api.mappers;

import dev.hanluc.expensetracker.expense.domain.Expense;
import dev.hanluc.expensetracker.expense.infraestructure.api.spec.dto.ExpenseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseDtoMapper {

  ExpenseDto toExpenseDto(Expense expense);

}
