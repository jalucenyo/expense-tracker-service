package dev.hanluc.expensetracker.expense.infraestructure.api.mappers;

import dev.hanluc.expensetracker.api.spec.dto.ExpenseCreateDto;
import dev.hanluc.expensetracker.expense.application.CreateExpenseUseCase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseCreateDtoMapper {

  CreateExpenseUseCase.ExpenseCreate toExpenseCreate(ExpenseCreateDto createExpense);

}
