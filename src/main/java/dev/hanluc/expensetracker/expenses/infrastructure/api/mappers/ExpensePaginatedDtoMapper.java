package dev.hanluc.expensetracker.expenses.infrastructure.api.mappers;

import dev.hanluc.expensetracker.expenses.domain.Expense;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpensePaginatedDto;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(
  componentModel = "spring",
  uses = { ExpenseDtoMapper.class }
)
public interface ExpensePaginatedDtoMapper {

  ExpensePaginatedDto toExpensePaginatedDto(Page<Expense> expenses);

}
