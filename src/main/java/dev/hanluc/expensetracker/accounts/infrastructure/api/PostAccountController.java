package dev.hanluc.expensetracker.accounts.infrastructure.api;

import dev.hanluc.expensetracker.accounts.application.CreateAccountUseCase;
import dev.hanluc.expensetracker.accounts.domain.exception.AccountException;
import dev.hanluc.expensetracker.accounts.infrastructure.api.dto.AccountCreateRequest;
import dev.hanluc.expensetracker.accounts.infrastructure.api.mappers.AccountCreateRequestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class PostAccountController {

  private final CreateAccountUseCase createAccountUseCase;
  private final AccountCreateRequestMapper mapper;

  public PostAccountController(CreateAccountUseCase createAccountUseCase, AccountCreateRequestMapper mapper) {
    this.createAccountUseCase = createAccountUseCase;
    this.mapper = mapper;
  }

  public ResponseEntity<Void> post (AccountCreateRequest request) {
    return createAccountUseCase.create(mapper.toAccountCreate(request))
      .fold(
        errors -> { throw new AccountException(errors); },
        account -> ResponseEntity.created(buildLocation(account.getId().toString())).build()
      );
  }

  private URI buildLocation(String id) {
    return ServletUriComponentsBuilder.fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(id)
      .toUri();
  }

}
