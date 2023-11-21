package mstopin.carsharing.carsharing.car.application;

import lombok.AllArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.*;
import mstopin.carsharing.carsharing.renter.domain.events.CarRentalFinishedEvent;
import mstopin.carsharing.carsharing.renter.domain.events.CarRentedEvent;
import mstopin.carsharing.carsharing.renter.domain.events.CarReservationCanceledEvent;
import mstopin.carsharing.carsharing.renter.domain.events.CarReservedEvent;
import org.springframework.context.event.EventListener;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

@AllArgsConstructor
public class RenterEventsHandler {
  private final CarRepository carRepository;

  private void handleIfCarFound(UUID carId, Consumer<Car> handler) {
    Optional<Car> maybeCar = carRepository.findById(carId);
    if (maybeCar.isEmpty()) {
      return;
    }

    handler.accept(maybeCar.get());
  }

  @EventListener
  void handleCarReserved(CarReservedEvent event) {
    handleIfCarFound(event.getCarId(), (car) -> {
      if (car instanceof AvailableCar availableCar) {
        carRepository.save(
          availableCar.handleCarReserved(event)
        );
      }
    });
  }

  @EventListener
  void handleCarReservationCancelled(CarReservationCanceledEvent event) {
    handleIfCarFound(event.getCarId(), (car) -> {
      if (car instanceof ReservedCar reservedCar) {
        carRepository.save(
          reservedCar.handleCarReservationCancelled(event)
        );
      }
    });
  }

  @EventListener
  void handleCarRented(CarRentedEvent event) {
    handleIfCarFound(event.getCarId(), (car) -> {
      if (car instanceof ReservedCar reservedCar) {
        carRepository.save(
          reservedCar.handleCarRented(event)
        );
      }
    });
  }

  @EventListener
  void handleCarRentalFinished(CarRentalFinishedEvent event) {
    handleIfCarFound(event.getCarId(), (car) -> {
      if (car instanceof RentedCar rentedCar) {
        carRepository.save(
          rentedCar.handleCarRentalFinished(event)
        );
      }
    });
  }
}
