package mstopin.carsharing.carsharing.renter.domain;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.Car;

import java.util.UUID;

@RequiredArgsConstructor
public class Reservation {
  private final UUID carId;
  private final ReservationExpiration reservationExpiration;

  public boolean isExpired() {
    return reservationExpiration.isExpired();
  }

  public boolean isFor(Car car) {
    return car.getAggregateId().equals(carId);
  }
}
