package mstopin.carsharing.common.infra;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.common.application.PasswordHashingService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class BCryptPasswordHashingService implements PasswordHashingService {
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public String hash(String password) {
    return bCryptPasswordEncoder.encode(password);
  }

  @Override
  public boolean verify(String password, String hash) {
    return bCryptPasswordEncoder.matches(password, hash);
  }
}
