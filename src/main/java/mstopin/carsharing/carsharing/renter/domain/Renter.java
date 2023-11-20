package mstopin.carsharing.carsharing.renter.domain;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.AvailableCar;
import mstopin.carsharing.carsharing.car.domain.RentedCar;
import mstopin.carsharing.carsharing.car.domain.ReservedCar;
import mstopin.carsharing.carsharing.renter.domain.events.CarRentalFinishedEvent;
import mstopin.carsharing.carsharing.renter.domain.events.CarRentedEvent;
import mstopin.carsharing.carsharing.renter.domain.events.CarReservationCanceledEvent;
import mstopin.carsharing.carsharing.renter.domain.events.CarReservedEvent;
import mstopin.carsharing.carsharing.renter.domain.rules.RequireActiveRentalForCar;
import mstopin.carsharing.carsharing.renter.domain.rules.RequireActiveReservationForCar;
import mstopin.carsharing.carsharing.renter.domain.rules.RequireEmptyOrExpiredReservation;
import mstopin.carsharing.carsharing.renter.domain.rules.RequireMinimalFuelPercent;
import mstopin.carsharing.common.domain.AggregateRoot;
import mstopin.carsharing.common.domain.Entity;

import java.util.UUID;

@RequiredArgsConstructor
public class Renter implements AggregateRoot, Entity {
  private final UUID renterId;
  private final Reservation reservation;
  private final Rental rental;
  private final ReservationExpirationPolicy reservationExpirationPolicy;

  @Override
  public UUID getAggregateId() {
    return renterId;
  }

  public CarReservedEvent reserve(AvailableCar car) {
    this.validateBusinessRule(new RequireEmptyOrExpiredReservation(reservation));
    this.validateBusinessRule(new RequireMinimalFuelPercent(car));

    ReservationExpiration reservationExpiration = reservationExpirationPolicy.calculateFor(this);

    return new CarReservedEvent(
      renterId,
      car.getAggregateId(),
      reservationExpiration.getExpiresAt()
    );
  }

  public CarReservationCanceledEvent cancelReservation(ReservedCar car) {
    this.validateBusinessRule(new RequireActiveReservationForCar(reservation, car));

    return new CarReservationCanceledEvent(
      getAggregateId(),
      car.getAggregateId(),
      reservation.getId()
    );
  }

  public CarRentedEvent rent(ReservedCar car) {
    this.validateBusinessRule(new RequireActiveReservationForCar(reservation, car));

    return new CarRentedEvent(
      getAggregateId(),
      car.getAggregateId()
    );
  }

  public CarRentalFinishedEvent finishRental(RentedCar car) {
    this.validateBusinessRule(new RequireActiveRentalForCar(rental, car));

    return new CarRentalFinishedEvent(
      getAggregateId(),
      car.getAggregateId(),
      rental.getId()
    );
  }
}
