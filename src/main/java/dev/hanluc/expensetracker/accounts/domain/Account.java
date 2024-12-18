package dev.hanluc.expensetracker.accounts.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;
import java.util.UUID;

@Table(name = "accounts")
public class Account {

  @Id
  private UUID id;

  @Version
  private Integer version;

  private String email;
  private String password;

  public Account(UUID id,  String email, String password) {
    this.id = id;
    this.email = email;
    this.password = password;
  }

  public UUID getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Account account)) return false;
    return Objects.equals(id, account.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", version=" + version +
      ", email='" + email + '\'' +
      '}';
  }

}
