package dev.hanluc.expensetracker.expense.infraestructure.api.mappers;

import dev.hanluc.expensetracker.expense.domain.Expense;
import dev.hanluc.expensetracker.expense.infraestructure.api.spec.dto.ExpensePaginatedDto;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface ExpensePaginatedDtoMapper {

  ExpensePaginatedDto toExpensePaginatedDto(Page<Expense> expenses);

}
