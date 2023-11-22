package mstopin.carsharing.useraccess.auth;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.useraccess.user.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
  private final AuthTokenProvider authTokenProvider;

  public AuthToken createAuthTokenFor(User user) {
    return authTokenProvider.provideFor(user);
  }
}
