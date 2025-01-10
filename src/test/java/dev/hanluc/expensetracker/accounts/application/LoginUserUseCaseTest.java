package dev.hanluc.expensetracker.accounts.application;

import dev.hanluc.expensetracker.accounts.domain.Account;
import dev.hanluc.expensetracker.accounts.domain.repository.AccountRepository;
import dev.hanluc.expensetracker.accounts.domain.vo.Email;
import dev.hanluc.expensetracker.common.asserts.ResultErrorAssert;
import dev.hanluc.expensetracker.common.domain.vo.Result;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginUserUseCaseTest {

  private final AccountRepository accountRepository = mock(AccountRepository.class);
  private final LoginUserUseCase loginUserUseCase = new LoginUserUseCase(accountRepository);

  @Test
  void given_email_not_exist_then_return_error() {
    final var notExistEmail = "notexist@test.local";
    when(accountRepository.findByEmail(notExistEmail)).thenReturn(Optional.empty());

    Result<Account> result = loginUserUseCase.login(new Email(notExistEmail));

    then(result.isFailure()).isTrue();
    ResultErrorAssert.then(result.errors()).hasMessage("User not found");
  }

  @Test
  void given_email_exist_then_return_account() {
    final var existEmail = "test@test.local";
    final var existAccount = new Account(UUID.randomUUID(), existEmail, "12345678");
    when(accountRepository.findByEmail(existEmail)).thenReturn(Optional.of(existAccount));

    Result<Account> result = loginUserUseCase.login(new Email(existEmail));

    then(result.isSuccess()).isTrue();
    then(result.value().getEmail()).isEqualTo(existEmail);
  }

}