package dev.hanluc.expensetracker.accounts.infrastructure.api.mappers;

import dev.hanluc.expensetracker.accounts.application.CreateAccountUseCase.AccountCreate;
import dev.hanluc.expensetracker.accounts.infrastructure.api.dto.AccountCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountCreateRequestMapper {

  AccountCreate toAccountCreate(AccountCreateRequest accountCreateRequest);

}
