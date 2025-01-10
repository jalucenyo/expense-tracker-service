package dev.hanluc.expensetracker.expenses.infrastructure.api.mappers.response;

import dev.hanluc.expensetracker.expenses.domain.Expense;
import dev.hanluc.expensetracker.expenses.infrastructure.api.dto.ExpensePaginatedResponse;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(
  componentModel = "spring",
  uses = { ExpenseResponseMapper.class }
)
public interface ExpensePaginatedResponseMapper {

  ExpensePaginatedResponse toResponse(Page<Expense> expenses);

}
