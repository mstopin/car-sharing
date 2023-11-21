package mstopin.carsharing.carsharing.car.application;

import lombok.AllArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.CarRepository;
import mstopin.carsharing.carsharing.renter.domain.events.CarRentalFinishedEvent;
import mstopin.carsharing.carsharing.renter.domain.events.CarRentedEvent;
import mstopin.carsharing.carsharing.renter.domain.events.CarReservationCanceledEvent;
import mstopin.carsharing.carsharing.renter.domain.events.CarReservedEvent;
import org.springframework.context.event.EventListener;

@AllArgsConstructor
public class RenterEventsHandler {
  private final CarRepository carRepository;

  @EventListener
  void handleCarReserved(CarReservedEvent event) {
    carRepository
      .findAvailableCarById(event.getCarId())
      .map((c) -> c.handleCarReserved(event))
      .ifPresent(carRepository::save);
  }

  @EventListener
  void handleCarReservationCancelled(CarReservationCanceledEvent event) {
    carRepository
      .findReservedCarById(event.getCarId())
      .map((c) -> c.handleCarReservationCancelled(event))
      .ifPresent(carRepository::save);
  }

  @EventListener
  void handleCarRented(CarRentedEvent event) {
    carRepository
      .findReservedCarById(event.getCarId())
      .map((c) -> c.handleCarRented(event))
      .ifPresent(carRepository::save);
  }

  @EventListener
  void handleCarRentalFinished(CarRentalFinishedEvent event) {
    carRepository
      .findRentedCarById(event.getCarId())
      .map((c) -> c.handleCarRentalFinished(event))
      .ifPresent(carRepository::save);
  }
}
