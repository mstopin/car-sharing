package mstopin.carsharing.carsharing.renter.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RenterJpaRepository extends JpaRepository<RenterEntity, String> {
  Optional<RenterEntity> findById(UUID id);
}
