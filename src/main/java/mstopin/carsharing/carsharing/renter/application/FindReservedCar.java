package mstopin.carsharing.carsharing.renter.application;

import mstopin.carsharing.carsharing.car.domain.ReservedCar;

import java.util.Optional;
import java.util.UUID;

public interface FindReservedCar {
  Optional<ReservedCar> findReservedCarById(UUID carId);
}
