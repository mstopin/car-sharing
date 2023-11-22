package mstopin.carsharing.useraccess.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import mstopin.carsharing.useraccess.user.User;
import mstopin.carsharing.useraccess.user.UserType;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class JwtAuthTokenProvider implements AuthTokenProvider {
  private static final long TOKEN_ISSUED_FOR_SECONDS = 3600;

  private final Environment environment;

  @Override
  public String provideFor(User user) {
    Instant issuedAt = Instant.now();
    Instant expiresAt = issuedAt.plusSeconds(TOKEN_ISSUED_FOR_SECONDS);
    Key key = getKey();

    return Jwts.builder()
      .issuedAt(Date.from(issuedAt))
      .expiration(Date.from(expiresAt))
      .claims(Map.of(
        "id", user.getId(),
        "type", user.getType()
      ))
      .signWith(key)
      .compact();
  }

  @Override
  public Authentication extractUser(String token) {
    Jws<Claims> jws = Jwts.parser()
      .verifyWith(getKey())
      .build()
      .parseSignedClaims(token);

    return new UsernamePasswordAuthenticationToken(
      jws.getPayload().get("id", String.class),
      null,
      List.of(
        UserType.valueOf(jws.getPayload().get("type", String.class))
      )
    );
  }

  private SecretKey getKey() {
    String jwtKey = environment.getProperty("jwt.secret");

    if (jwtKey == null) {
      throw new RuntimeException("JWT secret key not specified");
    }

    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtKey));
  }
}
