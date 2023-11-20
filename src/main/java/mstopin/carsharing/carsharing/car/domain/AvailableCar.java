package mstopin.carsharing.carsharing.car.domain;

import java.util.UUID;

public class AvailableCar extends Car {
  public AvailableCar(UUID carId, Fuel fuel) {
    super(carId, fuel);
  }
}
