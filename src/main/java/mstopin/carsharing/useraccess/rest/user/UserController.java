package mstopin.carsharing.useraccess.rest.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mstopin.carsharing.useraccess.UserService;
import mstopin.carsharing.useraccess.dto.CreateUserDto;
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
