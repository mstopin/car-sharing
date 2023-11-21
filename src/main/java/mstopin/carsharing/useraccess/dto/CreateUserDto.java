package mstopin.carsharing.useraccess.dto;

import lombok.Value;

@Value
public class CreateUserDto {
  String email;
  String password;
}

