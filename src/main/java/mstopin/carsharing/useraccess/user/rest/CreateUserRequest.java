package mstopin.carsharing.useraccess.user.rest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class CreateUserRequest {
  @Email
  @NotBlank
  String email;

  @Size(min = 8)
  String password;
}
