package mstopin.carsharing.carsharing.renter.domain;

import mstopin.carsharing.carsharing.renter.domain.events.RenterEvent;

import java.util.Optional;
import java.util.UUID;

public interface RenterRepository {
  Optional<Renter> find(UUID renterId);

  void save(Renter renter);

  void publishDomainEvent(RenterEvent renterEvent);
}
