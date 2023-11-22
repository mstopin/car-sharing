package mstopin.carsharing.useraccess.auth;

import mstopin.carsharing.useraccess.user.User;
import org.springframework.security.core.Authentication;

public interface AuthTokenProvider {
  String provideFor(User user);

  Authentication extractUser(String token);
}

