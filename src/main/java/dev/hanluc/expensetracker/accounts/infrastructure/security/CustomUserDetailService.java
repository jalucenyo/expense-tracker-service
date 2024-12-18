package dev.hanluc.expensetracker.accounts.infrastructure.security;

import dev.hanluc.expensetracker.accounts.application.LoginUserUseCase;
import dev.hanluc.expensetracker.accounts.domain.vo.Email;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

  private final LoginUserUseCase loginUserUseCase;

  public CustomUserDetailService(LoginUserUseCase loginUserUseCase) {
    this.loginUserUseCase = loginUserUseCase;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return loginUserUseCase.login(new Email(username))
      .fold(error -> {
        throw new UsernameNotFoundException("User not found");
      }, account -> new AccountDetails(account.getId(), account.getEmail(), account.getPassword()));
  }

}
