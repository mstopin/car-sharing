package mstopin.carsharing.useraccess;

import mstopin.carsharing.useraccess.auth.AuthService;
import mstopin.carsharing.useraccess.auth.AuthTokenProvider;
import mstopin.carsharing.useraccess.user.User;
import mstopin.carsharing.useraccess.user.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AuthServiceTest {
  private static class NoopAuthTokenProvider implements AuthTokenProvider {
    @Override
    public String provideFor(User user) {
      return "token";
    }

    @Override
    public Authentication extractUser(String token) {
      return null;
    }
  }

  @Test
  void shouldCreateTokenForUser() {
    User user = aUser();
    AuthService authService = new AuthService(new NoopAuthTokenProvider());

    assertThat(authService.createAuthTokenFor(user)).isEqualTo("token");
  }

  private User aUser() {
    return new User(UUID.randomUUID(), "email", "password", UserType.USER, Instant.now());
  }
}