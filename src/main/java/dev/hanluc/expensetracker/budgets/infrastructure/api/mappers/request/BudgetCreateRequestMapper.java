package dev.hanluc.expensetracker.budgets.infrastructure.api.mappers.request;

import dev.hanluc.expensetracker.budgets.applicaion.CreateBudgetUseCase.BudgetCreate;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BudgetCreateRequestMapper {

  BudgetCreate toDomain(BudgetCreateRequest createBudget);

}
