package mstopin.carsharing.carsharing.car.infra;

import jakarta.persistence.*;
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
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Enumerated(EnumType.STRING)
  private CarStatus status;

  private double fuel;

  Car toDomain() {
    if (status == CarStatus.AVAILABLE) {
      return new AvailableCar(id, new Fuel(fuel));
    }

    if (status == CarStatus.RESERVED) {
      return new ReservedCar(id, new Fuel(fuel));
    }

    if (status == CarStatus.RENTED) {
      return new RentedCar(id, new Fuel(fuel));
    }

    throw new IllegalStateException("Unexpected car status");
  }

  static CarEntity fromDomain(Car car) {
    if (car instanceof AvailableCar) {
      return new CarEntity(car.getAggregateId(), CarStatus.AVAILABLE, car.getFuel().asDouble());
    }

    if (car instanceof ReservedCar) {
      return new CarEntity(car.getAggregateId(), CarStatus.RESERVED, car.getFuel().asDouble());
    }

    if (car instanceof RentedCar) {
      return new CarEntity(car.getAggregateId(), CarStatus.RENTED, car.getFuel().asDouble());
    }

    throw new IllegalArgumentException(("Unexpected car status"));
  }
}
