package mstopin.carsharing.carsharing.renter.application;

import mstopin.carsharing.carsharing.car.domain.RentedCar;

import java.util.Optional;
import java.util.UUID;

public interface FindRentedCar {
  Optional<RentedCar> findRentedCarById(UUID carId);
}
