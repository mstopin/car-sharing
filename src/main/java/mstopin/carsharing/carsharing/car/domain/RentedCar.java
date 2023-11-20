package mstopin.carsharing.carsharing.car.domain;

import java.util.UUID;

public class RentedCar extends Car {
  public RentedCar(UUID carId, Fuel fuel) {
    super(carId, fuel);
  }
}
