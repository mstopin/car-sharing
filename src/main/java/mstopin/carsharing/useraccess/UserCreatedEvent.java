package mstopin.carsharing.useraccess;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.common.domain.DomainEvent;

import java.util.UUID;

@RequiredArgsConstructor
public class UserCreatedEvent implements DomainEvent {
  private final UUID userId;
  private final UserType userType;

  @Override
  public UUID getAggregateId() {
    return userId;
  }

  public UserType getUserType() {
    return userType;
  }
}
