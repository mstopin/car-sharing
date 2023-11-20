package mstopin.carsharing.carsharing.renter.application.handlers;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.ReservedCar;
import mstopin.carsharing.carsharing.renter.application.FindReservedCar;
import mstopin.carsharing.carsharing.renter.application.commands.RentCarCommand;
import mstopin.carsharing.carsharing.renter.domain.Renter;
import mstopin.carsharing.carsharing.renter.domain.RenterRepository;
import mstopin.carsharing.carsharing.renter.domain.events.CarRentedEvent;
import mstopin.carsharing.common.application.CommandHandler;

@RequiredArgsConstructor
public class RentCarCommandHandler implements CommandHandler<RentCarCommand, Void> {
  private final FindReservedCar findReservedCar;
  private final RenterRepository renterRepository;

  @Override
  public Void execute(RentCarCommand command) {
    Renter renter = renterRepository
      .find(command.getRenterId())
      .orElseThrow(() -> new IllegalArgumentException("Cannot find render with id: " + command.getRenterId()));

    ReservedCar car = findReservedCar
      .findReservedCarById(command.getCarId())
      .orElseThrow(() -> new IllegalArgumentException("Cannot find reserved car with id: " + command.getCarId()));

    CarRentedEvent carRentedEvent = renter.rent(car);
    renterRepository.publishDomainEvent(carRentedEvent);

    return null;
  }
}
