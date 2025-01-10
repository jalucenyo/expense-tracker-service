package dev.hanluc.expensetracker.budgets.infrastructure.api.mappers.response;

import dev.hanluc.expensetracker.budgets.domain.Budget;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetPaginatedResponse;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", uses = { BudgetResponseMapper.class })
public interface BudgetPaginatedResponseMapper {

  BudgetPaginatedResponse toResponse(Page<Budget> budgets);

}
