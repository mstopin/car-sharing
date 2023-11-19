package mstopin.carsharing.carsharing.renter.domain;

import mstopin.carsharing.carsharing.car.domain.AvailableCar;
import mstopin.carsharing.carsharing.car.domain.RentedCar;
import mstopin.carsharing.carsharing.renter.domain.events.CarReservationFailedEvent;
import mstopin.carsharing.carsharing.renter.domain.events.CarReservedEvent;
import mstopin.carsharing.carsharing.renter.domain.events.RenterEvent;
import mstopin.carsharing.common.domain.AggregateRoot;

import java.util.Optional;
import java.util.UUID;

public class Renter implements AggregateRoot {

  private final RenterId renterId;
  private final ReservationPolicies reservationPolicies;
  private Rental rental;

  public Renter(RenterId renterId) {
    this.renterId = renterId;
    this.reservationPolicies = ReservationPolicies.of(ReservationPolicy.allPolicies());
  }

  @Override
  public UUID getAggregateId() {
    return renterId.getId();
  }


  public RenterEvent reserve(AvailableCar car) {
    Optional<Rejection> reserveRejection = reservationPolicies.isSatisfied(this, car);

    if (reserveRejection.isPresent()) {
      return new CarReservationFailedEvent(
        getAggregateId(),
        car.getAggregateId(),
        reserveRejection.get().getReason()
      );
    }

    ReservationRental reservationRental = ReservationRental.fromNow(car);
    this.rental = reservationRental;

    return new CarReservedEvent(
      getAggregateId(),
      car.getAggregateId(),
      reservationRental.getTo()
    );
  }


  public void rent(RentedCar car) {

  }
  public boolean hasRental() {
    if (rental == null) {
      return false;
    }

    if (rental instanceof ReservationRental) {
      return !((ReservationRental) rental).isExpired();
    }

    return true;
  }
}
