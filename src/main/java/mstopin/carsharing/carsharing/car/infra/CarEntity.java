package mstopin.carsharing.carsharing.car.infra;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.*;

import java.util.UUID;

enum CarStatus {
  AVAILABLE,
  RESERVED,
  RENTED
}

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CarEntity {
  @Id
  private UUID id;

  @Enumerated(EnumType.STRING)
  private CarStatus status;

  private int fuelPercent;

  Car toDomain() {
    if (status == CarStatus.AVAILABLE) {
      return new AvailableCar(id, Fuel.fromPercent(fuelPercent));
    }

    if (status == CarStatus.RESERVED) {
      return new ReservedCar(id, Fuel.fromPercent(fuelPercent));
    }

    if (status == CarStatus.RENTED) {
      return new RentedCar(id, Fuel.fromPercent(fuelPercent));
    }

    throw new IllegalStateException("Unexpected car status");
  }

  static CarEntity fromDomain(Car car) {
    if (car instanceof AvailableCar) {
      return new CarEntity(car.getAggregateId(), CarStatus.AVAILABLE, car.getFuel().asPercent());
    }

    if (car instanceof ReservedCar) {
      return new CarEntity(car.getAggregateId(), CarStatus.RESERVED, car.getFuel().asPercent());
    }

    if (car instanceof RentedCar) {
      return new CarEntity(car.getAggregateId(), CarStatus.RENTED, car.getFuel().asPercent());
    }

    throw new IllegalArgumentException(("Unexpected car status"));
  }
}
