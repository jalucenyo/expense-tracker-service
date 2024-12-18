package dev.hanluc.expensetracker.accounts.infrastructure.api;


import dev.hanluc.expensetracker.accounts.infrastructure.api.controller.AccountsApi;
import dev.hanluc.expensetracker.accounts.infrastructure.api.dto.AccountCreateRequest;
import dev.hanluc.expensetracker.accounts.infrastructure.api.dto.RefreshTokenResponse;
import dev.hanluc.expensetracker.accounts.infrastructure.api.dto.SignInRequest;
import dev.hanluc.expensetracker.accounts.infrastructure.api.dto.SignInResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AccountsController implements AccountsApi {

  private final PostAccountController postAccountController;
  private final SignInController signInController;
  private final RefreshTokenController refreshTokenController;

  public AccountsController(
    PostAccountController postAccountController,
    SignInController signInController, RefreshTokenController refreshTokenController
  ) {
    this.postAccountController = postAccountController;
    this.signInController = signInController;
    this.refreshTokenController = refreshTokenController;
  }

  @Override
  public ResponseEntity<Void> post(AccountCreateRequest accountCreateRequest) {
    return postAccountController.post(accountCreateRequest);
  }

  @Override
  public ResponseEntity<RefreshTokenResponse> refreshToken() {
    final var authentication = SecurityContextHolder.getContext().getAuthentication();
    return refreshTokenController.refreshToken(authentication);
  }

  @Override
  public ResponseEntity<SignInResponse> signIn(SignInRequest signInRequest) {
    return signInController.signIn(signInRequest);
  }

}
