package mstopin.carsharing.carsharing.car.domain;

import mstopin.carsharing.carsharing.renter.domain.events.CarRentalFinishedEvent;

import java.util.UUID;

public class RentedCar extends Car {
  public RentedCar(UUID carId, Fuel fuel) {
    super(carId, fuel);
  }

  public AvailableCar handleCarRentalFinished(CarRentalFinishedEvent event) {
    return new AvailableCar(carId, fuel);
  }
}
