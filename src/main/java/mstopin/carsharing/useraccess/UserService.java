package mstopin.carsharing.useraccess;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.common.application.PasswordHashingService;
import mstopin.carsharing.common.domain.BusinessRuleBrokenException;
import mstopin.carsharing.common.domain.DomainEvents;
import mstopin.carsharing.useraccess.dto.CreateUserDto;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordHashingService passwordHashingService;
  private final DomainEvents domainEvents;

  public Optional<User> findUserByEmailAndPassword(String email, String password) {
    return userRepository
      .findByEmail(email)
      .filter((u) -> passwordHashingService.verify(password, u.getPassword()));
  }

  public User createUser(CreateUserDto createUserDto) {
    Optional<User> exactEmailUser = userRepository.findByEmail(
      createUserDto.getEmail()
    );
    if (exactEmailUser.isPresent()) {
      throw new BusinessRuleBrokenException("User with exact email already exists");
    }

    String hashedPassword = passwordHashingService.hash(createUserDto.getPassword());
    User user = new User(
      null,
      createUserDto.getEmail(),
      hashedPassword,
      UserType.USER,
      Instant.now()
    );

    userRepository.save(user);
    domainEvents.publish(new UserCreatedEvent(user.getId(), user.getType()));

    return user;
  }
}
