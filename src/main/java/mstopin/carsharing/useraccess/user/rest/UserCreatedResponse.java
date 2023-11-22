package mstopin.carsharing.useraccess.user.rest;

import lombok.Value;
import mstopin.carsharing.useraccess.user.User;

import java.time.Instant;
import java.util.UUID;

@Value
public class UserCreatedResponse {
  UUID id;
  String email;
  Instant createdAt;

  public static UserCreatedResponse from(User u) {
    return new UserCreatedResponse(
      u.getId(),
      u.getEmail(),
      u.getCreatedAt()
    );
  }
}
