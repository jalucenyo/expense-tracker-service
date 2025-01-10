package dev.hanluc.expensetracker.accounts.application;

import dev.hanluc.expensetracker.common.domain.vo.Result;
import dev.hanluc.expensetracker.common.domain.vo.ResultError;
import dev.hanluc.expensetracker.accounts.domain.Account;
import dev.hanluc.expensetracker.accounts.domain.repository.AccountRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Service
@Validated
public class CreateAccountUseCase {

  private final AccountRepository accountRepository;
  private final PasswordEncoder passwordEncoder;

  public CreateAccountUseCase(
    AccountRepository accountRepository,
    PasswordEncoder passwordEncoder
  ) {
    this.accountRepository = accountRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public Result<Account> create(@Valid AccountCreate accountCreate) {

    if(isEmailAlreadyExists(accountCreate.email())) {
      return Result.failure(ResultError.of("Email already exists"));
    }

    final var accountCreated = accountRepository.save(accountCreate.toAccount(passwordEncoder));
    return Result.success(accountCreated);
  }

  private boolean isEmailAlreadyExists(String email) {
    return accountRepository.findByEmail(email).isPresent();
  }

  public record AccountCreate(
    @Email
    String email,
    @NotEmpty
    String password
  ) {
    Account toAccount(PasswordEncoder encoder) {
      return new Account(UUID.randomUUID(), email(), encoder.encode(password()));
    }
  }

}
