package mstopin.carsharing.useraccess;

import mstopin.carsharing.common.application.PasswordHashingService;
import mstopin.carsharing.common.domain.BusinessRuleBrokenException;
import mstopin.carsharing.common.domain.DomainEvents;
import mstopin.carsharing.useraccess.dto.CreateUserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordHashingService passwordHashingService;

  @Mock
  private DomainEvents domainEvents;

  @InjectMocks
  private UserService userService;

  private static class UserFixture {
    static User USER = new User(UUID.randomUUID(), "email", "password", UserType.USER, Instant.now());
  }

  @Test
  void shouldCreateUserWithHashedPassword() {
    when(passwordHashingService.hash(any(String.class))).thenReturn("hashedPassword");

    User user = userService.createUser(new CreateUserDto("email", "password"));

    assertThat(user.getEmail()).isEqualTo("email");
    assertThat(user.getPassword()).isEqualTo("hashedPassword");
    verify(domainEvents).publish(any(UserCreatedEvent.class));
  }

  @Test
  void shouldThrowIfUserWithExactEmailAlreadyExists() {
    when(userRepository.findByEmail("email")).thenReturn(Optional.of(
      UserFixture.USER
    ));

    assertThrows(BusinessRuleBrokenException.class, () -> {
      User user = userService.createUser(new CreateUserDto("email", "password"));
    });
    verify(domainEvents, never()).publish(any(UserCreatedEvent.class));
  }
}