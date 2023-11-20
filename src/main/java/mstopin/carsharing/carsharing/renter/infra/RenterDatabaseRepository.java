package mstopin.carsharing.carsharing.renter.infra;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.renter.domain.Renter;
import mstopin.carsharing.carsharing.renter.domain.RenterRepository;
import mstopin.carsharing.carsharing.renter.domain.events.RenterEvent;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class RenterDatabaseRepository implements RenterRepository {
  private final RenterJpaRepository renterRepository;
  @Override
  public Optional<Renter> find(UUID renterId) {
    return renterRepository
      .findById(renterId)
      .map(RenterEntity::toDomain);
  }

  @Override
  public void publishDomainEvent(RenterEvent renterEvent) {}
}
