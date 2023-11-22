package mstopin.carsharing.useraccess.rest.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class LoginUserRequest {
  @Email
  String email;

  @NotBlank
  String password;
}
