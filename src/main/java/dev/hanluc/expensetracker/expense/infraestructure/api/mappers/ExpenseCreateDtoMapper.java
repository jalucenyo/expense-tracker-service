package dev.hanluc.expensetracker.expense.infraestructure.api.mappers;

import dev.hanluc.expensetracker.expense.application.CreateExpenseUseCase;
import dev.hanluc.expensetracker.expense.infraestructure.api.spec.dto.ExpenseCreateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseCreateDtoMapper {

  CreateExpenseUseCase.ExpenseCreate toExpenseCreate(ExpenseCreateDto createExpense);

}
