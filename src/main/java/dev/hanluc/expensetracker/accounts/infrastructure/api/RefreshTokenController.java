package dev.hanluc.expensetracker.accounts.infrastructure.api;

import dev.hanluc.expensetracker.accounts.infrastructure.api.dto.RefreshTokenResponse;
import dev.hanluc.expensetracker.accounts.infrastructure.security.CreateTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenController {

  private final CreateTokenService createTokenService;

  public RefreshTokenController(CreateTokenService createTokenService) {
    this.createTokenService = createTokenService;
  }

  ResponseEntity<RefreshTokenResponse> refreshToken(Authentication authentication) {
    String token = createTokenService.generateToken(authentication);
    return ResponseEntity.ok(new RefreshTokenResponse().token(token));
  }

}
