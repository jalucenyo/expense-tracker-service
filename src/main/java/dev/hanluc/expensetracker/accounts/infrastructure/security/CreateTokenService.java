package dev.hanluc.expensetracker.accounts.infrastructure.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class CreateTokenService {

  @Value("${api.security.token.expiration.hours}")
  private Integer tokenExpirationHours;

  @Value("${spring.application.name}")
  private String issuer;

  private final JwtEncoder jwtEncoder;

  public CreateTokenService(JwtEncoder jwtEncoder) {
    this.jwtEncoder = jwtEncoder;
  }

  public String generateToken(Authentication authentication) {

    final var subject =  authentication.getPrincipal() instanceof AccountDetails customUser
      ? customUser.getId().toString()
      : authentication.getName();

    try {
      JwtClaimsSet claims = JwtClaimsSet.builder()
        .subject(subject)
        .issuer(issuer)
        .issuedAt(getCurrentTime())
        .expiresAt(getExpirationTime())
        .claim("scope", "")
        .build();

      final var headers = JwsHeader.with(MacAlgorithm.HS256).build();
      return jwtEncoder.encode(JwtEncoderParameters.from(headers, claims)).getTokenValue();

    } catch (Exception exception) {
      throw new RuntimeException("Error creating token", exception);
    }
  }

  private Instant getCurrentTime() {
    return LocalDateTime.now().toInstant(ZoneOffset.UTC);
  }

  private Instant getExpirationTime() {
    return LocalDateTime.now().plusHours(tokenExpirationHours).toInstant(ZoneOffset.UTC);
  }

}
