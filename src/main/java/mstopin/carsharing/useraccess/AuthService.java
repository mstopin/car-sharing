package mstopin.carsharing.useraccess;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
  private final AuthTokenProvider authTokenProvider;

  public AuthToken createAuthTokenFor(User user) {
    return authTokenProvider.provideFor(user);
  }
}
