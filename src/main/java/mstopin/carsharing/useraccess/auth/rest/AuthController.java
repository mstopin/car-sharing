package mstopin.carsharing.useraccess.auth.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mstopin.carsharing.useraccess.auth.AuthService;
import mstopin.carsharing.useraccess.auth.AuthToken;
import mstopin.carsharing.useraccess.user.User;
import mstopin.carsharing.useraccess.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
  private final UserService userService;
  private final AuthService authService;

  @PostMapping("/login")
  UserTokenCreatedResponse login(@Valid @RequestBody LoginUserRequest loginUserRequest) {
    Optional<User> user = userService.findUserByEmailAndPassword(
      loginUserRequest.getEmail(),
      loginUserRequest.getPassword()
    );

    if (user.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    AuthToken authToken = authService.createAuthTokenFor(user.get());

    return UserTokenCreatedResponse.of(authToken.getToken());
  }
}
