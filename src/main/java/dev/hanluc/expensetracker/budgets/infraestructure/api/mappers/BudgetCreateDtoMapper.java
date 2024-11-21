package dev.hanluc.expensetracker.budgets.infraestructure.api.mappers;

import dev.hanluc.expensetracker.api.spec.dto.BudgetCreateDto;
import dev.hanluc.expensetracker.budgets.applicaion.CreateBudgetUseCase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BudgetCreateDtoMapper {

  CreateBudgetUseCase.BudgetCreate toBudgetCreate(BudgetCreateDto createBudget);

}
