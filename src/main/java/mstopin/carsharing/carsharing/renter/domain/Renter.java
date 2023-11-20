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
  private final AbstractRental abstractRental;

  @Override
  public UUID getAggregateId() {
    return renterId.getId();
  }

  public boolean hasActiveRental() {
    if (abstractRental == null) {
      return false;
    }

    return !abstractRental.isExpired();
  }

  public CarReservedEvent reserve(AvailableCar car) {
    this.validateBusinessRule(new EnsureMaxOneRentalRule(this));
    this.validateBusinessRule(new RequireMinimalFuelPercentRule(car));

    Reservation reservation = Reservation.reserveNow(car.getAggregateId());

    return new CarReservedEvent(
      getAggregateId(),
      car.getAggregateId(),
      reservation.getValidTo()
    );
  }

  public CarReservationCanceledEvent cancelReservation(ReservedCar car) {
    this.validateBusinessRule(new AssertRentalAppliesToCar(car, abstractRental));
    this.validateBusinessRule(new RequireNotExpiredRental(abstractRental));

    return new CarReservationCanceledEvent(
      getAggregateId(),
      car.getAggregateId()
    );
  }

  public CarRentedEvent rent(ReservedCar car) {
    this.validateBusinessRule(new AssertRentalAppliesToCar(car, abstractRental));
    this.validateBusinessRule(new RequireNotExpiredRental(abstractRental));

    return new CarRentedEvent(
      getAggregateId(),
      car.getAggregateId()
    );
  }

  public CarRentalFinishedEvent finishRental(RentedCar car) {
    this.validateBusinessRule(new AssertRentalAppliesToCar(car, abstractRental));

    return new CarRentalFinishedEvent(
      getAggregateId(),
      car.getAggregateId()
    );
  }
}
