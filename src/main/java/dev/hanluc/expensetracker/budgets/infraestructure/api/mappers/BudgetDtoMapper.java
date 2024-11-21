package dev.hanluc.expensetracker.budgets.infraestructure.api.mappers;

import dev.hanluc.expensetracker.api.spec.dto.BudgetDto;
import dev.hanluc.expensetracker.budgets.domain.Budget;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BudgetDtoMapper {

  BudgetDto toBudgetDto(Budget budget);

}
