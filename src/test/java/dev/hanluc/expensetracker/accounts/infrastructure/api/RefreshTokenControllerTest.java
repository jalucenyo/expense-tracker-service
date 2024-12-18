package dev.hanluc.expensetracker.accounts.infrastructure.api;

import dev.hanluc.expensetracker.TestContainersConfiguration;
import dev.hanluc.expensetracker.accounts.infrastructure.api.dto.RefreshTokenResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.BDDAssertions.then;

@ApplicationModuleTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestContainersConfiguration.class)
@Sql(scripts = "classpath:db/accounts/data-init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "classpath:db/accounts/data-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class RefreshTokenControllerTest {

  @Autowired
  TestRestTemplate restTemplate;

  @Autowired
  JwtEncoder jwtEncoder;

  @Test
  void should_return_unauthorized_when_no_credentials() {
    ResponseEntity<Void> response = restTemplate.postForEntity("/accounts/refresh-token", new HttpEntity<>(null), Void.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
  }

  @Test
  void should_return_unauthorized_when_invalid_credentials() {
    final var invalidToken = "invalid-token";
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + invalidToken);

    ResponseEntity<Void> response = restTemplate.postForEntity("/accounts/refresh-token", new HttpEntity<>(null, headers), Void.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
  }

  @Test
  void should_refresh_token_when_valid_credentials() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + generateMockToken());
    HttpEntity<Void> entity = new HttpEntity<>(null, headers);

    ResponseEntity<RefreshTokenResponse> response = restTemplate.postForEntity("/accounts/refresh-token", entity, RefreshTokenResponse.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  private String generateMockToken() {
    JwtClaimsSet claims = JwtClaimsSet.builder()
      .subject("test")
      .issuer("test")
      .issuedAt(LocalDateTime.now().toInstant(ZoneOffset.UTC))
      .expiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC))
      .claim("scope", "")
      .build();
    final var headers = JwsHeader.with(MacAlgorithm.HS256).build();

    return jwtEncoder.encode(JwtEncoderParameters.from(headers, claims)).getTokenValue();
  }

}