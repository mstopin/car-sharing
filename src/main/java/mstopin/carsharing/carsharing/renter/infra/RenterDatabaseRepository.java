package mstopin.carsharing.carsharing.renter.infra;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.renter.domain.Renter;
import mstopin.carsharing.carsharing.renter.domain.RenterRepository;
import mstopin.carsharing.carsharing.renter.domain.events.RenterEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface RenterEntityRepository extends JpaRepository<RenterEntity, UUID> {}

@RequiredArgsConstructor
public class RenterDatabaseRepository implements RenterRepository {
  private final RenterEntityRepository renterEntityRepository;

  @Override
  public Optional<Renter> find(UUID renterId) {
    return renterEntityRepository
      .findById(renterId)
      .map(RenterEntity::toDomain);
  }

  @Override
  public void publishDomainEvent(RenterEvent renterEvent) {

  }
}
