package mstopin.carsharing.useraccess.rest;

import lombok.Value;
import mstopin.carsharing.useraccess.User;

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
