package mstopin.carsharing.useraccess.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import mstopin.carsharing.useraccess.user.User;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class JwtAuthTokenProvider implements AuthTokenProvider {
  private static final long TOKEN_ISSUED_FOR_SECONDS = 3600;

  private final Environment environment;

  @Override
  public AuthToken provideFor(User user) {
    Instant issuedAt = Instant.now();
    Instant expiresAt = issuedAt.plusSeconds(TOKEN_ISSUED_FOR_SECONDS);
    Key key = getKey();

    String token = Jwts.builder()
      .issuedAt(Date.from(issuedAt))
      .expiration(Date.from(expiresAt))
      .header()
        .add("userId", user.getId())
        .add("userType", user.getType())
        .and()
      .signWith(key)
      .compact();

    return new AuthToken(token);
  }

  private Key getKey() {
    String jwtKey = environment.getProperty("jwt.secret");

    if (jwtKey == null) {
      throw new RuntimeException("JWT secret key not specified");
    }

    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtKey));
  }
}
