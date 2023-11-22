package mstopin.carsharing.useraccess.user;

import lombok.Value;

@Value
public class CreateUserDto {
  String email;
  String password;
}

