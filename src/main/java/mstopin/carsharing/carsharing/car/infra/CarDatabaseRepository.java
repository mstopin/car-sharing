package mstopin.carsharing.carsharing.car.infra;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface CarEntityRepository extends JpaRepository<CarEntity, UUID> {
  Optional<CarEntity> findByIdAndStatus(UUID carId, CarStatus status);
}

@RequiredArgsConstructor
public class CarDatabaseRepository implements CarRepository {
  private final CarEntityRepository carRepository;
  @Override
  public Optional<Car> findById(UUID id) {
    return carRepository
      .findById(id)
      .map(CarEntity::toDomain);
  }

  @Override
  public void save(Car car) {
    carRepository.save(CarEntity.fromDomain(car));
  }

  @Override
  public Optional<AvailableCar> findAvailableCarById(UUID carId) {
    return findById(carId)
      .filter((c) -> c instanceof AvailableCar)
      .map(AvailableCar.class::cast);
  }

  @Override
  public Optional<ReservedCar> findReservedCarById(UUID carId) {
    return findById(carId)
      .filter((c) -> c instanceof ReservedCar)
      .map(ReservedCar.class::cast);
  }

  @Override
  public Optional<RentedCar> findRentedCarById(UUID carId) {
    return findById(carId)
      .filter((c) -> c instanceof RentedCar)
      .map(RentedCar.class::cast);
  }
}
