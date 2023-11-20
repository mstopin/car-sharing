package mstopin.carsharing.carsharing.car.infra;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.AvailableCar;
import mstopin.carsharing.carsharing.car.domain.RentedCar;
import mstopin.carsharing.carsharing.car.domain.ReservedCar;
import mstopin.carsharing.carsharing.renter.application.FindAvailableCar;
import mstopin.carsharing.carsharing.renter.application.FindRentedCar;
import mstopin.carsharing.carsharing.renter.application.FindReservedCar;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class CarDatabaseRepository implements FindAvailableCar, FindReservedCar, FindRentedCar {
  private final CarEntityRepository carRepository;
  @Override
  public Optional<AvailableCar> findAvailableCarById(UUID carId) {
    return carRepository
      .findByIdAndStatus(carId, CarStatus.AVAILABLE)
      .map(CarEntity::toAvailableCar);
  }

  @Override
  public Optional<ReservedCar> findReservedCarById(UUID carId) {
    return carRepository
      .findByIdAndStatus(carId, CarStatus.RESERVED)
      .map(CarEntity::toReservedCar);
  }

  @Override
  public Optional<RentedCar> findRentedCarById(UUID carId) {
    return carRepository
      .findByIdAndStatus(carId, CarStatus.RENTED)
      .map(CarEntity::toRentedCar);
  }
}
