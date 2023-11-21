package mstopin.carsharing.carsharing.renter.application.handlers;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.CarRepository;
import mstopin.carsharing.carsharing.car.domain.RentedCar;
import mstopin.carsharing.carsharing.renter.application.commands.FinishRentalCommand;
import mstopin.carsharing.carsharing.renter.domain.Renter;
import mstopin.carsharing.carsharing.renter.domain.RenterRepository;
import mstopin.carsharing.carsharing.renter.domain.events.CarRentalFinishedEvent;
import mstopin.carsharing.common.application.CommandHandler;

@RequiredArgsConstructor
public class FinishRentalCommandHandler implements CommandHandler<FinishRentalCommand, Void> {
  private final CarRepository carRepository;
  private final RenterRepository renterRepository;

  @Override
  public Void execute(FinishRentalCommand command) {
    Renter renter = renterRepository
      .find(command.getRenterId())
      .orElseThrow(() -> new IllegalArgumentException("Cannot find render with id: " + command.getRenterId()));

    RentedCar car = carRepository
      .findRentedCarById(command.getCarId())
      .orElseThrow(() -> new IllegalArgumentException("Cannot find rented car with id: " + command.getCarId()));

    CarRentalFinishedEvent carRentalFinishedEvent = renter.finishRental(car);
    renterRepository.publishDomainEvent(carRentalFinishedEvent);

    return null;
  }
}
