package mstopin.carsharing.carsharing.car.infra;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.AvailableCar;
import mstopin.carsharing.carsharing.car.domain.CarRepository;
import mstopin.carsharing.carsharing.car.domain.RentedCar;
import mstopin.carsharing.carsharing.car.domain.ReservedCar;
import mstopin.carsharing.carsharing.renter.application.FindAvailableCar;
import mstopin.carsharing.carsharing.renter.application.FindRentedCar;
import mstopin.carsharing.carsharing.renter.application.FindReservedCar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface CarEntityRepository extends JpaRepository<CarEntity, String> {
  Optional<CarEntity> findByIdAndStatus(UUID carId, CarStatus status);
}


@RequiredArgsConstructor
public class CarDatabaseRepository implements CarRepository, FindAvailableCar, FindReservedCar, FindRentedCar {
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
