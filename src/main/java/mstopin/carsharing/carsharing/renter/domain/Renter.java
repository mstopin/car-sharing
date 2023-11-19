package mstopin.carsharing.carsharing.renter.domain;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.AvailableCar;
import mstopin.carsharing.carsharing.car.domain.RentedCar;
import mstopin.carsharing.carsharing.car.domain.ReservedCar;
import mstopin.carsharing.carsharing.renter.domain.events.CarRentalFinishedEvent;
import mstopin.carsharing.carsharing.renter.domain.events.CarRentedEvent;
import mstopin.carsharing.carsharing.renter.domain.events.CarReservationCanceledEvent;
import mstopin.carsharing.carsharing.renter.domain.events.CarReservedEvent;
import mstopin.carsharing.carsharing.renter.domain.rules.AssertRentalAppliesToCar;
import mstopin.carsharing.carsharing.renter.domain.rules.EnsureMaxOneRentalRule;
import mstopin.carsharing.carsharing.renter.domain.rules.RequireMinimalFuelPercentRule;
import mstopin.carsharing.carsharing.renter.domain.rules.RequireNotExpiredRental;
import mstopin.carsharing.common.domain.AggregateRoot;
import mstopin.carsharing.common.domain.Entity;

import java.util.UUID;

@RequiredArgsConstructor
public class Renter implements AggregateRoot, Entity {
  private final RenterId renterId;
  private final Rental rental;

  @Override
  public UUID getAggregateId() {
    return renterId.getId();
  }

  public boolean hasRental() {
    if (rental == null) {
      return false;
    }

    return !rental.isExpired();
  }

  public CarReservedEvent reserve(AvailableCar car) {
    this.validateBusinessRule(new EnsureMaxOneRentalRule(this));
    this.validateBusinessRule(new RequireMinimalFuelPercentRule(car));

    ReservationRental rental = ReservationRental.fromNow(car);

    return new CarReservedEvent(
      getAggregateId(),
      car.getAggregateId(),
      rental.getTo()
    );
  }

  public CarReservationCanceledEvent cancelReservation(ReservedCar car) {
    this.validateBusinessRule(new AssertRentalAppliesToCar(car, rental));
    this.validateBusinessRule(new RequireNotExpiredRental(rental));

    return new CarReservationCanceledEvent(
      getAggregateId(),
      car.getAggregateId()
    );
  }

  public CarRentedEvent rent(ReservedCar car) {
    this.validateBusinessRule(new AssertRentalAppliesToCar(car, rental));
    this.validateBusinessRule(new RequireNotExpiredRental(rental));

    return new CarRentedEvent(
      getAggregateId(),
      car.getAggregateId()
    );
  }

  public CarRentalFinishedEvent finishRent(RentedCar car) {
    this.validateBusinessRule(new AssertRentalAppliesToCar(car, rental));

    return new CarRentalFinishedEvent(
      getAggregateId(),
      car.getAggregateId()
    );
  }
}
