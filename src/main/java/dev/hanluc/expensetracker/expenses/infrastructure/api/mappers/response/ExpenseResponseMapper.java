package dev.hanluc.expensetracker.expenses.infrastructure.api.mappers.response;

import dev.hanluc.expensetracker.expenses.domain.Expense;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpenseResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseResponseMapper {

  ExpenseResponse toResponse(Expense expense);

}
