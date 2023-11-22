package mstopin.carsharing.useraccess.auth;

import mstopin.carsharing.useraccess.user.User;

public interface AuthTokenProvider {
  AuthToken provideFor(User user);
}

