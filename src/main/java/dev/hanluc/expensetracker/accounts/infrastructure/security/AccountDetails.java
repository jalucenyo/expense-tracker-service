package dev.hanluc.expensetracker.accounts.infrastructure.security;

import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AccountDetails extends User {

  private final UUID id;

  public AccountDetails(UUID id, String email, String password) {
    super(email, password, List.of());
    this.id = id;
  }

  public UUID getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AccountDetails that)) return false;
    if (!super.equals(o)) return false;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id);
  }

}
