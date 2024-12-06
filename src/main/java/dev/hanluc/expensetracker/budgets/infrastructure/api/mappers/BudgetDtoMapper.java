package dev.hanluc.expensetracker.budgets.infrastructure.api.mappers;

import dev.hanluc.expensetracker.budgets.domain.Budget;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BudgetDtoMapper {

  BudgetResponse toBudgetDto(Budget budget);

}
