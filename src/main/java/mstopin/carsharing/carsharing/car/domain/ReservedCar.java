package mstopin.carsharing.carsharing.car.domain;

import mstopin.carsharing.carsharing.renter.domain.events.CarRentedEvent;
import mstopin.carsharing.carsharing.renter.domain.events.CarReservationCanceledEvent;

import java.util.UUID;

public class ReservedCar extends Car {
  public ReservedCar(UUID carId, Fuel fuel) {
    super(carId, fuel);
  }

  public RentedCar handleCarRented(CarRentedEvent event) {
    return new RentedCar(carId, fuel);
  }

  public AvailableCar handleCarReservationCancelled(CarReservationCanceledEvent event) {
    return new AvailableCar(carId, fuel);
  }
}
