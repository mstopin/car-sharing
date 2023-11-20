package mstopin.carsharing.carsharing.renter.application;

import mstopin.carsharing.carsharing.car.domain.AvailableCar;

import java.util.Optional;
import java.util.UUID;

public interface FindAvailableCar {
  Optional<AvailableCar> findAvailableCarById(UUID carId);
}
