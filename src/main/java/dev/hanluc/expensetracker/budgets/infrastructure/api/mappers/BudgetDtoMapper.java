package dev.hanluc.expensetracker.budgets.infrastructure.api.mappers;

import dev.hanluc.expensetracker.budgets.domain.Budget;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BudgetDtoMapper {

  BudgetDto toBudgetDto(Budget budget);

}
