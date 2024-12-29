package dev.hanluc.expensetracker.accounts.application;

import dev.hanluc.expensetracker.accounts.domain.Account;
import dev.hanluc.expensetracker.accounts.domain.repository.AccountRepository;
import dev.hanluc.expensetracker.common.asserts.ResultErrorAssert;
import dev.hanluc.expensetracker.common.domain.vo.Result;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateAccountUseCaseTest {

  private final AccountRepository accountRepository = mock(AccountRepository.class);
  private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
  private final CreateAccountUseCase createAccountUseCase = new CreateAccountUseCase(accountRepository, passwordEncoder);

  @Test
  void given_email_exist_then_return_error() {
    final var accountCreate = new CreateAccountUseCase.AccountCreate("test@test.local", "12345678");
    final var accountExist = new Account(UUID.randomUUID(), "test@test.local", "12345678");
    when(accountRepository.findByEmail(accountCreate.email())).thenReturn(Optional.of(accountExist));

    Result<Account> result = createAccountUseCase.create(accountCreate);

    then(result.isFailure()).isTrue();
    ResultErrorAssert.then(result.errors()).hasMessage("Email already exists");
  }

  @Test
  void given_email_not_exist_then_create_account() {
    final var accountCreate = new CreateAccountUseCase.AccountCreate("test@test.local", "12345678");
    when(passwordEncoder.encode(accountCreate.password())).thenReturn(accountCreate.password());
    when(accountRepository.findByEmail(accountCreate.email())).thenReturn(Optional.empty());
    when(accountRepository.save(any())).then(invocation -> invocation.getArgument(0));

    Result<Account> result = createAccountUseCase.create(accountCreate);

    then(result.isSuccess()).isTrue();
    then(result.value().getEmail()).isEqualTo(accountCreate.email());
    then(result.value().getPassword()).isEqualTo(accountCreate.password());
  }

}