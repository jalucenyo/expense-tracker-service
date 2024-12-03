package dev.hanluc.expensetracker.expenses.infrastructure.api.mappers;

import dev.hanluc.expensetracker.expenses.application.CreateExpenseUseCase;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpenseCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseCreateDtoMapper {

  CreateExpenseUseCase.ExpenseCreate toExpenseCreate(ExpenseCreateRequest createExpense);

}
