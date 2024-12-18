package dev.hanluc.expensetracker.accounts.domain.repository;

import dev.hanluc.expensetracker.accounts.domain.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends CrudRepository<Account, UUID> {

  Optional<Account> findByEmail(String email);

}
