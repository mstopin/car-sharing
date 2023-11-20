package mstopin.carsharing.carsharing.renter.application.handlers;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.AvailableCar;
import mstopin.carsharing.carsharing.renter.application.FindAvailableCar;
import mstopin.carsharing.carsharing.renter.application.commands.ReserveCarCommand;
import mstopin.carsharing.carsharing.renter.domain.Renter;
import mstopin.carsharing.carsharing.renter.domain.RenterRepository;
import mstopin.carsharing.carsharing.renter.domain.events.CarReservedEvent;
import mstopin.carsharing.common.application.CommandHandler;

@RequiredArgsConstructor
public class ReserveCarCommandHandler implements CommandHandler<ReserveCarCommand, Void> {
  private final FindAvailableCar findAvailableCar;
  private final RenterRepository renterRepository;

  @Override
  public Void execute(ReserveCarCommand command) {
    Renter renter = renterRepository
      .find(command.getRenterId())
      .orElseThrow(() -> new IllegalArgumentException("Cannot find renter with id: " + command.getRenterId()));

    AvailableCar car = findAvailableCar
      .findAvailableCarById(command.getCarId())
      .orElseThrow(() -> new IllegalArgumentException("Cannot find available car with id: " + command.getCarId()));

    CarReservedEvent carReservedEvent = renter.reserve(car);
    renterRepository.publishDomainEvent(carReservedEvent);

    return null;
  }
}
