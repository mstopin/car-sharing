package mstopin.carsharing.useraccess.user.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mstopin.carsharing.useraccess.user.CreateUserDto;
import mstopin.carsharing.useraccess.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  @PostMapping("/create")
  UserCreatedResponse createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
    return UserCreatedResponse.from(
      userService.createUser(new CreateUserDto(
        createUserRequest.getEmail(),
        createUserRequest.getPassword()
      ))
    );
  }
}
