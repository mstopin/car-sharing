package mstopin.carsharing.carsharing.car.domain;

import java.util.Optional;
import java.util.UUID;

public interface CarRepository {
  Optional<Car> findById(UUID id);

  void save(Car car);
}
