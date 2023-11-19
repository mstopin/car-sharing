package mstopin.carsharing.carsharing.renter.domain.events;

import mstopin.carsharing.common.domain.DomainEvent;

import java.util.UUID;

public interface RenterEvent extends DomainEvent {
  default UUID getAggregateId() {
     return getRenterId();
  }

  UUID getRenterId();
}
