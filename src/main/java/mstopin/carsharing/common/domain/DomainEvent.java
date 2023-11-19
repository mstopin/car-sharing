package mstopin.carsharing.common.domain;

import java.util.UUID;

public interface DomainEvent {
  default UUID getEventId() {
    return UUID.randomUUID();
  }
  UUID getAggregateId();
}
