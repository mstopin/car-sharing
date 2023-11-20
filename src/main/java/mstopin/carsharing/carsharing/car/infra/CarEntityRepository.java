package mstopin.carsharing.carsharing.car.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CarEntityRepository extends JpaRepository<CarEntity, String> {
  Optional<CarEntity> findByIdAndStatus(UUID carId, CarStatus status);
}
