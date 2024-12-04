package dev.hanluc.expensetracker.budgets.infrastructure.api.mappers;

import dev.hanluc.expensetracker.budgets.applicaion.CreateBudgetUseCase.BudgetCreate;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BudgetCreateDtoMapper {

  BudgetCreate toBudgetCreate(BudgetCreateRequest createBudget);

}
