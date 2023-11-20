package mstopin.carsharing.carsharing.car.domain;

import java.util.UUID;

public class ReservedCar extends Car {
  public ReservedCar(UUID carId, Fuel fuel) {
    super(carId, fuel);
  }
}
