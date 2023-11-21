package mstopin.carsharing.common.application;

public interface PasswordHashingService {
  String hash(String password);
  boolean verify(String password, String hash);
}
