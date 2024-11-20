package dev.hanluc.expensetracker.expense.infraestructure.api.mappers;

import dev.hanluc.expensetracker.api.spec.dto.ExpensePaginatedDto;
import dev.hanluc.expensetracker.expense.domain.Expense;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(
  componentModel = "spring",
  uses = { ExpenseDtoMapper.class }
)
public interface ExpensePaginatedDtoMapper {

  ExpensePaginatedDto toExpensePaginatedDto(Page<Expense> expenses);

}
