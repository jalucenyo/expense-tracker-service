package dev.hanluc.expensetracker.accounts.seeder;

import dev.hanluc.expensetracker.accounts.domain.Account;
import dev.hanluc.expensetracker.accounts.domain.repository.AccountRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@Configuration
public class UserSeeder implements ApplicationRunner {

  private final AccountRepository accountRepository;
  private final PasswordEncoder passwordEncoder;

  public UserSeeder(
    AccountRepository accountRepository,
    PasswordEncoder passwordEncoder) {
    this.accountRepository = accountRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void run(ApplicationArguments args) {
    if(accountRepository.count() > 0) {
      return;
    }
    final var users = List.of(
      new Account(UUID.randomUUID(), "user1@test.local", passwordEncoder.encode("1234")),
      new Account(UUID.randomUUID(), "user2@test.local", passwordEncoder.encode("1234"))
    );

    accountRepository.saveAll(users);

  }

}
