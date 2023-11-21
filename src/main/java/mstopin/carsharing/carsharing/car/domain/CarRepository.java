package mstopin.carsharing.carsharing.car.domain;

import java.util.Optional;
import java.util.UUID;

public interface CarRepository {
  Optional<Car> findById(UUID id);
  Optional<AvailableCar> findAvailableCarById(UUID carId);
  Optional<RentedCar> findRentedCarById(UUID carId);
  Optional<ReservedCar> findReservedCarById(UUID carId);
  void save(Car car);
}
