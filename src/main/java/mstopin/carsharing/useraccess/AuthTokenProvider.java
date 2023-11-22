package mstopin.carsharing.useraccess;

public interface AuthTokenProvider {
  AuthToken provideFor(User user);
}

