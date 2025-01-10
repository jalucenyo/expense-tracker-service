package dev.hanluc.expensetracker.expenses.infrastructure.api.mappers.request;

import dev.hanluc.expensetracker.expenses.application.CreateExpenseUseCase.ExpenseCreate;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpenseCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseCreateRequestMapper {

  ExpenseCreate toDomain(ExpenseCreateRequest createExpense);

}
