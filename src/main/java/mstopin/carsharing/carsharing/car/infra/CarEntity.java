package mstopin.carsharing.carsharing.car.infra;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.AvailableCar;
import mstopin.carsharing.carsharing.car.domain.Fuel;
import mstopin.carsharing.carsharing.car.domain.RentedCar;
import mstopin.carsharing.carsharing.car.domain.ReservedCar;

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

  AvailableCar toAvailableCar() {
    return new AvailableCar(id, new Fuel(fuel));
  }

  ReservedCar toReservedCar() {
    return new ReservedCar(id, new Fuel(fuel));
  }

  RentedCar toRentedCar() {
    return new RentedCar(id, new Fuel(fuel));
  }
}
