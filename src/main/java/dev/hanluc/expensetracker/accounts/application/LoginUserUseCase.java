package dev.hanluc.expensetracker.accounts.application;

import dev.hanluc.expensetracker.common.domain.vo.Result;
import dev.hanluc.expensetracker.common.domain.vo.ResultError;
import dev.hanluc.expensetracker.accounts.domain.Account;
import dev.hanluc.expensetracker.accounts.domain.repository.AccountRepository;
import dev.hanluc.expensetracker.accounts.domain.vo.Email;
import org.springframework.stereotype.Service;

@Service
public class LoginUserUseCase {

  private final AccountRepository accountRepository;

  public LoginUserUseCase(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Result<Account> login(Email email) {
    return accountRepository.findByEmail(email.value())
      .map(Result::success)
      .orElse(Result.failure(ResultError.of("User not found")));
  }

}
