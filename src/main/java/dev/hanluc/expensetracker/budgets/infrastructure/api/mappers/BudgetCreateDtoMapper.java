package dev.hanluc.expensetracker.budgets.infrastructure.api.mappers;

import dev.hanluc.expensetracker.budgets.applicaion.CreateBudgetUseCase;
import dev.hanluc.expensetracker.budgets.infrastructure.api.dto.BudgetCreateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BudgetCreateDtoMapper {

  CreateBudgetUseCase.BudgetCreate toBudgetCreate(BudgetCreateDto createBudget);

}