package mstopin.carsharing.carsharing.car.domain;

import mstopin.carsharing.carsharing.renter.domain.events.CarReservedEvent;

import java.util.UUID;

public class AvailableCar extends Car {
  public AvailableCar(UUID carId, Fuel fuel) {
    super(carId, fuel);
  }

  public ReservedCar handleCarReserved(CarReservedEvent event) {
    return new ReservedCar(carId, fuel);
  }
}
