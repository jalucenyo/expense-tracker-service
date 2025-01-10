package dev.hanluc.expensetracker.accounts.infrastructure.api;

import dev.hanluc.expensetracker.accounts.infrastructure.api.dto.SignInRequest;
import dev.hanluc.expensetracker.accounts.infrastructure.api.dto.SignInResponse;
import dev.hanluc.expensetracker.accounts.infrastructure.security.CreateTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class SignInController {

  private final CreateTokenService createTokenService;
  private final AuthenticationManager authenticationManager;

  public SignInController(
    CreateTokenService createTokenService,
    AuthenticationManager authenticationManager) {
    this.createTokenService = createTokenService;
    this.authenticationManager = authenticationManager;
  }

  ResponseEntity<SignInResponse> signIn(SignInRequest request) {
    final Authentication authentication = authenticationManager
      .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

    String token = createTokenService.generateToken(authentication);

    return ResponseEntity.ok(new SignInResponse().token(token));
  }

}
